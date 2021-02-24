package com.intive.patronage.calc.errors;

public class FileStorageException extends RuntimeException {

    public FileStorageException() {
        super("Nie można zapisać pliku.");
    }
}
