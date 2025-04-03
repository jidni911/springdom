package com.jidnivai.springdom.dto;


import com.jidnivai.springdom.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class JwtResponse {

    private User user;
    private String jwtToken;

    

    public JwtResponse(String jwtToken) {
        this.jwtToken = jwtToken;
    }

   
}
