package com.example.LibraryManagement.service;

import com.example.LibraryManagement.appUtils.AppUtils;
import com.example.LibraryManagement.model.Otp;
import com.example.LibraryManagement.repository.OtpRepository;
import com.example.LibraryManagement.request.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpService {


    @Autowired
    private final OtpRepository otpRepository;

    @Autowired
    private  final EmailService emailService;

    public Response otpManager(OtpRequest otpRequest){
        String otp= AppUtils.otpGenerator();

        otpRepository.save(Otp.builder()
                        .otp(otp)
                        .email(otpRequest.getEmail())
                        .expirationTime(LocalDateTime.now().plusMinutes(2))
                .build());
        emailService.sendEmail(EmailDetails.builder()
                        .emailSubject("YOU REQUESTED FOR OTP")
                        .emailBody("Your otp is: "+otp+" use it before expirat")
                        .emailRecipient(otpRequest.getEmail())
                .build());

        return Response.builder()
                .statusCode(200)
                .responseMessage("Successfully sent, check your email")
                .build();
    }


    public Response validateOtp(OtpValidationRequest otpValidationRequest){
        Otp otp=otpRepository.findByEmail(otpValidationRequest.getEmail());
        log.info("Email: ", otpValidationRequest.getEmail());
        if (otp==null){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Otp can't be empty")
                    .build();
        }
        if (otp.getExpirationTime().isBefore(LocalDateTime.now())){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Expired Otp")
                    .build();
        }

        if (!otp.getOtp().equals(otpValidationRequest.getOtp())){
            return Response.builder()
                    .statusCode(400)
                    .responseMessage("Provide a valid Otp")
                    .build();
        }


        return Response.builder()
                .statusCode(200)
                .responseMessage("Successful")
                .otpResponse(OtpResponse.builder()
                        .isOtpValid(true)
                        .build())
                .build();
    }


}
