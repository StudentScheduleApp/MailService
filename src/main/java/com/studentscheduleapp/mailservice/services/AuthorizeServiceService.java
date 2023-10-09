package com.studentscheduleapp.mailservice.services;

import com.studentscheduleapp.mailservice.repos.ServiceTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizeServiceService {
    @Autowired
    private ServiceTokenRepository serviceTokenRepository;

    public boolean authorize(String token) throws Exception {
        return serviceTokenRepository.existsByServiceToken(token);
    }

}
