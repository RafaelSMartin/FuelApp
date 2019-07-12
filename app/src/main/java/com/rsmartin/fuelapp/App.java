package com.rsmartin.fuelapp;

import androidx.multidex.MultiDexApplication;

public class App extends MultiDexApplication {

    private static App instance;

    public static App getInstance(){
        return  instance;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
    }

}
