package com.example.InstaPay_Travel_Tours.service;


import com.example.InstaPay_Travel_Tours.dto.UserDTO;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);
}