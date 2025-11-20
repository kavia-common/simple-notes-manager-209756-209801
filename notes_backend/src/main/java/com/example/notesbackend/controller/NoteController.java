package com.example.notesbackend.controller;

import com.example.notesbackend.dto.NoteRequest;
import com.example.notesbackend.dto.NoteResponse;
import com.example.notesbackend.model.Note;
import com.example.notesbackend.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for Notes CRUD API.
 * Provides endpoints under /api/notes for managing notes.
 */
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@Tag(name = "Notes", description = "CRUD operations for notes")
public class NoteController {

    private final NoteService service;

    public NoteController(NoteService service) {
        this.service = service;
    }

    private static NoteResponse toResponse(Note n) {
        return new NoteResponse(n.getId(), n.getTitle(), n.getContent(), n.getCreatedAt(), n.getUpdatedAt());
    }

    // PUBLIC_INTERFACE
    @GetMapping
    @Operation(summary = "List notes", description = "Returns all notes")
    public List<NoteResponse> getAll() {
        return service.getAll().stream().map(NoteController::toResponse).collect(Collectors.toList());
    }

    // PUBLIC_INTERFACE
    @GetMapping("/{id}")
    @Operation(summary = "Get note", description = "Returns a single note by ID")
    public NoteResponse getById(@PathVariable Long id) {
        return toResponse(service.getById(id));
    }

    // PUBLIC_INTERFACE
    @PostMapping
    @Operation(summary = "Create note", description = "Creates a new note")
    public ResponseEntity<NoteResponse> create(@Valid @RequestBody NoteRequest request) {
        Note created = service.create(request);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(location).body(toResponse(created));
    }

    // PUBLIC_INTERFACE
    @PutMapping("/{id}")
    @Operation(summary = "Update note", description = "Updates an existing note by ID")
    public NoteResponse update(@PathVariable Long id, @Valid @RequestBody NoteRequest request) {
        return toResponse(service.update(id, request));
    }

    // PUBLIC_INTERFACE
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete note", description = "Deletes a note by ID")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // PUBLIC_INTERFACE
    @PostMapping("/seed")
    @Operation(summary = "Seed sample data", description = "Creates a number of sample notes (default 5)")
    public ResponseEntity<List<NoteResponse>> seed(@RequestParam(name = "count", defaultValue = "5") int count) {
        List<NoteResponse> created = service.seedSampleData(Math.max(1, Math.min(count, 50)))
                .stream().map(NoteController::toResponse).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
