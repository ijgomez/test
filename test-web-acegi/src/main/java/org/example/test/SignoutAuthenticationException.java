package org.example.test;

import org.acegisecurity.AuthenticationException;

public class SignoutAuthenticationException extends AuthenticationException {

	private static final long serialVersionUID = -6499608755612653789L;

	public SignoutAuthenticationException(String msg) {
		super(msg);
	}

}
