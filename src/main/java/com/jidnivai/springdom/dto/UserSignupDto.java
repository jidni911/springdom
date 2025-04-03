package com.jidnivai.springdom.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupDto {
    private String username;
    private String password;
    private String email;
    private String fullName;
    private String retypePassword;
}
