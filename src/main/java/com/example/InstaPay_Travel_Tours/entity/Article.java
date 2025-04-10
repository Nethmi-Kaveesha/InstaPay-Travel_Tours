package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Article {

    @Id
    private Long id;
    private String title;
    private String content;
    private String author;
    private String createdDate;


}
