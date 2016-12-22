package ru.levelup.model;

@FunctionalInterface
public interface Callback<T> {

    void call(T result);
}
