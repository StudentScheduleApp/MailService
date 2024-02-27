package com.studentscheduleapp.mailservice.services;

import com.studentscheduleapp.mailservice.properties.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceService {
    @Autowired
    private GlobalProperties globalProperties;

    public boolean authorize(String token) {
        return globalProperties.getServiceToken().equals(token);
    }
}
