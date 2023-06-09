package com.devmountain.garcia.ruben.noteApp.services;

import com.devmountain.garcia.ruben.noteApp.dto.NoteDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    @Transactional
    List<NoteDto> findAllNotesByUser(String userName);


    @Transactional
    void addNote(NoteDto noteDto, String userName);

    @Transactional
    void deleteNote(Long noteId);

    @Transactional
    String updateNote(String body, Long noteId);

    @Transactional
    Optional<NoteDto> findNoteById(Long noteId);
}
