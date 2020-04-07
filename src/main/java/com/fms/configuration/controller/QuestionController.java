package com.fms.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.configuration.dto.EditQuestionRequestDTO;
import com.fms.configuration.dto.QuestionDTO;
import com.fms.configuration.dto.QuestionRequestDTO;
import com.fms.configuration.dto.QuestionResponseDTO;
import com.fms.configuration.entity.Question;
import com.fms.configuration.service.QuestionService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PostMapping
	public ResponseEntity<Mono<Void>> createQuestion(@RequestBody QuestionRequestDTO questionRequestDTO) {

		Mono<Void> response = questionService.createQuestion(questionRequestDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping
	public Flux<QuestionResponseDTO> getQuestions() {

		return questionService.getQuestions();
	}

	@GetMapping("/{id}")
	public Flux<QuestionDTO> getQuestion(@PathVariable Integer id) {

		return questionService.getQuestion(id);
	}

	@PutMapping
	public Mono<Question> editQuestion(@RequestBody EditQuestionRequestDTO editQuestionRequestDTO) {

		return questionService.editQuestion(editQuestionRequestDTO);
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteQuestion(@PathVariable Integer id) {

		return questionService.deleteQuestion(id);
	}

}
