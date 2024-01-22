package com.example.LibraryManagement.repository;

import com.example.LibraryManagement.model.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository

public interface BookRepository extends JpaRepository <Book, Integer> {


    Optional<Book> findByIsbn(String isbn);


    Optional<Book> findByTitle(String title);


//    @Query("select b from Book b where b.isbn=?1")
//    Book getByIsbn(String isbn);
//
//    @Query("select b from Book b where b.title=?1")
//    Book getByTitle(String title);


}
