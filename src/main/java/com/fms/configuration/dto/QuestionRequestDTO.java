package com.fms.configuration.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDTO {
	
	private String feedbackType;
	private String answerType;
	private String questionDescription;
	private List<String> answers;

}
