package com.studentscheduleapp.mailservice.repos;

import com.studentscheduleapp.mailservice.http.HeaderRequestInterceptor;
import com.studentscheduleapp.mailservice.models.api.AuthorizeServiceRequest;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

@Repository
public class ServiceTokenRepository  {

    @Value("${ip.identityservice}")
    private String identityService;

    @Autowired
    private RestTemplate restTemplate;

    public boolean existsByServiceToken(String serviceToken) throws Exception{
        ResponseEntity<Void> r = restTemplate.postForEntity(identityService + "/api/service/authorize", new AuthorizeServiceRequest(serviceToken), Void.class);
        if(r.getStatusCode().is2xxSuccessful())
            return true;
        if(r.getStatusCode().equals(HttpStatus.NOT_FOUND))
            return false;
        throw new Exception("request to " + identityService + " return code " + r.getStatusCode());
    }
}
