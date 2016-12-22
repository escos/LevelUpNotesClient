package ru.levelup.enttities;

import com.google.gson.annotations.Expose;

import javax.persistence.*;

@Embeddable

public class AccessRight {

    @Column(name = "Note_id")
    private String noteId;

    @Column(name = "mode")
    @Expose
    private int mode;

    @Column(name = "userId")
    @Expose
    private String userId;

    public AccessRight() {
    }

    public AccessRight( int mode, String userId){
        this.mode = mode;
        this.userId = userId;
    }

    public int getMode() {
        return mode;
    }

    public String getUserId() {
        return userId;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
