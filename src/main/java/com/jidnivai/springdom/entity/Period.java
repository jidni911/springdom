package com.jidnivai.springdom.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Entity
public class Period {
    @Id
    private Long id;

    @ManyToOne
    private User taker;

    private LocalDateTime dateTime;

    private String subject;

    @ManyToMany
    private List<ClassRoom> classRooms;

    @ManyToMany
    private List<User> presentStudents;
}
