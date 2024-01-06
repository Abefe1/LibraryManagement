package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.model.BookUser;
import com.example.LibraryManagement.service.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private BookUserService bookUserService;


    @PostMapping("/user")
    public ResponseEntity<BookUser> saveBookUser(@RequestBody BookUser bookUser){
        bookUserService.saveBookUser(bookUser);
        return ResponseEntity.ok(bookUser);
//        return new ResponseEntity<>(bookUserService.saveBookUser(bookUser), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookUser> updateById(@PathVariable Integer id, @RequestBody BookUser bookUser){
        BookUser updatedUser= bookUserService.findUserById(id).getBody();

        assert updatedUser !=null;

        updatedUser.setAge(bookUser.getAge());
        updatedUser.setAddress(bookUser.getAddress());
        updatedUser.setFullName(bookUser.getFullName());
        updatedUser.setEmail(bookUser.getEmail());

        return bookUserService.saveBookUser(updatedUser);
    }


    @GetMapping("/user/{id}")
    public ResponseEntity<BookUser> findUserById(@PathVariable Integer id){
        return ResponseEntity.ok(bookUserService.findUserById(id).getBody());

    }

    @GetMapping("/users")
    public ResponseEntity<List<BookUser>> getAllUsers(){
        return ResponseEntity.ok(bookUserService.getAllUsers().getBody());

    }

    @GetMapping("/user/{fullName}")
    public ResponseEntity<BookUser> findUserByFullName(@PathVariable String fullName){
        return ResponseEntity.ok(bookUserService.findByFullName(fullName).getBody());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<BookUser> findUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(bookUserService.findUserByEmail(email).getBody());
    }



}
