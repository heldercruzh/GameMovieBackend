package com.movie.model.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class UserVO {
	
	@ApiModelProperty(value = "User identifier (optional)", example = "1")	
	private Long id;	
	
	@ApiModelProperty(value = "User name (required)", example = "Name")
	@NotNull(message = "The field name is required")
	@NotEmpty(message = "The field name is required")
	private String name;
	
	@ApiModelProperty(value = "User e-mail (required)", example = "Name")
	@NotNull(message = "The field e-mail is required")
	@NotEmpty(message = "The field e-mail is required")
	private String email;
	
	@ApiModelProperty(value = "User password (required)", example = "Name")
	@NotNull(message = "The field password is required")
	@NotEmpty(message = "The field password is required")
	private String password;	
	
		
}
