package com.example.LibraryManagement;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EnableCaching
@SpringBootApplication
//@ComponentScan(basePackages = "com/example/LibraryManagement/model/BorrowedBooks.isbn")
public class LibraryManagementApplication {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }


	public static void main(String[] args) {
		SpringApplication.run(LibraryManagementApplication.class, args);
	}

}
