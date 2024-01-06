package com.example.LibraryManagement.controller;


import com.example.LibraryManagement.model.Book;
import com.example.LibraryManagement.model.BorrowedBooks;
import com.example.LibraryManagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class ControllerBooks {

    @Autowired
    private BookService bookService;

    public ControllerBooks(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/post")
    public ResponseEntity<Book> saveBooks(@RequestBody Book book){
        bookService.saveBooks(book).getBody();
        return ResponseEntity.ok(book);
    }

    @GetMapping("/allbooks")
    public ResponseEntity<List<Book>> getAllBook(){

        return ResponseEntity.ok(bookService.getAllBooks().getBody());
    }

    @PostMapping("/borrowed")
    public ResponseEntity<BorrowedBooks> addBorrowedBook(@RequestBody BorrowedBooks borrowedBooks){
        bookService.addBorrowedBook(borrowedBooks.getBorrowerId(), borrowedBooks.getBorrowedBookIsbn());
        return ResponseEntity.ok(borrowedBooks);
    }

    @GetMapping("/allborrowed")
    public ResponseEntity<List<BorrowedBooks>> getAllBorrowed(){

        return ResponseEntity.ok(bookService.getAllBorrowed().getBody());
    }

    @PostMapping("/return")
    public ResponseEntity<BorrowedBooks> returnBorrowedBook(@RequestBody BorrowedBooks borrowedBooks){
        bookService.returnBorrowedBook(borrowedBooks.getBorrowedBookIsbn());
        return ResponseEntity.ok(borrowedBooks);
    }


    @DeleteMapping("/return")
    public ResponseEntity<BorrowedBooks> returnBorrowedBook(String isbn, @RequestBody BorrowedBooks borrowedBooks){
         bookService.returnBorrowedBook(isbn);

        return ResponseEntity.ok(borrowedBooks);
    }


    @GetMapping("/user/{isbn}")
    public ResponseEntity<Book> findUserByIsbnNo(@PathVariable String isbn){
        return ResponseEntity.ok(bookService.findBookByIsbnNo(isbn));
    }

    @GetMapping("/user/{title}")
    public ResponseEntity<Book> findUserByTitle(@PathVariable String title){
        return ResponseEntity.ok(bookService.findBookByTitle(title).getBody());
    }
}
