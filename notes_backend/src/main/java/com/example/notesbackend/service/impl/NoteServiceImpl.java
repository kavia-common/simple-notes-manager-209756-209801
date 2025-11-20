package com.example.notesbackend.service.impl;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.exception.NotFoundException;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.repository.NoteRepository;
import com.example.notesbackend.service.NoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of NoteService using JPA repository.
 */
@Service
@Transactional
public class NoteServiceImpl implements NoteService {

    private final NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Note create(NoteRequest request) {
        Note note = new Note(request.getTitle(), request.getContent());
        return repository.save(note);
    }

    @Override
    public Note update(Long id, NoteRequest request) {
        Note note = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
        note.setTitle(request.getTitle());
        note.setContent(request.getContent());
        return repository.save(note);
    }

    @Override
    @Transactional(readOnly = true)
    public Note getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Note with id " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Note> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Note with id " + id + " not found");
        }
        repository.deleteById(id);
    }

    @Override
    public List<Note> seedSampleData(int count) {
        List<Note> created = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            Note n = new Note("Sample Note " + i, "This is the content for sample note " + i + ".");
            created.add(repository.save(n));
        }
        return created;
    }
}
