package ru.levelup.model.entities;

public class RemoveAccessRightPayload {

    private String noteId;

    private String email;

    public RemoveAccessRightPayload(String noteId, String email) {
        this.noteId = noteId;
        this.email = email;
    }
}
