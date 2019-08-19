package com.rsmartin.fuelapp.presentation.executor;

import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.domain.executor.UseCaseCallback;


/**
 * Abstract use case callback
 */

public abstract class AbstractUseCaseCallback<R> implements UseCaseCallback<R> {

    private ErrorHandler errorHandler;

    public AbstractUseCaseCallback(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public void onError(Throwable error) {

        errorHandler.handle(error);
    }
}
