package com.fms.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.fms.configuration.dto.QuestionRequestDTO;
import com.fms.configuration.dto.QuestionResponseDTO;
import com.fms.configuration.entity.Question;
import com.fms.configuration.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	QuestionService questionService;

	@PostMapping("/question")
	public ResponseEntity<Mono<Question>> createQuestion(
			@RequestBody QuestionRequestDTO questionRequestDTO) {

		Mono<Question> response = questionService
				.createQuestion(questionRequestDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);

	}

	@GetMapping("/question")
	public Flux<QuestionResponseDTO> getQuestions() {

		return questionService.getQuestions();
	}

}
