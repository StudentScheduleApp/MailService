package com.studentscheduleapp.mailservice.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailProperties {
    @Value("${mail.transport.protocol}")
    private String protocol;
    @Value("${mail.smtps.auth}")
    private String auth;
    @Value("${mail.smtps.host}")
    private String host;
    @Value("${mail.smtps.user}")
    private String user;
    @Value("${mail.smtps.password}")
    private String password;
}
