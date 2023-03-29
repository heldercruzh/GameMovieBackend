package com.movie.exceptions;

import lombok.Generated;

@Generated
public class DataAlreadyRegisteredException extends RuntimeException {

	private static final long serialVersionUID = -4419857949077202288L;

	public DataAlreadyRegisteredException(String msg) {
		super(msg);
	}
}
