package com.movie.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProfileEnum {

	GAMER(1, "Gamer"),
	ADMINISTRADOR(1, "Administrador");
	
	private Integer id;	
	private String descricao;
		
}
