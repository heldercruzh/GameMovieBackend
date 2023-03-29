package com.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AnswerDTO {
		
	private Long id;
	private Long idGame;
	private boolean blAnswer;
	private Long scoreGame;
	private Long scoreUser;
	
}
