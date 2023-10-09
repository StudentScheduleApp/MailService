package com.studentscheduleapp.mailservice.api;

import com.studentscheduleapp.mailservice.models.api.SendMailRequest;
import com.studentscheduleapp.mailservice.services.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class MailController {

    @Autowired
    private MailService mailService;
    @PostMapping("send")
    public ResponseEntity<Void> send(@RequestBody SendMailRequest sendMailRequest) {
        if(sendMailRequest.getEmail() == null || sendMailRequest.getEmail().isEmpty())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        try {
            mailService.send(sendMailRequest);
            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}