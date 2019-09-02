package org.example.test.views.components.exceptions;

public class ApplicationViewException extends RuntimeException {

	/** Value that it is used during deserialization to verify that the sender and receiver of a serialized object have loaded classes for that object that are compatible with respect to serialization. */
	private static final long serialVersionUID = 8788631098311497294L;

	public ApplicationViewException(String message, Exception e) {
		super(message, e);
	}
	
}
