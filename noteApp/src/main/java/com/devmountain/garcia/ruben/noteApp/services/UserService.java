package com.devmountain.garcia.ruben.noteApp.services;

import com.devmountain.garcia.ruben.noteApp.dto.UserDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    List<String> addUser(UserDto userDto);

    List<String> userLogin(UserDto userDto);
}
