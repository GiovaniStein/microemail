package com.micro.email.controller;

import com.micro.email.model.EmailEntity;
import com.micro.email.model.dto.EmailDto;
import com.micro.email.service.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping
    public ResponseEntity<List<EmailEntity>> listAll() {
        return ResponseEntity.status(HttpStatus.OK).body(emailService.listAll());
    }

    @PostMapping("/send")
    public ResponseEntity<EmailEntity> sendEmail(@RequestBody @Valid EmailDto emailDto) {
        EmailEntity email = new EmailEntity();
        BeanUtils.copyProperties(emailDto, email);
        emailService.sendEmail(email);
        return ResponseEntity.status(HttpStatus.CREATED).body(email);
    }

}
