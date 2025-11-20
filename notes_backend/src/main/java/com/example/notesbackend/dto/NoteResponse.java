package com.example.notesbackend.dto;

import java.time.Instant;

/**
 * Response DTO for a note resource.
 */
public class NoteResponse {
    private Long id;
    private String title;
    private String content;
    private Instant createdAt;
    private Instant updatedAt;

    public NoteResponse() {}

    public NoteResponse(Long id, String title, String content, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // PUBLIC_INTERFACE
    public Long getId() {
        /** Note ID. */
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // PUBLIC_INTERFACE
    public String getTitle() {
        /** Note title. */
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // PUBLIC_INTERFACE
    public String getContent() {
        /** Note content. */
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    // PUBLIC_INTERFACE
    public Instant getCreatedAt() {
        /** Creation timestamp. */
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    // PUBLIC_INTERFACE
    public Instant getUpdatedAt() {
        /** Last update timestamp. */
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
