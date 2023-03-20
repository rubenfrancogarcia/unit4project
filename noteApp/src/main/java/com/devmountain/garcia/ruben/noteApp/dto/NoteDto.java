package com.devmountain.garcia.ruben.noteApp.dto;

import com.devmountain.garcia.ruben.noteApp.entities.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto implements Serializable {
    private Long id;

    private String body;

    private UserDto userDto;

    public NoteDto(Note note){
        if(note.getId() != null){
            this.id = note.getId();
        }
        if(note.getBody() != null){
            this.body = note.getBody();
        }
    }
}
