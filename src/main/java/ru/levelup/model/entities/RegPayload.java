package ru.levelup.model.entities;

public class RegPayload {

    private String email;

    private String pwdHash;

    private String name;

    public RegPayload(String email, String pwdHash, String name) {
        this.email = email;
        this.pwdHash = pwdHash;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public String getName() {
        return name;
    }
}
