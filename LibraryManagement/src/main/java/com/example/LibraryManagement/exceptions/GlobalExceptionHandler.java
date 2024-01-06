package com.example.LibraryManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingMatrixVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingMatrixVariableException.class)
    public Map<String, String> badRequestHandler(@org.jetbrains.annotations.NotNull MethodArgumentNotValidException exception){

        Map<String, String> errorMap=new HashMap<>();
        List<FieldError> fieldErrors= exception.getFieldErrors();
        for (FieldError error: fieldErrors){
            errorMap.put(error.getField(), error.getDefaultMessage());
        }
        return errorMap;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public  Map<String, String> duplicateEntryExceptionHandler(SQLIntegrityConstraintViolationException exception){
        Map<String, String> errorMap= new HashMap<>();
        exception.forEach(e-> {errorMap.put(e.getLocalizedMessage(), e.getLocalizedMessage());
        });

        return errorMap;
    };

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookUserNotFoundException.class)
    public String BookUserNotFoundexception(BookUserNotFoundException exception){
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BookUserNotFoundException.class)
    public String BookNotFoundexception(BookNotFoundException exception){
        return exception.getMessage();
    }






}
