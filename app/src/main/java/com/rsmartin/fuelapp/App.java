package com.rsmartin.fuelapp;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.rsmartin.fuelapp.presentation.internal.dagger.component.ApplicationComponent;
import com.rsmartin.fuelapp.presentation.internal.dagger.component.DaggerApplicationComponent;
import com.rsmartin.fuelapp.presentation.internal.dagger.module.ApplicationModule;

public class App extends MultiDexApplication {

    private static final String TAG = "AppAplication";
    private ApplicationComponent applicationComponent;

    private static App instance;
    public static App getInstance(){
        return  instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        this.initializeInjector();
        instance = this;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public String getStringFromMetadata(String name) {

        return getMetaDataBundle().getString(name);
    }

    private Bundle getMetaDataBundle() {
        ApplicationInfo applicationInfo = null;
        try {
            applicationInfo = getPackageManager()
                    .getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            Log.w(TAG, "Get meta data", e);
        }

        if (applicationInfo != null) {
            return applicationInfo.metaData;
        } else {
            return null;
        }
    }

}
