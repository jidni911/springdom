package com.jidnivai.springdom.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document
@Getter
@Setter
@ToString
public class UserDocument {

    @Id
    private String id;

    private String username;
    private String email;
    private int age;


}
