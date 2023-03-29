package com.movie.model.vo;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class AnswerVO {
	
	@ApiModelProperty(value = "Question identifier (required)", example = "1")
	@NotNull(message = "The field question id is required")	
	private Long id;
	
	@ApiModelProperty(value = "The first movie option identifier (required)", example = "1")
	@NotNull(message = "The field question id is required")	
	private Long idFirstMovie;
	
	@ApiModelProperty(value = "The second movie option identifier (required)", example = "1")
	@NotNull(message = "The field idSecondMovie is required")	
	private Long idSecondMovie;
	
	@ApiModelProperty(value = "The identifier of movie chosen (required)", example = "1")
	@NotNull(message = "The field idChosenMovie is required")	
	private Long idChosenMovie;
	
}
