package com.rsmartin.fuelapp.presentation.executor;

import com.rsmartin.fuelapp.domain.executor.UseCaseHandler;

import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Use case handler.
 */

@Singleton
public class DefaultUseCaseHandler implements UseCaseHandler {

    private ExecutorService executorService;

    @Inject
    public DefaultUseCaseHandler(ExecutorService executorService) {

        this.executorService = executorService;
    }

    @Override
    public void execute(Runnable command) {

        executorService.execute(command);
    }
}
