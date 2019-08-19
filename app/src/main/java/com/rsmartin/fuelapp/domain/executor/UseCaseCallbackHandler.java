package com.rsmartin.fuelapp.domain.executor;

/**
 * Use case callback executor.
 */

public interface UseCaseCallbackHandler {

    void post(Runnable runnable);
}
