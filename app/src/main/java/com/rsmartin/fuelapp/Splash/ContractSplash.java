package com.rsmartin.fuelapp.Splash;

import com.rsmartin.fuelapp.remote.ApiDataGob.ListaEESSPrecioWraper;
import com.rsmartin.fuelapp.remote.ApiDataGob.Model;

import java.util.List;

interface ContractSplash {

    interface SplashView{
        void showResult(List<ListaEESSPrecioWraper> wraperList);
        void showProgress();
        void hideProgress();
    }

    interface SplashPresenter{
        void getOilsGob();

        void showResultMap(List<ListaEESSPrecioWraper> wraperList);

    }

    interface SplashModel{
        void map(Model response);
    }

}
