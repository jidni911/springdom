package com.jidnivai.springdom.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("STUDENT") // Optional: this goes into user_type column
@Getter
@Setter
@ToString(callSuper = true)
public class Student extends User {

    private int classRoll;
    private int registration;
    private String section;
    private int year;
    private String session;
}
