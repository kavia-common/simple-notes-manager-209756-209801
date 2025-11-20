package com.example.notesbackend.service;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.model.Note;

import java.util.List;

/**
 * Service interface for managing notes.
 */
public interface NoteService {

    // PUBLIC_INTERFACE
    Note create(NoteRequest request);

    // PUBLIC_INTERFACE
    Note update(Long id, NoteRequest request);

    // PUBLIC_INTERFACE
    Note getById(Long id);

    // PUBLIC_INTERFACE
    List<Note> getAll();

    // PUBLIC_INTERFACE
    void delete(Long id);

    // PUBLIC_INTERFACE
    List<Note> seedSampleData(int count);
}
