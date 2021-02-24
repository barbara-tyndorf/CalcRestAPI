package com.intive.patronage.calc.errors;

public class IdNumberException extends RuntimeException {

	public IdNumberException() {
		super("Podaj liczbę, z zakresu dostępnych wpisów, który możesz sprawdzić "
				+ "wywołując metodę <getPossibleOperationsRange>.");
	}
}
