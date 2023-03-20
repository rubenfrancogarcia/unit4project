package com.devmountain.garcia.ruben.noteApp.repositories;

import com.devmountain.garcia.ruben.noteApp.entities.Note;
import com.devmountain.garcia.ruben.noteApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findAllByUserEquals(User user);
}
