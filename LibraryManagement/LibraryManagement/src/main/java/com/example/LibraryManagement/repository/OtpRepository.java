package com.example.LibraryManagement.repository;

import com.example.LibraryManagement.model.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
    Otp findByEmail(String email);
}
