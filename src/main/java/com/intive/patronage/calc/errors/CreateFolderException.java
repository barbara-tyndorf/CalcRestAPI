package com.intive.patronage.calc.errors;

public class CreateFolderException extends RuntimeException {

    public CreateFolderException(){
        super("Nie można utworzyć folderu.");
    }
}
