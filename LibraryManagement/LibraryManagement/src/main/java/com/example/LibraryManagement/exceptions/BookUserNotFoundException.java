package com.example.LibraryManagement.exceptions;

public class BookUserNotFoundException extends RuntimeException{
    public BookUserNotFoundException(String message) {
        super(message);
    }
}
