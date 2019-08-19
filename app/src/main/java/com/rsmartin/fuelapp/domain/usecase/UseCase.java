package com.rsmartin.fuelapp.domain.usecase;

import com.rsmartin.fuelapp.domain.executor.UseCaseCallback;
import com.rsmartin.fuelapp.domain.executor.UseCaseCallbackHandler;

public abstract class UseCase<R> implements Runnable {

    private UseCaseCallbackHandler callbackHandler;

    private UseCaseCallback<R> callback;

    public UseCase(UseCaseCallbackHandler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }

    public void notifyResult(final R result) {
        callbackHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onResult(result);
            }
        });
    }

    public void notifyError(final Throwable error) {
        callbackHandler.post(new Runnable() {
            @Override
            public void run() {

                callback.onError(error);
            }
        });
    }

    public void setCallback(UseCaseCallback<R> callback) {
        this.callback = callback;
    }
}
