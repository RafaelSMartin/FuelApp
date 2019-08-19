package com.rsmartin.fuelapp.presentation.executor;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.domain.executor.ErrorHandler;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Default error handler
 */

@Singleton
public class DefaultErrorHandler implements ErrorHandler {

    private App application;

    @Inject
    public DefaultErrorHandler(App application) {
        this.application = application;
    }

    @Override
    public boolean handle(Throwable error) {

        if (error == null) {
            return false;
        }

        return false;
    }
}
