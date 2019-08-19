package com.rsmartin.fuelapp.data.model;

import java.io.Serializable;

import okhttp3.internal.http2.ErrorCode;

public class ApiError implements Serializable {

    private ErrorCode errorCode;

    private String message;

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
