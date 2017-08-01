package com.foundation.soaint.massiveloader.web.infrastructure.common;

/**
 * Created by administrador_1 on 04/09/2016.
 */
public class HTTPResponse {

    private boolean success;
    private String message;

    public HTTPResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
