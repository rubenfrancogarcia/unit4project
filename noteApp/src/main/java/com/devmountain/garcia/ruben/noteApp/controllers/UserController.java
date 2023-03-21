package com.devmountain.garcia.ruben.noteApp.controllers;

import com.devmountain.garcia.ruben.noteApp.dto.UserDto;
import com.devmountain.garcia.ruben.noteApp.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public List<String> addUser(@RequestBody UserDto userDto){
        String passHash = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(userDto.getPassword().toLowerCase());
        userDto.setPassword(passHash);
        if(userService.ifUserExists(userDto.getUsername().toLowerCase()).equals("yes")){
            List<String> list = new ArrayList<>();
            list.add("user exists already");
            return list;
        }else{
            return userService.addUser(userDto);
        }
    }

    @PostMapping("/login")
    public List<String> userLogin(@RequestBody UserDto userDto){
        return userService.userLogin(userDto);
    }
}
