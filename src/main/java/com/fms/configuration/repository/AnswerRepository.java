package com.fms.configuration.repository;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.fms.configuration.entity.Answer;

@Repository
public interface AnswerRepository extends
		ReactiveCrudRepository<Answer, Integer> {

	@Query("DELETE FROM answer WHERE questionid= = :questionID")
	public void deleteByQuestionID(Integer questionID);

}
