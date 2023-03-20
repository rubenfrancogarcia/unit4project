package com.devmountain.garcia.ruben.noteApp.dto;

import com.devmountain.garcia.ruben.noteApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto implements Serializable {
    private Long id;

    private String username;

    private String password;

    private Set<NoteDto> noteDtoSet = new HashSet<>();

    public UserDto(User user){
        if(user.getId() != null){
            this.id = user.getId();
        }

        if(user.getUsername() != null){
            this.username = user.getUsername();
        }

        if(user.getPassword() != null){
            this.password = user.getPassword();
        }
    }
}
