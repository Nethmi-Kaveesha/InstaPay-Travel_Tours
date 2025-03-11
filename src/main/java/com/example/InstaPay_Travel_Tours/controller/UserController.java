package com.example.InstaPay_Travel_Tours.controller;

import com.example.InstaPay_Travel_Tours.dto.AuthDTO;
import com.example.InstaPay_Travel_Tours.dto.ResponseDTO;
import com.example.InstaPay_Travel_Tours.dto.UserDTO;
import com.example.InstaPay_Travel_Tours.service.UserService;
import com.example.InstaPay_Travel_Tours.util.JwtUtil;
import com.example.InstaPay_Travel_Tours.util.ResponseUtil;
import com.example.InstaPay_Travel_Tours.util.VarList;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private final UserService userService;
    private final JwtUtil jwtUtil;

    // Constructor injection
    public UserController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<ResponseDTO> registerUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            int res = userService.saveUser(userDTO);
            switch (res) {
                case VarList.Created -> {
                    String token = jwtUtil.generateToken(userDTO);
                    AuthDTO authDTO = new AuthDTO();
                    authDTO.setEmail(userDTO.getEmail());
                    authDTO.setToken(token);
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(VarList.Created, "Success", authDTO));
                }
                case VarList.Not_Acceptable -> {
                    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                            .body(new ResponseDTO(VarList.Not_Acceptable, "Email Already Used", null));
                }
                default -> {
                    return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(new ResponseDTO(VarList.Bad_Gateway, "Error", null));
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, e.getMessage(), null));
        }
    }
    @PostMapping(value = "save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil saveUser(@RequestBody UserDTO userDTO) { // Change to saveUser
        userService.addUser(userDTO); // Change to addUser
        return new ResponseUtil(201, "User Saved", null);
    }

    @GetMapping("getAll")
    public List<UserDTO> getAllUsers() { // Change to getAllUsers
        return userService.getAllUsers(); // Change to getAllUsers
    }

    @PutMapping(value = "update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseUtil updateUser(@RequestBody UserDTO userDTO) { // Change to updateUser
        userService.updateUser(userDTO); // Change to updateUser
        return new ResponseUtil(200, "User Updated", null);
    }

    @DeleteMapping("delete/{uid}") // Change to uid for UUID
    public ResponseUtil deleteUser(@PathVariable("uid") String uid) { // Change to uid
        userService.deleteUser(UUID.fromString(uid)); // Change to UUID handling
        return new ResponseUtil(200, "User deleted", null);
    }


}
