package ru.levelup.model.entities;

public class AddAccessRightPayload {

    private String noteId;

    private String email;

    private int mode;

    public AddAccessRightPayload(String noteId, String email, int mode) {
        this.noteId = noteId;
        this.email = email;
        this.mode = mode;
    }

    public String getNoteId() {
        return noteId;
    }

    public String getEmail() {
        return email;
    }

    public int getMode() {
        return mode;
    }
}
