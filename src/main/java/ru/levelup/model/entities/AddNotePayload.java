package ru.levelup.model.entities;

public class AddNotePayload {

    private String tittle;

    private String body;

    public AddNotePayload(String tittle, String body) {
        this.tittle = tittle;
        this.body = body;
    }
}
