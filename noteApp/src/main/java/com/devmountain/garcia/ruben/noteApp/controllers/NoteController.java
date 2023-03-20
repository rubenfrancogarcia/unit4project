package com.devmountain.garcia.ruben.noteApp.controllers;

import com.devmountain.garcia.ruben.noteApp.dto.NoteDto;
import com.devmountain.garcia.ruben.noteApp.entities.Note;
import com.devmountain.garcia.ruben.noteApp.entities.User;
import com.devmountain.garcia.ruben.noteApp.repositories.NoteRepository;
import com.devmountain.garcia.ruben.noteApp.repositories.UserRepository;
import com.devmountain.garcia.ruben.noteApp.services.NoteServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/notes")
public class NoteController {
    @Autowired
    private NoteServiceImpl noteService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/findAllNotesBy/{userId}")
    public List<NoteDto> findAllNotesByUserId(@PathVariable Long userId){
        return noteService.findAllNotesByUser(userId);
    }

    @PostMapping("/addNote")
    public void addNote(@RequestBody NoteDto noteDto, @RequestParam Long userId){
        noteService.addNote(noteDto, userId);
    }

    @DeleteMapping("/deleteNote")
    public void deleteNote(@RequestParam Long noteId){
        noteService.deleteNote(noteId);
    }

    @PutMapping("/updateNote")
    public void updateNote(@RequestBody String body, @RequestParam Long noteId){
        noteService.updateNote(body, noteId);
    }

    @GetMapping("/findNoteById")
    public NoteDto findNoteById(@RequestParam Long noteId){
        Optional<NoteDto> result = noteService.findNoteById(noteId);
        return result.orElse(null);
    }
}
