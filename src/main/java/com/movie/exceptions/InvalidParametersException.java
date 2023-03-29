package com.movie.exceptions;

import lombok.Generated;

@Generated
public class InvalidParametersException extends RuntimeException {

	private static final long serialVersionUID = 648583825819346885L;

	public InvalidParametersException(String msg) {
		super(msg);
	}
}
