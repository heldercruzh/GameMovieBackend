package com.movie.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class GameDTO {
	
	private Long id;	
	private UserDTO user;
	private boolean blOpen;
		
}
