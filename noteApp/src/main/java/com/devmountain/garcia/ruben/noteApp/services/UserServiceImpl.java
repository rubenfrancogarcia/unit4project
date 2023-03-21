package com.devmountain.garcia.ruben.noteApp.services;

import com.devmountain.garcia.ruben.noteApp.dto.NoteDto;
import com.devmountain.garcia.ruben.noteApp.dto.UserDto;
import com.devmountain.garcia.ruben.noteApp.entities.Note;
import com.devmountain.garcia.ruben.noteApp.entities.User;
import com.devmountain.garcia.ruben.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<String> addUser(UserDto userDto){
        List<String> response = new ArrayList<>();
        User user = new User(userDto);
        userRepository.saveAndFlush(user);
        response.add("http://localhost:8080/login.html");
        return response;
    }

    @Override
    public List<String> userLogin(UserDto userDto){
        List<String> response = new ArrayList<>();
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());
        if(userOptional.isPresent()){
            if(passwordEncoder.matches(userDto.getPassword(), userOptional.get().getPassword())){
                response.add("http://localhost:8080/login.html");
                response.add(String.valueOf(userOptional.get().getId()));
            }else{
                response.add("Username or Password is incorrect");
            }
        }else{
            response.add("Username or Password is incorrect");
        }
        return response;
    }

    public String ifUserExists(String username){
        Optional<User> user = userRepository.findByUsername(username);
        if(user.isPresent()){
            return "yes";
        }else{
            return "no";
        }
    }
}
