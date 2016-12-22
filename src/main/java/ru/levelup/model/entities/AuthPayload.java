package ru.levelup.model.entities;

public class AuthPayload {

    private String email;

    private String pwdHash;

    public AuthPayload(String email, String pwdHash) {
        this.email = email;
        this.pwdHash = pwdHash;
    }

}
