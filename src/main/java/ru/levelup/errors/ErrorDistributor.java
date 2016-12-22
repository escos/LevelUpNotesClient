package ru.levelup.errors;

public class ErrorDistributor {

    public String errorWriter(int code, String message){
        switch (code){
            case 451:
                return "WRONG_JSON_MESSAGE";
            case 452:
                return "INVALID_PROTOCOL";
            case 453:
                return "INVALID_TOKEN";
            case 454:
                return message;
            default:

        }
        return "WRONG_CODE";
    }
}
