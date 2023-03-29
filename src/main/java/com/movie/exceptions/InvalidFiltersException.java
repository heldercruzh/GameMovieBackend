package com.movie.exceptions;

import lombok.Generated;

@Generated
public class InvalidFiltersException extends RuntimeException {

	private static final long serialVersionUID = -7974134095265932797L;

	public InvalidFiltersException(String msg) {
		super(msg);
	}
}