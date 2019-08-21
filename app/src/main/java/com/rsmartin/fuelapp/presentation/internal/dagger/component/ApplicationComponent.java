package com.rsmartin.fuelapp.presentation.internal.dagger.component;

import com.rsmartin.fuelapp.presentation.internal.dagger.module.ApplicationModule;
import com.rsmartin.fuelapp.presentation.internal.dagger.module.DataModule;
import com.rsmartin.fuelapp.presentation.internal.dagger.module.DomainModule;
import com.rsmartin.fuelapp.presentation.ui.AbstractActivity;
import com.rsmartin.fuelapp.presentation.ui.map.MapsActivity;
import com.rsmartin.fuelapp.presentation.ui.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Application component. Dagger.
 */

@Singleton
@Component(modules = {ApplicationModule.class, DataModule.class, DomainModule.class})
public interface ApplicationComponent {

    void inject(AbstractActivity abstractActivity);

    void inject(SplashActivity splashActivity);

    void inject(MapsActivity mapsActivity);

}
