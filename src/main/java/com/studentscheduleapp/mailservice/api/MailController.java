package com.studentscheduleapp.mailservice.api;

import com.studentscheduleapp.mailservice.models.api.SendMailRequest;
import com.studentscheduleapp.mailservice.services.MailService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@RequiredArgsConstructor
public class MailController {

    private static final Logger log = LogManager.getLogger(MailController.class);
    @Autowired
    private MailService mailService;

    @PostMapping("${mapping.send}")
    public ResponseEntity<Void> send(@RequestBody SendMailRequest sendMailRequest) {
        if (sendMailRequest.getEmail() == null || sendMailRequest.getEmail().isEmpty()) {
            log.warn("bad request: email is null or empty");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        try {
            mailService.send(sendMailRequest);
            log.info("send email to " + sendMailRequest.getEmail() + " success");
            return ResponseEntity.ok().build();
        } catch (MessagingException e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            log.error("send email to " + sendMailRequest.getEmail() + " failed: " + errors);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            StringWriter errors = new StringWriter();
            e.printStackTrace(new PrintWriter(errors));
            log.error("send email to " + sendMailRequest.getEmail() + " failed: " + errors);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}