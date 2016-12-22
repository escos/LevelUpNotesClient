package ru.levelup.presenter;

import ru.levelup.view.View;

public interface Presenter {

    void setView(View view);

    void startMenu();

    void noteMenu();
}
