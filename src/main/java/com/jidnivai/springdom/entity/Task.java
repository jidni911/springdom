package com.jidnivai.springdom.entity;

import java.time.LocalDateTime;

import com.jidnivai.springdom.enums.TaskType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String description;
    private boolean isDone;
    private LocalDateTime dateTime;

    @ManyToOne
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;


}

