package com.example.InstaPay_Travel_Tours.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {
    private String email;
    private String password;
    private String name;
    private String role;
    private String phoneNumber; // Added phone number field
}
