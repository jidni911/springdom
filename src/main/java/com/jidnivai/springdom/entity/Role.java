package com.jidnivai.springdom.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Role {
    @Id
    private String name;
    // private String description;
    // @JsonIgnore
    // @ToString.Exclude
    // @ManyToMany(mappedBy = "roles")
    // private List<User> users;
}
