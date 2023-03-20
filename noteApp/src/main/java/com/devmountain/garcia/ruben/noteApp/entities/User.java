package com.devmountain.garcia.ruben.noteApp.entities;

import com.devmountain.garcia.ruben.noteApp.dto.UserDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    @Column
    private String password;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Note> noteSet = new HashSet<>();

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(UserDto userDto){
        if(userDto.getUsername() != null){
            this.username = userDto.getUsername();
        }
    if(userDto.getPassword() != null){
        this.password = userDto.getPassword();
    }
    if(userDto.getId() != null){
        this.id = userDto.getId();
    }
    }


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
