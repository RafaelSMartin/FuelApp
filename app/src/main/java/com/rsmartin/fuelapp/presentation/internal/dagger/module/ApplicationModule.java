package com.rsmartin.fuelapp.presentation.internal.dagger.module;

import com.rsmartin.fuelapp.App;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Application DI module.
 */

@Module
public class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @Singleton
    App provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    ExecutorService provideExecutorService() {
        return Executors.newCachedThreadPool();
    }
}
