package com.example.InstaPay_Travel_Tours.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Reviews {

    @Id
    private Long id;
    private String name;
    private String email;
    private int rating;
    private String comment;


    public Reviews() {
    }

    public Reviews(String name, String email, int rating, String comment) {
        this.name = name;
        this.email = email;
        this.rating = rating;
        this.comment = comment;
    }

    // Getters and setters...
}
