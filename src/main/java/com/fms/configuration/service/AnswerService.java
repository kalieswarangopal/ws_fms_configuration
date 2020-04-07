package com.fms.configuration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fms.configuration.repository.AnswerRepository;

import reactor.core.publisher.Mono;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository answerRepository;

	public Mono<Void> deleteAnswer(Integer id) {

		return answerRepository.deleteById(id);
	}

}
