package com.rsmartin.fuelapp.Splash;

import com.rsmartin.fuelapp.remote.ApiDataGob.Model;

interface ContractSplash {

    interface SplashView{
        void showResult(String response);
        void showProgress();
        void hideProgress();
    }

    interface SplashPresenter{
        void getOilsGob();
        void showResultMap(String response);

    }

    interface SplashModel{
        void map(Model response);
    }


//    void saveModelGob(String model);
//    void launchGoogleMaps();
}
