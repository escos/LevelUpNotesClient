package ru.levelup.presenter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import ru.levelup.enttities.AccessRight;
import ru.levelup.enttities.Note;
import ru.levelup.enttities.User;
import ru.levelup.model.Api;
import ru.levelup.model.Callback;
import ru.levelup.view.View;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

@Component("presenter")
public class ConsolePresenter implements Presenter {
    private View view;
    private Api api;

    @Autowired
    @Override
    public void setView(View view) {
        this.view = view;
        startMenu();
    }

    @Autowired
    public void setApi(Api api) {
        this.api = api;
    }

    @Override
    public void startMenu() {

        String answer = view.read("" +
                "1 - Authorization\n" +
                "2 - Registration\n" +
                "3 - Quit");
        switch (answer) {
            case "1":
                String email = view.read("Enter yor email:");
                String pwd = view.read("Enter your password:");
                api.authorize(email, pwd,
                        new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showLoading();
                                view.showLoadingDone();
                                noteMenu();
                            }
                        }, new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                                startMenu();
                            }
                        });
                break;
            case "2":
                String name = view.read("Enter your name:");
                email = view.read("Enter yor email:");
                pwd = view.read("Enter your password:");
                api.registration(name, email, pwd,
                        new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showLoading();
                                view.showLoadingDone();
                                startMenu();
                            }
                        }, new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                                startMenu();
                            }
                        });
                break;
            case "3":
                break;
            default:
                view.showMessage("INVALID_COMMAND");
                startMenu();
        }
    }

    @Override
    public void noteMenu() {
        String answer = view.read(
                "1 - Get_users\n" +
                        "2 - Create_note\n" +
                        "3 - Edit_note\n" +
                        "4 - Get_notes\n" +
                        "5 - Delete_note\n" +
                        "6 - AddAccessRight \n" +
                        "7 - RemoveAccessRight\n" +
                        "8 - Quit");
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        switch (answer) {
            case "1":
                api.getUsers(new Callback<List<User>>() {
                                 @Override
                                 public void call(List<User> result) {
                                     view.showLoading();
                                     view.showMessage("User_list:");
                                     for (User user : result) {
                                         view.showMessage("  " + user.getName() + " -- " +
                                                 user.getEmail());
                                     }
                                     noteMenu();
                                 }
                             }, new Callback<String>() {
                                 @Override
                                 public void call(String result) {
                                     view.showMessage(result);
                                     noteMenu();
                                 }
                             }
                );
                break;
            case "2":
                String tittle = view.read("Enter the tittle: ");
                String body = view.read("Enter the body:");
                api.createNote(tittle, body, new Callback<Note>() {
                            @Override
                            public void call(Note result) {
                                view.showLoading();
                                view.showMessage("Created_note: ");
                                view.showMessage("Title: " + result.getTitle() + " Body: " + result.getBody());
                                view.showLoadingDone();
                                noteMenu();
                            }
                        }, new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                                noteMenu();
                            }
                        }
                );
                break;
            case "3":
                String noteId = view.read("Enter the noteId:");
                tittle = view.read("Enter the tittle: ");
                body = view.read("Enter the body:");
                api.editNote(noteId, tittle, body,
                        new Callback<Note>() {
                            @Override
                            public void call(Note result) {
                                view.showLoading();
                                view.showMessage("Changed_note: ");
                                view.showMessage("Title: " + result.getTitle() + " Body: " + result.getBody() +
                                        " Created: " + sdf.format(result.getCreated()) +
                                        " Updated: " + sdf.format(result.getUpdated()));
                                noteMenu();
                            }
                        }, new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                                noteMenu();
                            }
                        });
                break;
            case "4":
                api.getNotes(new Callback<List<Note>>() {
                                 @Override
                                 public void call(List<Note> result) {
                                     view.showLoading();
                                     view.showMessage("List of the notes: ");
                                     for (Note note : result) {
                                         view.showMessage("< " + note.getId() + " > " +
                                                 " Title: " + note.getTitle() +
                                                 " Body: " + note.getBody() +
                                                 " Created: " + sdf.format(note.getCreated()) +
                                                 " Updated: " + sdf.format(note.getUpdated()));
                                     }
                                     view.showLoadingDone();
                                     noteMenu();
                                 }
                             },
                        new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                                noteMenu();
                            }
                        });
                break;
            case "5":
                api.deleteNote(view.read("Enter the noteId"),
                        new Callback<Note>() {
                            @Override
                            public void call(Note result) {
                                view.showLoading();
                                view.showMessage("Deleted_note: Title: " + result.getTitle() +
                                        " Body: " + result.getBody());
                                view.showLoadingDone();
                                noteMenu();
                            }
                        }, new Callback<String>() {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                            }
                        });
                break;
            case "6":
                noteId = view.read("Enter noteId:");
                String email = view.read("Enter email:");
                String modeStr = view.read("Enter mode(required 0 or 1):");
                int mode;
                try {
                    mode = Integer.parseInt(modeStr);
                    api.addAccessRight(noteId, email, mode, new Callback<Boolean>() {
                                @Override
                                public void call(Boolean result) {
                                    view.showLoading();
                                    if (result) {
                                        view.showLoadingDone();
                                    } else {
                                        view.showMessage("ACCESS_RIGHT_FOR_ALREADY_EXIST");
                                    }
                                    noteMenu();
                                }
                            },
                            new Callback<String>() {
                                @Override
                                public void call(String result) {
                                    view.showMessage(result);
                                    noteMenu();
                                }
                            });
                } catch (NumberFormatException e) {
                    view.showMessage("INVALID_MODE_VALUE");
                    noteMenu();
                }
                break;
            case "7":
                noteId = view.read("Enter noteId:");
                email = view.read("Enter email:");
                api.removeAccessRight(noteId, email, new Callback<Boolean>() {
                            @Override
                            public void call(Boolean result) {
                                view.showLoading();
                                if (result) {
                                    view.showLoadingDone();
                                } else {
                                    view.showMessage("ACCESS_RIGHT_NOT_FOUND");
                                }
                                noteMenu();
                            }
                        },
                        new Callback<String>()
                        {
                            @Override
                            public void call(String result) {
                                view.showMessage(result);
                                noteMenu();
                            }
                        });
                break;
            case "8":
                startMenu();
                break;
            default:
                view.showMessage("WRONG_COMMAND");
                noteMenu();
        }
    }

    public String generateHash(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
            byte[] buffer = input.getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
