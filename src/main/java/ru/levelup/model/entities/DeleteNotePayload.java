package ru.levelup.model.entities;

public class DeleteNotePayload {

    private String noteId;

    public DeleteNotePayload(String noteId) {
        this.noteId = noteId;
    }
}
