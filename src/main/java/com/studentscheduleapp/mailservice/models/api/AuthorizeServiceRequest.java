package com.studentscheduleapp.mailservice.models.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizeServiceRequest {

    private String serviceToken;

}
