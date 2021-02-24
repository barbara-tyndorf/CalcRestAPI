package com.intive.patronage.calc.errors;

public class FilenameNotProvidedException extends RuntimeException {

	public FilenameNotProvidedException() {
		super("Podaj nazwę pliku!");
	}

}
