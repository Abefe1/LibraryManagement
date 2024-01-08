package com.example.LibraryManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

//@RequiredArgsConstructor
@Setter
@Getter
@Table(name="borrowed_books")
@Entity
public class BorrowedBooks {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "borrowed_Book_Id")
    private  int Id;


    @Column(name = "borrower_id", unique = true)
    private int borrowerId;


    @Column(name = "borrowed_book_title")
    private String borrowedBookTitle;

    @Column(name = "borrowed_book_Author")
    private String borrowedBookAuthor;

    @Column(name = "borrowed_book_isbn")
    private String borrowedBookIsbn;

//
    @Column(name = "borrowed_book_pub_year")
    private String borrowedBookPublicationYear;


    public BorrowedBooks(int borrowerId, String isbn) {
        this.borrowerId = borrowerId;
        this.borrowedBookIsbn = isbn;
    }

    public BorrowedBooks() {
    }


    public int getId() {
        return Id;
    }

    public int getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowedBookTitle() {
        return borrowedBookTitle;
    }

    public void setBorrowedBookTitle(String borrowedBookTitle) {
        this.borrowedBookTitle = borrowedBookTitle;
    }

    public String getBorrowedBookAuthor() {
        return borrowedBookAuthor;
    }

    public void setBorrowedBookAuthor(String borrowedBookAuthor) {
        this.borrowedBookAuthor = borrowedBookAuthor;
    }

    public String getBorrowedBookIsbn() {
        return borrowedBookIsbn;
    }

    public void setBorrowedBookIsbn(String borrowedBookIsbn) {
        this.borrowedBookIsbn = borrowedBookIsbn;
    }

    public String getBorrowedBookPublicationYear() {
        return borrowedBookPublicationYear;
    }

    public void setBorrowedBookPublicationYear(String borrowedBookPublicationYear) {
        this.borrowedBookPublicationYear = borrowedBookPublicationYear;
    }

    @Override
    public String toString() {
        return "Book with the following info:/n"
                + "Title: " + getBorrowedBookTitle() + "/n" +
                "Author: " + getBorrowedBookAuthor() + "/n" +
                "Isbn: " + getBorrowedBookIsbn() + "/n" +
                "has been borrowed by a user and it has been removed from list of available books " +
                "till it is returned";
    }
}