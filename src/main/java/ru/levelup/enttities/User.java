package ru.levelup.enttities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Entity
@org.mongodb.morphia.annotations.Entity("users")
@Table(name = "users")

public class User implements BaseEntity<String> {

    @Id
    @org.mongodb.morphia.annotations.Id
    @Expose
    private String id;
    @Column(name = "name")
    @Expose
    private String name;
    @Column(name = "email")
    @Expose
    private String email;
    @Column(name = "pwdHash")
    private String pwdHash;
    @Column(name = "token")
    private String token;

    public User() {
    }

    public User(String id,String name, String email, String pwdHash, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.pwdHash = pwdHash;
        this.token = token;
    }

    public String getId() {
        return id;
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

    public String getToken() {
        return token;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

