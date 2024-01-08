package com.example.LibraryManagement.repository;

import com.example.LibraryManagement.model.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<BookUser, Integer> {

    Optional<BookUser> findUserByEmail(String email);
    Optional<BookUser> findUserByFullName(String fullName);


}
