package com.rsmartin.fuelapp.domain.exception;

import com.rsmartin.fuelapp.data.model.ApiError;

public class AppException extends RuntimeException {

    private ApiError apiError;

    public AppException() {
        super();
    }

    public AppException(ApiError apiError) {
        this.apiError = apiError;
    }

    public AppException(String detailMessage) {
        super(detailMessage);
    }

    public AppException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public AppException(Throwable throwable) {
        super(throwable);
    }

    public ApiError getApiError() {
        return apiError;
    }

    public void setApiError(ApiError apiError) {
        this.apiError = apiError;
    }
}


