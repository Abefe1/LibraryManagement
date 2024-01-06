package com.example.LibraryManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;


@Getter
@Entity
@Table(name="book_User")
public class BookUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

//
    @NotNull(message="Email can not be null")
    @NotBlank(message="Email can not be blank")
    @Column(unique = true)
    @Email
    private String email;

    @NotNull(message="Fullname can not be null")
    @NotBlank(message="Fullname can not be blank")
    @Length(min = 6, max=15, message= "Full Name should not less than 6 character nor greater than 15 character")
    @Column(name="full_Name")
    private String fullName;

    @NotNull
    @Min(value = 18, message ="You have to be at least 18 to be registered")
    @Max(value = 70, message ="Sorry, maximum age for user is 70")
    private int age;

    @NotNull(message="Address can not be null")
    @NotBlank(message="Address can not be blank")
    private String address;


    @Autowired
    public BookUser( String email, String fullName, int age, String address) {
        this.email = email;
        this.fullName = fullName;
        this.age = age;
        this.address = address;
    }

    public BookUser() {
    }


    public BookUser(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "BookUser{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
