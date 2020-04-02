package com.fms.configuration.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponseDTO {
	
	private Integer questionid;
	private String questiondescription;
	private Integer totalanswers;
	private String feedbacktype;

}
