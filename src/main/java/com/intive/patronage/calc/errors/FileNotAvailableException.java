package com.intive.patronage.calc.errors;

public class FileNotAvailableException extends RuntimeException {

	public FileNotAvailableException() {
		super("Pliki nie są dostępne. Zmień ustawienia w application.properties "
				+ "usuwając wartość <db> przy <calc.history-service>.");
	}
}
