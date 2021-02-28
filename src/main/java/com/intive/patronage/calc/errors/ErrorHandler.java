package com.intive.patronage.calc.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {


    @ExceptionHandler(CreateFolderException.class)
    public ResponseEntity<Object> createFolderException(CreateFolderException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FilesLoadException.class)
    public ResponseEntity<Object> filesLoadException(FilesLoadException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<Object> fileStorageException(FileStorageException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IdNumberException.class)
    public ResponseEntity<Object> idNumberException(IdNumberException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoOperationException.class)
    public ResponseEntity<Object> noOperationException(NoOperationException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileNotAvailableException.class)
    public ResponseEntity<Object> fileNotAvailableException(FileNotAvailableException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileDoesNotExistException.class)
    public ResponseEntity<Object> fileDoesNotExistException(FileDoesNotExistException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OperationTypeException.class)
    public ResponseEntity<Object> operationTypeException(OperationTypeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OperationImpossibleException.class)
    public ResponseEntity<Object> operationImpossibleException(OperationImpossibleException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
