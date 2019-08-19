package com.rsmartin.fuelapp.domain.executor;

/**
 * Error handler
 */
public interface ErrorHandler {

    /**
     * Handles errors.
     *
     * @param error the throwable error
     * @return {@code true} when error is handled, otherwise {@code false}.
     */
    boolean handle(Throwable error);
}
