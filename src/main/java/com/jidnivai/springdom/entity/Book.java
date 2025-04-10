package com.jidnivai.springdom.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private LocalDateTime publishedDate;
    private String category;
    private String language;
    private Integer totalCopies;
    private Integer availableCopies;
    private String shelfLocation;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String imageUrl;
}

