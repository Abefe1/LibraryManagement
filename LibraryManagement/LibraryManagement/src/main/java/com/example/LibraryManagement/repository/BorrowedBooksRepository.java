package com.example.LibraryManagement.repository;

import com.example.LibraryManagement.model.Book;
import com.example.LibraryManagement.model.BorrowedBooks;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowedBooksRepository extends JpaRepository<BorrowedBooks, Integer> {


    Optional<BorrowedBooks> findByBorrowedBookIsbn(String isbn);

//    @Query("select c from BorrowedBooks c where c.borrowedBookIsbn=?1")
//    Book getBorrowedByIsbn(String isbn);

}
