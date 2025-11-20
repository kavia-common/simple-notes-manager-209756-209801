package com.example.notesbackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Request DTO for creating/updating a note.
 */
public class NoteRequest {

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must be at most 255 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    public NoteRequest() {}

    public NoteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // PUBLIC_INTERFACE
    public String getTitle() {
        /** Title of the note. */
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // PUBLIC_INTERFACE
    public String getContent() {
        /** Content of the note. */
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
