package com.intive.patronage.calc.errors;

public class FileDoesNotExistException extends RuntimeException {

	public FileDoesNotExistException() {
		super("Wybrany plik nie istnieje. Sprawdź listę dostępnych plików "
				+ "wywołując metodę <getPossibleRange>.");
	}

}
