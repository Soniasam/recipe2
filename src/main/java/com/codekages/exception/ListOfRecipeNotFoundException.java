package com.codekages.exception;

public class ListOfRecipeNotFoundException extends Exception{

	public ListOfRecipeNotFoundException() {
		super();
		
	}

	public ListOfRecipeNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public ListOfRecipeNotFoundException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public ListOfRecipeNotFoundException(String message) {
		super(message);
		
	}

	public ListOfRecipeNotFoundException(Throwable cause) {
		super(cause);
		
	}

}
