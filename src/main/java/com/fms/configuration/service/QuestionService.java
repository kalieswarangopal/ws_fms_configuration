package com.fms.configuration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.configuration.dto.EditQuestionRequestDTO;
import com.fms.configuration.dto.QuestionDTO;
import com.fms.configuration.dto.QuestionRequestDTO;
import com.fms.configuration.dto.QuestionResponseDTO;
import com.fms.configuration.entity.Answer;
import com.fms.configuration.entity.Question;
import com.fms.configuration.repository.AnswerRepository;
import com.fms.configuration.repository.QuestionRepository;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepository;

	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	DatabaseClient databaseClient;

	public Mono<Void> createQuestion(QuestionRequestDTO questionRequestDTO) {

		Mono<Void> voidMono = Mono.empty();
		Question question = mapQuestionRequestDTO(questionRequestDTO);

		if (!CollectionUtils.isEmpty(questionRequestDTO.getAnswers())) {
			Mono<Question> questionMono = questionRepository.save(question);
			voidMono = questionMono.map(Question::getQuestionID)
					.flatMap(value -> createAnswers(value, questionRequestDTO.getAnswers())).then();
		} else {
			questionRepository.save(question).subscribe();
		}

		return voidMono;

	}

	private Question mapQuestionRequestDTO(QuestionRequestDTO questionRequestDTO) {

		Question question = new Question();
		question.setFeedbackType(questionRequestDTO.getFeedbackType());
		question.setAnswerType(questionRequestDTO.getAnswerType());
		question.setQuestionDescription(questionRequestDTO.getQuestionDescription());
		return question;
	}

	private Mono<Void> createAnswers(Integer questionID, List<String> answers) {

		List<Answer> answerList = new ArrayList<>();
		answers.forEach(answerText -> {
			Answer answer = new Answer(null, questionID, answerText);
			answerList.add(answer);
		});

		answerRepository.saveAll(answerList).subscribe();
		return Mono.empty();
	}

	public Flux<QuestionResponseDTO> getQuestions() {

		Flux<QuestionResponseDTO> response = databaseClient.execute().sql(
				"select q.questionid, q.questiondescription, q.feedbacktype, count(a.answerid) as totalanswers from question q left outer join answer a on q.questionid=a.questionid group by q.questionid;")
				.as(QuestionResponseDTO.class).fetch().all();
		return response;

	}

	public Flux<QuestionDTO> getQuestion(Integer questionID) {

		Flux<QuestionDTO> response = databaseClient.execute().sql(
				"select q.questionid, q.questiondescription, q.feedbacktype, q.answertype, a.answerid, a.answertext from question q left join answer a on q.questionid=a.questionid where q.questionid = :questionID")
				.bind("questionID", questionID).as(QuestionDTO.class).fetch().all();
		return response;

	}

	public Mono<Question> editQuestion(EditQuestionRequestDTO editQuestionRequestDTO) {

		Mono<Question> questionMono = null;

		if (Optional.ofNullable(editQuestionRequestDTO.getQuestion()).isPresent()) {
			questionMono = questionRepository.save(editQuestionRequestDTO.getQuestion());
		}

		if (!CollectionUtils.isEmpty(editQuestionRequestDTO.getAnswers())) {
			answerRepository.saveAll(editQuestionRequestDTO.getAnswers()).subscribe();
		}

		return questionMono;

	}

	public Mono<Void> deleteQuestion(Integer id) {

		answerRepository.deleteByQuestionID(id).subscribe();

		return questionRepository.deleteById(id);

	}

}
