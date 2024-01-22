//package com.example.LibraryManagement.service;
//
//import com.example.LibraryManagement.exceptions.BookUserNotFoundException;
//import com.example.LibraryManagement.model.Book;
//import com.example.LibraryManagement.model.BookUser;
//import com.example.LibraryManagement.repository.BookRepository;
//import com.example.LibraryManagement.repository.BorrowedBooksRepository;
//import com.example.LibraryManagement.repository.UserRepository;
//import lombok.Getter;
//import lombok.RequiredArgsConstructor;
//import lombok.Setter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//
//@Setter
//@Getter
//@RequiredArgsConstructor
//@Service
//public class BorrowedBooks {
//
//    @Autowired
//    private final BookRepository bookRepository;
//
//    @Autowired
//    private BorrowedBooksRepository borrowedBooksRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public BorrowedBooks(BookRepository bookRepository, BorrowedBooksRepository borrowedBooksRepository) {
//        this.bookRepository = bookRepository;
//        this.borrowedBooksRepository = borrowedBooksRepository;
//    }/        BorrowedBooks toBeBorrowed = new BorrowedBooks();
////        assert borrowed != null;
////        assert borrower != null;
////        assert toBeBorrowed == null;
////
////
////        toBeBorrowed.setBorrowerId(borrower.getId());
////        toBeBorrowed.setBorrowedBookAuthor(borrowed.getAuthor());
////        toBeBorrowed.setBorrowedBookIsbn(borrowed.getIsbn());
////        toBeBorrowed.setBorrowedBookTitle(borrowed.getTitle());
////
////        return borrowedBooksRepository.save(toBeBorrowed);
////    }
////}
//
//    public BorrowedBooks() {
//
//    }
//
//    public BorrowedBooks addBorrowedBook(int id, String isbn) {
//        BookUser borrower = userRepository.findById(id).orElseThrow(
//                () -> new BookUserNotFoundException(String.format("BookUser with %d title was not found", id))
//        );
//        Book borrowed = bookRepository.getByIsbn(isbn).orElseThrow(
//                () -> new BookUserNotFoundException(String.format("BookUser with %s title was not found", isbn))
//        );
