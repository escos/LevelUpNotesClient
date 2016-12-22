package ru.levelup.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component("view")
public class ConsoleView implements View {
    private BufferedReader console;

    @Autowired
    public ConsoleView(BufferedReader console) {
        this.console = console;
    }

    @Override
    public void showMessage(String text) {
        System.out.println(text);
    }

    @Override
    public void showLoading() {
        System.out.println("Please, wait...");
    }

    @Override
    public void showLoadingDone() {
        System.out.println("Done");
    }

    @Override
    public void showLoadingError(String reason) {
        System.out.println("Error: " + reason);
    }

    @Override
    public String read(String message) {
        System.out.println(message);
        String input = null;
        try {
            input = console.readLine();
        } catch (IOException ignored) {
        }
        return input;
    }
}
