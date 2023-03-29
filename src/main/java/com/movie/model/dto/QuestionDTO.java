package com.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class QuestionDTO {	
	
	private Long id;
	private GameDTO game;
	private MovieDTO firstMovie;
	private MovieDTO secondMovie;

}
