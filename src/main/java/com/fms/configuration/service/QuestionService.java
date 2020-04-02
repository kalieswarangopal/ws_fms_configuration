package com.fms.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.configuration.dto.QuestionRequestDTO;
import com.fms.configuration.dto.QuestionResponseDTO;
import com.fms.configuration.entity.Question;
import com.fms.configuration.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	DatabaseClient databaseClient;

	public Mono<Question> createQuestion(QuestionRequestDTO questionRequestDTO) {

		Question question = mapQuestionRequestDTO(questionRequestDTO);
		Mono<Question> questionMono = questionRepository.save(question);
		return questionMono;

	}

	private Question mapQuestionRequestDTO(QuestionRequestDTO questionRequestDTO) {
		Question question = new Question();
		question.setFeedbackType(questionRequestDTO.getFeedbackType());
		question.setAnswerType(questionRequestDTO.getAnswerType());
		question.setQuestionDescription(questionRequestDTO
				.getQuestionDescription());
		return question;
	}

	public Flux<QuestionResponseDTO> getQuestions() {

		Flux<QuestionResponseDTO> response = databaseClient
				.execute()
				.sql("select q.questionid, q.questiondescription, q.feedbacktype, count(a.answerid) as totalanswers from question q left outer join answer a on q.questionid=a.questionid group by q.questionid;")
				.as(QuestionResponseDTO.class).fetch().all();
		return response;

	}

}
