package com.example.LibraryManagement.service;

import com.example.LibraryManagement.exceptions.BookNotFoundException;
import com.example.LibraryManagement.exceptions.BookUserNotFoundException;
import com.example.LibraryManagement.model.Book;
import com.example.LibraryManagement.model.BookUser;
import com.example.LibraryManagement.model.BorrowedBooks;
import com.example.LibraryManagement.repository.BookRepository;
import com.example.LibraryManagement.repository.BorrowedBooksRepository;
import com.example.LibraryManagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class BookService {

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    private final UserRepository userRepository;
//
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

    @CacheEvict(value = "allBooks", allEntries = true)
    public ResponseEntity<Book> saveBooks(Book book) {
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @Cacheable("allBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @CacheEvict(value = "allBorrowed", allEntries = true)
    public String addBorrowedBook(Integer id, String isbn) {
        BookUser borrower = userRepository.findById(id).orElseThrow(
                () -> new BookUserNotFoundException(String.format("BookUser with %d id was not found", id))
        );
        Book borrowed = bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new BookUserNotFoundException(String.format("Book with %s isbn was not found", isbn))
        );

        BorrowedBooks toBeBorrowed = new BorrowedBooks();
        assert borrower != null;
        assert false;


        toBeBorrowed.setBorrowerId(borrower.getId());
        toBeBorrowed.setBorrowedBookAuthor(borrowed.getAuthor());
        toBeBorrowed.setBorrowedBookIsbn(borrowed.getIsbn());
        toBeBorrowed.setBorrowedBookTitle(borrowed.getTitle());

        borrowedBooksRepository.save(toBeBorrowed);
        bookRepository.delete(borrowed);
        return "Book with the following info:/n"
                + "Title: " + toBeBorrowed.getBorrowedBookTitle() + "/n" +
                "Author: " + toBeBorrowed.getBorrowedBookAuthor() + "/n" +
                "Isbn: " + toBeBorrowed.getBorrowedBookIsbn() + "/n" +
                "has been borrowed by" + borrower.getFullName() + "and it has been removed from list of available books " +
                "till it is returned";

    }

    @CacheEvict(value = "allBorrowed", allEntries = true)
    public String returnBorrowedBook(String isbn) {
        BorrowedBooks toBeReturned = borrowedBooksRepository.findByBorrowedBookIsbn(isbn).orElseThrow(() ->
                new BookNotFoundException(String.format("Book with %s isbn was not found in the borrowed book list", isbn))
        );


        Book returned = new Book();

        assert toBeReturned !=null;

         returned.setAuthor(toBeReturned.getBorrowedBookAuthor());
         returned.setIsbn(toBeReturned.getBorrowedBookIsbn());
         returned.setTitle(toBeReturned.getBorrowedBookTitle());
         returned.setPublicationYear(toBeReturned.getBorrowedBookPublicationYear());
//
        bookRepository.save(returned);
        borrowedBooksRepository.delete(toBeReturned);

        return ("Book with the following info:/n"
                + "Title: " + toBeReturned.getBorrowedBookTitle() + "/n" +
                "Author: " + toBeReturned.getBorrowedBookAuthor() + "/n" +
                "Isbn: " + toBeReturned.getBorrowedBookIsbn() + "/n" +
                "has been returned by user with Id: " + toBeReturned.getBorrowerId() +
                "and it has been removed from list of borrowed books " +
                "till it is returned"
        );
    }

    @Cacheable("allBorrowed")
    public ResponseEntity<List<BorrowedBooks>> getAllBorrowed() {
        return ResponseEntity.ok(borrowedBooksRepository.findAll());
    }



    @Cacheable(value = "singleBook", key = "#isbn")
    public Book findBookByIsbnNo(String isbn){
        return  bookRepository.findByIsbn(isbn).orElseThrow(
                () -> new BookNotFoundException(String.format("Book with %s isbn was not found", isbn)));
    }


    @Cacheable(value = "singleBook", key = "#title")
    public ResponseEntity<Book> findByTitle(String title){
        return ResponseEntity.ok(bookRepository.findByTitle(title).orElseThrow(
                () -> new BookNotFoundException(String.format("Book with %s title was not found", title))));
    }


}
