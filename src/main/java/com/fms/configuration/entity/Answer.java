package com.fms.configuration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("answer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
	
	@Id
	@Column("AnswerID")
	private Integer answerID;
	
	@Column("QuestionID")
	private Integer questionID;
	
	@Column("AnswerText")
	private String answerText;

}
