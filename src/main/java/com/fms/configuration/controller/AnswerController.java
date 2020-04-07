package com.fms.configuration.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fms.configuration.service.AnswerService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	AnswerService answerService;

	@DeleteMapping("/{id}")
	public Mono<Void> deleteAnswer(@PathVariable Integer id) {

		return answerService.deleteAnswer(id);
	}

}
