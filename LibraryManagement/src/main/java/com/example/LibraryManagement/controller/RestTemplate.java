package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.model.BookUser;
import com.example.LibraryManagement.model.BorrowedBooks;
import org.springframework.http.ResponseEntity;

public class RestTemplate {
    public static void main(String[] args) {
        org.springframework.web.client.RestTemplate template= new org.springframework.web.client.RestTemplate();
        String url="http://localhost:8084/books/borrowed";
        BorrowedBooks borrowedBooks =new BorrowedBooks(5, "563278iuqweiwer");
        ResponseEntity<BorrowedBooks> response=template.postForEntity(url, borrowedBooks, BorrowedBooks.class);
        BorrowedBooks extracted=response.getBody();
        System.out.println(extracted);
    }
}

