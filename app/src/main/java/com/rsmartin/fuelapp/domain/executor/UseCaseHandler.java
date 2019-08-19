package com.rsmartin.fuelapp.domain.executor;

/**
 * Use case executor.
 */

public interface UseCaseHandler {

    void execute(Runnable command);
}
