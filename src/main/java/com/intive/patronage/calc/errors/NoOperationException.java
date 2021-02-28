package com.intive.patronage.calc.errors;

public class NoOperationException extends RuntimeException {

	public NoOperationException() {
		super("Lista operacji jest pusta.");
	}
}
