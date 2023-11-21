package com.studentscheduleapp.mailservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceService {
    @Value("${service.token}")
    private String serviceToken;

    public boolean authorize(String token){
        return serviceToken.equals(token);
    }
}
