package com.jidnivai.springdom.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class ImageData {
    @Id
    private Long id;
    private String name;
    private String type;
    private byte[] data;
    private String url;
    @ManyToOne
    private User owner;
}
