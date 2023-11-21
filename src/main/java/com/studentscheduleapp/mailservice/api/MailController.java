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
import java.util.logging.Logger;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class MailController {

    @Autowired
    private MailService mailService;
    @PostMapping("send")
    public ResponseEntity<Void> send(@RequestBody SendMailRequest sendMailRequest) {
        if(sendMailRequest.getEmail() == null || sendMailRequest.getEmail().isEmpty()) {
            Logger.getGlobal().info("bad request: " + sendMailRequest.getEmail() + " is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            mailService.send(sendMailRequest);
            Logger.getGlobal().info("send email to " + sendMailRequest.getEmail() + " success");
            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            Logger.getGlobal().info("send email to " + sendMailRequest.getEmail() + " failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }catch (Exception e) {
            Logger.getGlobal().info("send email to " + sendMailRequest.getEmail() + " failed: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}