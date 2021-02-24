package com.intive.patronage.calc.errors;

public class OperationImpossibleException extends RuntimeException {

    public OperationImpossibleException() {
        super("Nie można wykonać operacji. Zapoznaj się z instrukcją w pliku README.md i spróbuj jeszcze raz.");
    }
}
