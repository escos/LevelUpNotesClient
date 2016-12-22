package ru.levelup.view;

public interface View {

    void showMessage(String text);

    void showLoading();

    void showLoadingDone();

    void showLoadingError(String reason);

    String read(String message);

}

