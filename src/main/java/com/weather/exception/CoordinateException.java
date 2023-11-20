package com.weather.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CoordinateException extends RuntimeException {
	public CoordinateException(String message) {
		super(message);
	}
}
