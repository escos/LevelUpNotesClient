package ru.levelup.model;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.access.BootstrapException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.enttities.AccessRight;
import ru.levelup.enttities.Note;
import ru.levelup.enttities.User;
import ru.levelup.model.entities.*;


import java.util.List;

@Service("api")
public class ClientApi implements Api {
    private WSClient wsClient;
    private String token;

    @Autowired
    public ClientApi(WSClient wsClient) {
        this.wsClient = wsClient;
    }

    @Override
    public void authorize(String email, String pwdHash,
                          Callback<String> onSuccess, Callback<String> onError) {
        AuthPayload payload = new AuthPayload(email, pwdHash);
        wsClient.sendRequest("authorize", payload, null,
                new TypeToken<ResponseContainer<String>>() {
                }.getType(), new Callback<String>() {
                    @Override
                    public void call(String result) {
                        token = result;
                        onSuccess.call(result);
                    }
                }, onError);
    }

    @Override
    public void registration(String name, String email, String pwdHash,
                             Callback<String> onSuccess, Callback<String> onError) {
        RegPayload payload = new RegPayload(email, pwdHash, name);
        wsClient.sendRequest("registration", payload, null,
                new TypeToken<ResponseContainer<String>>() {
                }.getType(), new Callback<String>() {
                    @Override
                    public void call(String result) {
                        token = result;
                        onSuccess.call(result);
                    }
                }, onError);
    }

    @Override
    public void getUsers(Callback<List<User>> onSuccess, Callback<String> onError) {
        wsClient.sendRequest("getUsers", null, token,new TypeToken<ResponseContainer<List<User>>>() {
        }.getType(), onSuccess, onError);
    }

    @Override
    public void createNote( String tittle, String body, Callback<Note> onSuccess, Callback<String> onError) {
        AddNotePayload payload = new AddNotePayload(tittle, body);
        wsClient.sendRequest("addNote", payload, token,
                new TypeToken<ResponseContainer<Note>>() {
                }.getType(), onSuccess, onError);
    }

    @Override
    public void editNote( String noteId, String tittle, String body,
                          Callback<Note> onSuccess, Callback<String> onError) {
        EditNotePayload payload = new EditNotePayload(noteId, tittle, body);
        wsClient.sendRequest("editNote", payload, token,
                new TypeToken<ResponseContainer<Note>>() {
                }.getType(), onSuccess, onError);
    }

    @Override
    public void deleteNote( String noteId, Callback<Note> onSuccess, Callback<String> onError) {
        DeleteNotePayload payload = new DeleteNotePayload(noteId);
        wsClient.sendRequest("deleteNote", payload, token,new TypeToken<ResponseContainer<Note>>() {
        }.getType(), onSuccess, onError);
    }

    @Override
    public void getNotes( Callback<List<Note>> onSuccess, Callback<String> onError) {
        wsClient.sendRequest("getNotes",null, token,new TypeToken<ResponseContainer<List<Note>>>() {
        }.getType(), onSuccess, onError);
    }

    @Override
    public void addAccessRight(String noteId, String userId, int mode,
                               Callback<Boolean> onSuccess, Callback<String> onError) {
        AddAccessRightPayload payload = new AddAccessRightPayload(noteId, userId, mode);
        wsClient.sendRequest("addAccessRight", payload, token,
                new TypeToken<ResponseContainer<Boolean>>() {
                }.getType(), onSuccess, onError);
    }

    @Override
    public void removeAccessRight(String noteId, String email,
                                  Callback<Boolean> onSuccess, Callback<String> onError) {
        RemoveAccessRightPayload payload = new RemoveAccessRightPayload(noteId, email);
        wsClient.sendRequest("removeAccessRight", payload, token,
                new TypeToken<ResponseContainer<Boolean>>() {
                }.getType(), onSuccess, onError);
    }
}