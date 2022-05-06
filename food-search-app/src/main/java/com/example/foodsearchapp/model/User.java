package com.example.foodsearchapp.model;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String email;
    private String pass;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_status",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "status_id"))
    private Set<Status> statuses = new HashSet<>();

    public User(String username, String email, String pass) {
        this.username = username;
        this.email = email;
        this.pass = pass;
    }

    public User() {
    }
}
