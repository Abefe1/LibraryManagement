package com.example.LibraryManagement.controller;

import com.example.LibraryManagement.request.OtpRequest;
import com.example.LibraryManagement.request.OtpValidationRequest;
import com.example.LibraryManagement.request.Response;
import com.example.LibraryManagement.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/otp")
@RequiredArgsConstructor
public class OtpController {

    private  final OtpService otpService;

    @PostMapping("/sendOtp")
    public Response sendOtp(@RequestBody OtpRequest otpRequest){
        return otpService.otpManager(otpRequest);
    }

    @PostMapping("/validateOtp")
    public Response validateOtp(@RequestBody OtpValidationRequest otpValidationRequest){
        return otpService.validateOtp(otpValidationRequest);
    }



}
