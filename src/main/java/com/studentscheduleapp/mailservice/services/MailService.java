package com.studentscheduleapp.mailservice.services;

import com.studentscheduleapp.mailservice.models.api.SendMailRequest;
import com.studentscheduleapp.mailservice.properties.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    private MailProperties mailProperties;

    public void send(SendMailRequest sendMailRequest) throws MessagingException {
        final Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", mailProperties.getProtocol());
        properties.setProperty("mail.smtps.auth", mailProperties.getAuth());
        properties.setProperty("mail.smtps.host", mailProperties.getHost());
        properties.setProperty("mail.smtps.user", mailProperties.getUser());
        properties.setProperty("mail.smtps.password", mailProperties.getPassword());
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        String from = properties.getProperty("mail.smtps.user");
        message.setFrom(new InternetAddress(from.substring(0, from.indexOf("@"))));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(sendMailRequest.getEmail()));
        message.setSubject(sendMailRequest.getTitle() == null ? "no title" : sendMailRequest.getTitle());
        message.setText(sendMailRequest.getBody() == null ? "no body" : sendMailRequest.getBody());

        Transport tr = session.getTransport();
        tr.connect(null, properties.getProperty("mail.smtps.password"));
        tr.sendMessage(message, message.getAllRecipients());
        tr.close();
    }

}

