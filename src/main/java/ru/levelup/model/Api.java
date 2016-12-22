package ru.levelup.model;

import ru.levelup.enttities.AccessRight;
import ru.levelup.enttities.Note;
import ru.levelup.enttities.User;

import java.util.List;
import java.util.jar.Attributes;

public interface Api {

    void authorize(String email, String pwdHash,
                   Callback<String> onSuccess, Callback<String> onError);

    void registration(String name, String email, String pwdHash,
                      Callback<String> onSuccess, Callback<String> onError);

    void getUsers(Callback<List<User>> onSuccess, Callback<String> onError);

    void createNote(String tittle, String body,
                    Callback<Note> onSuccess, Callback<String> onError);

    void editNote(String noteId, String tittle, String body,
                  Callback<Note> onSuccess, Callback<String> onError);

    void deleteNote(String noteId, Callback<Note> onSuccess, Callback<String> onError);

    void getNotes(Callback<List<Note>> onSuccess, Callback<String> onError);

    void addAccessRight(String noteId, String userId, int mode,
                        Callback<Boolean> onSuccess, Callback<String> onError);

    void removeAccessRight(String noteId, String email, Callback<Boolean> onSuccess, Callback<String> onError);
}
