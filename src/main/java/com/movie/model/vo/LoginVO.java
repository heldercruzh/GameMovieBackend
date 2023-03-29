package com.movie.model.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class LoginVO {
		
	@ApiModelProperty(value = "User E-mail (required)", example = "email@dominio.com")
	@NotNull(message = "The field e-mail is required")
	@NotEmpty(message = "The field e-mail is required")
	private String email;
	
	@ApiModelProperty(value = "User password (required)", example = "xxxxx")
	@NotNull(message = "The field password is required")
	@NotEmpty(message = "The field password is required")
	private String password;
		
}
