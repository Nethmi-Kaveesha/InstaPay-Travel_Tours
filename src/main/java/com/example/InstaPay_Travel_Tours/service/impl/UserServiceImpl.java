package com.example.InstaPay_Travel_Tours.service.impl;

import com.example.InstaPay_Travel_Tours.dto.UserDTO;
import com.example.InstaPay_Travel_Tours.entity.User;
import com.example.InstaPay_Travel_Tours.repo.UserRepository;
import com.example.InstaPay_Travel_Tours.service.UserService;
import com.example.InstaPay_Travel_Tours.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return modelMapper.map(user, UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        Optional<User> userOptional = userRepository.findByEmail(username);
        return userOptional.map(user -> modelMapper.map(user, UserDTO.class)).orElse(null);
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            // Set default role ONLY if none is provided
            if (userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
                userDTO.setRole("USER");
            }

            // Map DTO to Entity and include gender and phoneNumber fields
            User user = modelMapper.map(userDTO, User.class);
            user.setGender(userDTO.getGender()); // Ensure gender is mapped properly
            userRepository.save(user);
            return VarList.Created;
        }
    }

    @Override
    public void addUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUid())) { // Check using UUID directly
            throw new RuntimeException("User already exists");
        }
        userRepository.save(modelMapper.map(userDTO, User.class)); // Mapping to User entity
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return modelMapper.map(userRepository.findAll(),
                new TypeToken<List<UserDTO>>() {}.getType()); // Map User entities to UserDTO list
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getUid())) { // Check using UUID directly
            userRepository.save(modelMapper.map(userDTO, User.class)); // Mapping to User entity
        } else {
            throw new RuntimeException("User does not exist");
        }
    }


    @Override
    public void deleteUser(UUID uid) { // Directly use UUID for deletion
        userRepository.deleteById(uid); // Use UUID directly
    }

}
