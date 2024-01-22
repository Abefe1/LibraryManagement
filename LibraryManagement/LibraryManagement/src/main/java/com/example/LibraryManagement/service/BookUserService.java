package com.example.LibraryManagement.service;

import com.example.LibraryManagement.exceptions.BookNotFoundException;
import com.example.LibraryManagement.exceptions.BookUserNotFoundException;
//import com.example.LibraryManagement.model.Book;
import com.example.LibraryManagement.model.BookUser;
import com.example.LibraryManagement.model.BorrowedBooks;
import com.example.LibraryManagement.repository.BookRepository;
import com.example.LibraryManagement.repository.BorrowedBooksRepository;
import com.example.LibraryManagement.repository.UserRepository;
import com.example.LibraryManagement.request.Response;
import com.example.LibraryManagement.request.UserInfo;
import com.example.LibraryManagement.request.UserLoginRequest;
import com.example.LibraryManagement.request.UserSignUpRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final BookRepository bookRepository;

    @Autowired
    private final BorrowedBooksRepository borrowedBooksRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;




//    public BookUserService(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    public ResponseEntity<Response> signup(UserSignUpRequest userSignUpRequest) {

        //first check if user already exist
        if (userRepository.findUserByEmail(userSignUpRequest.getEmail()).isPresent()){
            return  ResponseEntity.badRequest().body(
                    Response.builder()
                            .statusCode(400)
                            .responseMessage("Account already exist")
                            .build()
            );
        }
        BookUser bookUser=BookUser.builder()
                .email(userSignUpRequest.getEmail())
                .password(passwordEncoder.encode(userSignUpRequest.getPassword()))
                .age(userSignUpRequest.getAge())
                .address(userSignUpRequest.getAddress())
                .fullName(userSignUpRequest.getFullName())
                .build();
        BookUser savedUser=userRepository.save(bookUser);
        return ResponseEntity.ok(Response.builder()
                .statusCode(200)
                .responseMessage("Account succesfully created")
                .userInfo(modelMapper.map(savedUser, UserInfo.class))
                .build());
    }


    public ResponseEntity<Response> login(UserLoginRequest userLoginRequest) {
//
        BookUser user= userRepository.findUserByEmail(userLoginRequest.getEmail()).orElseThrow(()->
                new BookUserNotFoundException("User with the email not found"));
        String requestEmail=userLoginRequest.getEmail();
        String requestPassword=userLoginRequest.getPassword();

        if(requestEmail==null || requestPassword==null){
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .statusCode(400)
                            .responseMessage("Empty field not accepted")
                            .build()
            );
        }
        if (userRepository.findUserByEmail(userLoginRequest.getEmail()).isPresent() &&
                !requestPassword.equals(user.getPassword())) {
            return ResponseEntity.badRequest().body(
                    (Response.builder()
                            .statusCode(400)
                            .responseMessage("Invalid! provide correct password")
                            .build()));
        }
        if (userRepository.findUserByEmail(userLoginRequest.getEmail()).isPresent() &&
                requestPassword.equals(user.getPassword())) {
            return ResponseEntity.ok(
                    (Response.builder()
                            .statusCode(200)
                            .responseMessage("Successful")
                            .build()));
        }
        return ResponseEntity.ok(Response.builder()
                        .statusCode(200)
                        .responseMessage("Welcome")
                .build());
    }

    public Response resetPassword() {

        return null;
    }


    public Response changePassword() {

        return null;
    }





    @CacheEvict(value = "allUsers", allEntries = true)
    public ResponseEntity<BookUser> saveBookUser(BookUser bookUser){
        return ResponseEntity.ok(userRepository.save(bookUser));
    }


    @Cacheable("allUsers")
    public ResponseEntity<List<BookUser>> getAllUsers(){
        return ResponseEntity.ok(userRepository.findAll());
    }



    @CacheEvict(value = {"allUser", "singleUser"}, allEntries = true)
    public ResponseEntity<String> deleteBookUser(int id){
        userRepository.deleteById(id);
            return new ResponseEntity<>("User deleted", HttpStatus.OK) ;

    }


    @CacheEvict(value = {"allUser", "singleUser"}, key = "#id")
    public BookUser updateBookUser(int id, BookUser bookUser){
        BookUser updatedUser= userRepository.findById(id).orElseThrow(
                () -> new BookUserNotFoundException(String.format("BookUser with %d title was not found", id))
        );

        assert updatedUser !=null;
        updatedUser.setFullName(bookUser.getFullName());
        updatedUser.setAddress(bookUser.getAddress());
        updatedUser.setEmail(bookUser.getEmail());
        updatedUser.setAge(bookUser.getAge());

        return userRepository.save(updatedUser);
    }

    @Cacheable(value = "singleUser", key = "#id")
    public ResponseEntity<BookUser> findUserById(int id){
       return ResponseEntity.ok(userRepository.findById(id).orElseThrow(
               () -> new BookUserNotFoundException(String.format("BookUser with %d title was not found", id))
       ));
    }

    @Cacheable(value = "singleUser", key = "#fullName")
    public ResponseEntity<BookUser> findByFullName(String fullName){
        return ResponseEntity.ok(userRepository.findUserByFullName(fullName).orElseThrow(
                () -> new BookUserNotFoundException(String.format("BookUser with %s fullname was not found", fullName))
        ));
    }


    @Cacheable(value = "singleUser", key = "#email")
    public ResponseEntity<BookUser> findUserByEmail(String email){
        return ResponseEntity.ok(userRepository.findUserByEmail(email).orElseThrow(
                () -> new BookUserNotFoundException(String.format("BookUser with %s email was not found", email))
        ));
    }

//    @CacheEvict(value = {"allUser", "singleUser"}, key = "#id")
//    public BorrowedBooks addBorrowedBook(int id, String isbn, BorrowedBooks borrowedBooks, BookUser bookUser ){
//        BookUser borrower= userRepository.findById(id).orElseThrow(
//                () -> new BookUserNotFoundException(String.format("BookUser with %d title was not found", id))
//        );
//        BorrowedBooks borrowed= .findById(id).orElseThrow(
//                () -> new BookUserNotFoundException(String.format("BookUser with %d title was not found", id))
//        );
//
//        assert borrower !=null;
//
//        return userRepository.save(updatedUser);
//    }

}
