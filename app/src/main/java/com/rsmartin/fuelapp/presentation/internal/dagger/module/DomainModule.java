package com.rsmartin.fuelapp.presentation.internal.dagger.module;

import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.domain.executor.UseCaseCallbackHandler;
import com.rsmartin.fuelapp.domain.executor.UseCaseHandler;
import com.rsmartin.fuelapp.presentation.executor.DefaultErrorHandler;
import com.rsmartin.fuelapp.presentation.executor.DefaultUseCaseCallbackHandler;
import com.rsmartin.fuelapp.presentation.executor.DefaultUseCaseHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class DomainModule {

    @Provides
    UseCaseHandler provideUseCaseHandler(DefaultUseCaseHandler useCaseHandler) {
        return useCaseHandler;
    }

    @Provides
    UseCaseCallbackHandler provideUseCaseCallbackHandler(
            DefaultUseCaseCallbackHandler useCaseCallbackHandler) {
        return useCaseCallbackHandler;
    }

    @Provides
    ErrorHandler provideErrorHandler(DefaultErrorHandler errorHandler) {
        return errorHandler;
    }
}
