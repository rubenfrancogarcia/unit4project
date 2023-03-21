package com.devmountain.garcia.ruben.noteApp.services;

import com.devmountain.garcia.ruben.noteApp.dto.NoteDto;
import com.devmountain.garcia.ruben.noteApp.entities.Note;
import com.devmountain.garcia.ruben.noteApp.repositories.NoteRepository;
import com.devmountain.garcia.ruben.noteApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.devmountain.garcia.ruben.noteApp.entities.User;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public List<NoteDto> findAllNotesByUser(String userName) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            List<Note> noteList = noteRepository.findAllByUserEquals(userOptional.get());
            return noteList.stream().map(NoteDto::new).collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    @Override
    @Transactional
    public void addNote(NoteDto noteDto, Long userId) {
        Optional<User> user = userRepository.findById(userId);
        Note note = new Note(noteDto);
        if (user.isPresent()) {
            note.setUser(user.get());
        }
        noteRepository.saveAndFlush(note);
    }

    @Override
    @Transactional
    public void deleteNote(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        noteOptional.ifPresent(note -> noteRepository.delete(note));
    }

    @Override
    @Transactional
    public String updateNote(String body, Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        noteOptional.ifPresent(note -> {
            note.setBody(body);
            noteRepository.saveAndFlush(note);
        });
        if(noteOptional.isPresent()){
            return "Updated";
        }else{
            return "no existing note id from input";
        }
    }

    @Override
    @Transactional
    public Optional<NoteDto> findNoteById(Long noteId) {
        Optional<Note> noteOptional = noteRepository.findById(noteId);
        if (noteOptional.isPresent()) {
            return Optional.of(new NoteDto(noteOptional.get()));
        }
        return Optional.empty();
    }
}
