package com.movie.exceptions;

import lombok.Generated;

@Generated
public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2080165728071492796L;

	public UnauthorizedException(String msg) {
		super(msg);
	}
}
