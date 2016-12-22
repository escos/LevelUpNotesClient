package ru.levelup.model.entities;

import java.util.UUID;

public class RequestContainer<T> extends BaseRequest {
    private T payload;

    public RequestContainer(String method, T payload) {
        this.requestId = UUID.randomUUID().toString();
        this.method = method;
        this.payload = payload;
    }
}
