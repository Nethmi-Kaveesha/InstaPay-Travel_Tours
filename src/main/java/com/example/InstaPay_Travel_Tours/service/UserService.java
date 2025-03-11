package com.example.InstaPay_Travel_Tours.service;


import com.example.InstaPay_Travel_Tours.dto.UserDTO;

import java.util.List;
import java.util.UUID;

public interface UserService {
    int saveUser(UserDTO userDTO);
    UserDTO searchUser(String username);

    void addUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    void updateUser(UserDTO userDTO);

    void deleteUser(UUID uid);
}