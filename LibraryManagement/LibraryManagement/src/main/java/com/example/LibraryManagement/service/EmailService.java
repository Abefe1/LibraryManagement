package com.example.LibraryManagement.service;

import com.example.LibraryManagement.request.EmailDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;
    
    public void sendEmail(EmailDetails emailDetails){
        try {
            SimpleMailMessage mailMessage= new SimpleMailMessage();
            mailMessage.setFrom(emailSender);
            mailMessage.setTo(emailDetails.getEmailRecipient());
            mailMessage.setText(emailDetails.getEmailBody());
            mailMessage.setSubject(emailDetails.getEmailSubject());

            javaMailSender.send(mailMessage);
            log.info("Message sent to: {}", emailDetails.getEmailRecipient());
            log.info("Message sent by: {}", emailSender);

        } catch (MailException e) {
            throw new RuntimeException(e);
        }
    }

//    public static void main(String[] args) {
//        EmailService emailService=new EmailService();
//        emailService.sendEmail(EmailDetails.builder()
//                        .emailBody("Testing")
//                        .emailRecipient("lukmonakingbade@gmail.com")
//                        .emailSubject("Practising")
//                .build());
//    }
}
