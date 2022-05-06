package com.example.foodsearchapp.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EStatus name;

    public Status(EStatus name) {
        this.name = name;
    }

    public Status() {
    }
}
