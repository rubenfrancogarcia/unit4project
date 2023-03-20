package com.devmountain.garcia.ruben.noteApp.entities;

import com.devmountain.garcia.ruben.noteApp.dto.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    private User user;

    public Note(NoteDto noteDto){
        if(noteDto.getBody() != null){
            this.body = noteDto.getBody();
        }
        if(noteDto.getId() != null){
            this.id = noteDto.getId();
        }
        if(noteDto.getUserDto() != null){
            User localUser = new User(noteDto.getUserDto());
            this.user = localUser;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
