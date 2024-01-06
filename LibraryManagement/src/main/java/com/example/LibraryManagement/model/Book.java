package com.example.LibraryManagement.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import org.springframework.data.jpa.repository.Query;
//import com.example.LibraryManagement.repository.BookRepository;


@Getter
@Table(name = "books")
@Entity
public class Book {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull(message="Title can not be null")
    @NotBlank(message="Title can not be blank")
    private String title;

    @NotNull(message="Author can not be null")
    @NotBlank(message="Author can not be blank")
    private String author;


    @Pattern(regexp = "[0-9]{5}-[0-9]{5}", message="follow the 10 digit isbn method")
    @NotNull(message="Isbn can not be null")
    @NotBlank(message="Isbn can not be blank")
    @Column(name = "isbn", unique = true)
    private String isbn;


    @NotNull(message="publication Year can not be null")
    @NotBlank(message="publication Year can not be blank")
    @Column(name = "publication_year")
    private String publicationYear;

    public Book(String title, String author, String isbn, String publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
    }

    public Book() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                '}';
    }
}
