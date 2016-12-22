package ru.levelup.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.levelup.errors.ErrorDistributor;
import ru.levelup.model.entities.BaseResponse;
import ru.levelup.model.entities.RequestContainer;
import ru.levelup.model.entities.ResponseContainer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;

@Component("wsClient")
public class WSClient extends WebSocketClient {
    private Gson gson;
    private HashMap<String, ResponseProcessingData> requests = new HashMap<>();
    private ErrorDistributor errorDistributor = new ErrorDistributor();

    @Autowired
    public WSClient(URI wsUri, Gson gson) {
        super(wsUri);
        this.gson = gson;
    }

    @PostConstruct
    public void onCreated() {
        connect();
    }

    @PreDestroy
    public void onDestroyed() {
        close();
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected");
    }

    @Override
    public void onMessage(String message) {
        System.out.println("server: " + message);
        BaseResponse baseResponse = gson.fromJson(message, BaseResponse.class);
        if (requests.containsKey(baseResponse.getRequestId())) {
            ResponseProcessingData processingData = requests.get(baseResponse.getRequestId());
            if (baseResponse.getCode() == 200) {
                ResponseContainer response = gson.fromJson(message, processingData.getResultType());
                processingData.getOnSuccess().call(response.getPayload());
            } else {
                ResponseContainer<String> response = gson.fromJson(message,
                        new TypeToken<ResponseContainer<String>>() {
                        }.getType());
                processingData.getOnError().call(errorDistributor.errorWriter(baseResponse.getCode(),
                        response.getPayload()));
            }
            requests.remove(baseResponse.getRequestId());
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed: " + reason);
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public void sendRequest(String method, Object payload, String token, Type type,
                            Callback onSuccess, Callback onError) {
        RequestContainer request = new RequestContainer(method, payload);
        request.setToken(token);
        new Thread(new Runnable() {
            @Override
            public void run() {
                requests.put(request.getRequestId(), new ResponseProcessingData(type, onSuccess, onError));
                send(gson.toJson(request));
            }
        }).start();
    }
}
