package com.rsmartin.fuelapp.presentation.internal.dagger.component;

import com.rsmartin.fuelapp.presentation.internal.dagger.module.ApplicationModule;
import com.rsmartin.fuelapp.presentation.internal.dagger.module.DataModule;
import com.rsmartin.fuelapp.presentation.internal.dagger.module.DomainModule;
import com.rsmartin.fuelapp.presentation.ui.AbstractActivity;
import com.rsmartin.fuelapp.presentation.ui.AbstractFragment;
import com.rsmartin.fuelapp.presentation.ui.AbstractFragmentActivity;
import com.rsmartin.fuelapp.presentation.ui.customdetail.CustomDetailFragment;
import com.rsmartin.fuelapp.presentation.ui.login.LoginActivity;
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

    void inject(AbstractFragmentActivity abstractFragmentActivity);

    void inject(AbstractFragment abstractFragment);

    void inject(SplashActivity splashActivity);

    void inject(LoginActivity loginActivity);

    void inject(MapsActivity mapsActivity);

    void inject(CustomDetailFragment customDetailFragment);

//    void inject(Navigator navigator);

}
