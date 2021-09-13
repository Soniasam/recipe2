package com.codekages.exception;

public class IngredientNotFoundException extends Exception {

	public IngredientNotFoundException() {
		super();
	}

	public IngredientNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public IngredientNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public IngredientNotFoundException(String message) {
		super(message);
	}

	public IngredientNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
