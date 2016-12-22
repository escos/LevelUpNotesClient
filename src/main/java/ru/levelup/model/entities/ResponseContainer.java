package ru.levelup.model.entities;

public class ResponseContainer<T> extends BaseResponse {

    private T payload;

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }
}
