package com.intive.patronage.calc.errors;

public class OperationTypeException extends RuntimeException {

    public OperationTypeException() {
        super("Nieprawidłowy typ operacji. Spróbuj jeszcze raz.");
    }
}
