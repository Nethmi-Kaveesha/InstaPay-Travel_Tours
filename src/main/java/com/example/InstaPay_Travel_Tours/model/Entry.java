package com.example.InstaPay_Travel_Tours.model;

import jakarta.persistence.*;


@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private double amount;

    @Enumerated(EnumType.STRING)
    private EntryType type;

    public Entry() {}

    public Entry(String description, double amount, EntryType type) {
        this.description = description;
        this.amount = amount;
        this.type = type;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public EntryType getType() {
        return type;
    }

    public void setType(EntryType type) {
        this.type = type;
    }
}
