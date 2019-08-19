package com.rsmartin.fuelapp.presentation.executor;

import android.os.Handler;
import android.os.Looper;

import com.rsmartin.fuelapp.domain.executor.UseCaseCallbackHandler;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Use case callback handler.
 */

@Singleton
public class DefaultUseCaseCallbackHandler implements UseCaseCallbackHandler {

    private final Handler handler;

    @Inject
    public DefaultUseCaseCallbackHandler() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    /**
     * Causes the Runnable command to be added to the message queue.
     * The runnable will be run on the main thread.
     *
     * @param runnable {@link Runnable} to be executed.
     */
    @Override
    public void post(Runnable runnable) {
        handler.post(runnable);
    }

}
