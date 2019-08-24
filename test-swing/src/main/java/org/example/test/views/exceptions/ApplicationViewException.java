package org.example.test.views.exceptions;

public class ApplicationViewException extends RuntimeException {

	private static final long serialVersionUID = 8788631098311497294L;

	public ApplicationViewException(String message, Exception e) {
		super(message, e);
	}
	
}
