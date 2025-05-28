package com.pscode.nourish_now.exception.custome;

import org.springframework.security.core.AuthenticationException;

public class UserAlreadyExistException extends AuthenticationException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserAlreadyExistException(String msg) {
		super(msg);
	}

	public UserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
