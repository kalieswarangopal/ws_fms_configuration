package com.fms.configuration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDTO {
	
	private Integer questionid;
	private String questiondescription;
	private String feedbacktype;
	private String answertype;
	private Integer answerid;
	private String answertext;

}
