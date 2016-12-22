package ru.levelup.model.entities;

public class EditNotePayload {

    private String noteId;

    private String tittle;

    private String body;

    public EditNotePayload(String noteId, String tittle, String body) {
        this.noteId = noteId;
        this.tittle = tittle;
        this.body = body;
    }

    public void setId(String noteId) {
        this.noteId = noteId;
    }

    public String getId() {
        return noteId;
    }

    public String getTittle() {
        return tittle;
    }

    public String getBody() {
        return body;
    }
}
