package com.rsmartin.fuelapp;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.rsmartin.fuelapp.remote.APIService;
import com.rsmartin.fuelapp.remote.ApiUtils;

public class App extends MultiDexApplication {

    private static App instance;

    public static App getInstance(){
        return  instance;
    }

    private APIService apiService;

    public APIService getApiService(){ return apiService; }

    @Override
    public void onCreate(){
        super.onCreate();
        MultiDex.install(this);
        instance = this;
        apiService = ApiUtils.getApiService();
    }

}
