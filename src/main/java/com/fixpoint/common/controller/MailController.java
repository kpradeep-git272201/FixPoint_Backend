package com.fixpoint.common.controller;

import com.fixpoint.common.dtos.RequestMail;
import com.fixpoint.common.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/email")
public class MailController {
    @Autowired
    private EmailService emailService;
    @PostMapping("/send-mail")
    public ResponseEntity<Object> sendMail(@RequestBody RequestMail requestMail) {
        if(requestMail.getToEmail().isBlank()||requestMail.getSubject().isBlank()||requestMail.getBody().isBlank()){
            return new ResponseEntity<>("Invalid email body", HttpStatus.BAD_REQUEST);
        }
        emailService.sendSimpleEmail(requestMail.getToEmail(), requestMail.getSubject(), requestMail.getBody());
        return new ResponseEntity<>("Email sent successfully!", HttpStatus.OK);
    }
}
