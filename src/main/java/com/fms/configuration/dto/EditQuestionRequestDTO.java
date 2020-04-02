package com.fms.configuration.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fms.configuration.entity.Answer;
import com.fms.configuration.entity.Question;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditQuestionRequestDTO {
	
	private Question question;
	private List<Answer> answers;

}
