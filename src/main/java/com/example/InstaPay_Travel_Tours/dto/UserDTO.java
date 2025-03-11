package com.example.InstaPay_Travel_Tours.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private UUID uid; // Added uid for consistency with entity
    private String email;
    private String password;
    private String name;
    private String role;
    private String phoneNumber; // Added phone number field
    private String gender; // Added gender field
}
