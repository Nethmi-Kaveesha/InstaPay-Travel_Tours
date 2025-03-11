package com.example.InstaPay_Travel_Tours.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "systemuser")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uid;

    @Column(unique = true)
    private String email;

    private String password;
    private String name;
    private String role;

    private String phoneNumber; // Added phone number field

    @Column(length = 500) // Optional: Adjust length based on your requirement
    private String address; // Added address field
}
