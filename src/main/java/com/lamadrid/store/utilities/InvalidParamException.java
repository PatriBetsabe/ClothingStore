package com.lamadrid.store.utilities;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "invalid parameter")
public class InvalidParamException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidParamException() {
		super();
	}

	public InvalidParamException(String text) {
		super(text);
	}
}
