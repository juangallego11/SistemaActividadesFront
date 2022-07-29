package com.logistic.exceptions.responses;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author JuanG
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException{
    public NoContentException(String exception) {
		super(exception);
	}
}
