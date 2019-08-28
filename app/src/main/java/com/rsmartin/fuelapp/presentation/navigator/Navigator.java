package com.rsmartin.fuelapp.presentation.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.presentation.ui.login.LoginActivity;
import com.rsmartin.fuelapp.presentation.ui.map.MapsActivity;
import com.rsmartin.fuelapp.presentation.ui.splash.SplashActivity;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {

    @Inject
    public Navigator() {
    }

    private void navigateTo(Context context, Class<? extends Activity> activity) {
        Intent intentToLaunch = new Intent(context, activity);
        intentToLaunch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentToLaunch);
    }

    private void navigateTo(Context context, Class<? extends Activity> activity, ListaDatosGasolineras listaDatosGasolinerasShort) {
        Intent intentToLaunch = new Intent(context, activity);
        Bundle bundle = new Bundle();
        bundle.putSerializable(IExtras.ARGS_LIST_OILS_SHORT, listaDatosGasolinerasShort);
        intentToLaunch.putExtras(bundle);
        intentToLaunch.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intentToLaunch);
    }

    public void navigateToSplash(Context context) {
        navigateTo(context, SplashActivity.class);
    }

    public void navigateToLogin(Context context) {
        navigateTo(context, LoginActivity.class);
    }

    public void navigateToMaps(Context context) {
        navigateTo(context, MapsActivity.class);
    }

    public void navigateToMaps(Context context, ListaDatosGasolineras listaDatosGasolinerasShort) {
        navigateTo(context, MapsActivity.class, listaDatosGasolinerasShort);
    }


}
