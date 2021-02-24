package com.intive.patronage.calc.errors;

public class FilesLoadException extends RuntimeException {

    public FilesLoadException() {
        super("Nie można załadować pliku/ów.");
    }
}
