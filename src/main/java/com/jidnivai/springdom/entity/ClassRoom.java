package com.jidnivai.springdom.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ClassRoom {
    
    @Id
    private Long id;

    private String name;

    @ManyToMany
    private List<User> students;

    @ManyToMany
    private List<User> invitedStudents;

    @ManyToMany
    private List<User> requestingStudents;
}

