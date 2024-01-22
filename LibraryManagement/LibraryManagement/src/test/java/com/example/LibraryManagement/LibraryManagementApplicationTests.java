package com.example.LibraryManagement;

import com.example.LibraryManagement.model.Book;
import com.example.LibraryManagement.repository.BookRepository;
import com.example.LibraryManagement.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class LibraryManagementApplicationTests {
//
//	@MockBean
//	private BookRepository bookRepository;
//
//	@Autowired
//	private BookService bookService;
//
//	@Test
//	void contextLoads() {
//
//
//	}
//
//	@Test
//	public  void getBooks(){
//		when(bookRepository.findAll()).thenReturn(
//				Stream.of(
//						new Book("No gree", "Nigerian", "98725979", "2024"),
//						new Book("Love", "Nigerio;asos", "98725979", "2024")
//		).collect(Collectors.toList()));
//
//		assertEquals(2, bookService.getAllBooks().getBody().size());
//	}

}
