package com.movie.exceptions;

import lombok.Generated;

@Generated
public class MessageException extends RuntimeException {

	private static final long serialVersionUID = 8369989871623756901L;

	public MessageException(String msg) {
		super(msg);
	}

}
