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

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    public UserDTO loadUserDetailsByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        return modelMapper.map(user, UserDTO.class);
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public UserDTO searchUser(String username) {
        if (userRepository.existsByEmail(username)) {
            User user = userRepository.findByEmail(username);
            return modelMapper.map(user, UserDTO.class);
        } else {
            return null;
        }
    }

    @Override
    public int saveUser(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            return VarList.Not_Acceptable;
        } else {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            // ✅ Set default role ONLY if none is provided
            if (userDTO.getRole() == null || userDTO.getRole().isEmpty()) {
                userDTO.setRole("USER");
            }

            // Map DTO to Entity and include gender and phoneNumber fields (address removed)
            User user = modelMapper.map(userDTO, User.class);
            user.setGender(userDTO.getGender()); // Ensure gender is mapped properly
            userRepository.save(user);
            return VarList.Created;
        }
    }
    @Override
    public void addUser(UserDTO userDTO) { // Change to addUser
        if (userRepository.existsById(String.valueOf(userDTO.getUid()))) { // Change to check for UID
            throw new RuntimeException("User already exists");
        }
        userRepository.save(modelMapper.map(userDTO, User.class)); // Change to User
    }

    @Override
    public List<UserDTO> getAllUsers() { // Change to getAllUsers
        return modelMapper.map(userRepository.findAll(),
                new TypeToken<List<UserDTO>>() {}.getType()); // Change to UserDTO
    }

    @Override
    public void updateUser(UserDTO userDTO) { // Change to updateUser
        if (userRepository.existsById(String.valueOf(userDTO.getUid()))) { // Change to check for UID
            userRepository.save(modelMapper.map(userDTO, User.class)); // Change to User
        }
        throw new RuntimeException("User does not exist");
    }

    @Override
    public void deleteUser(UUID uid) { // Change to use UUID for deletion
        userRepository.deleteById(String.valueOf(uid)); // Change to UserRepo and UUID
    }

}
