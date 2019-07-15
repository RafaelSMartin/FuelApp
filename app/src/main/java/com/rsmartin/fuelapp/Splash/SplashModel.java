package com.rsmartin.fuelapp.Splash;


import com.rsmartin.fuelapp.remote.ApiDataGob.ListaEESSPrecio;
import com.rsmartin.fuelapp.remote.ApiDataGob.ListaEESSPrecioWraper;
import com.rsmartin.fuelapp.remote.ApiDataGob.Model;

import java.util.ArrayList;
import java.util.List;

public class SplashModel implements ContractSplash.SplashModel {

    private SplashPresenter presenter;

    public SplashModel(SplashPresenter presenter){
        this.presenter = presenter;
    }


    @Override
    public void map(Model response) {
        List<ListaEESSPrecioWraper> modelWraper = new ArrayList<>();

//        for (int i = 0; i < response.getListaEESSPrecio().size(); i++){

//            modelWraper.get(i).setRotulo(response.getListaEESSPrecio().get(i).getRotulo());
//            modelWraper.get(i).setLat(response.getListaEESSPrecio().get(i).getLatitud());
//            modelWraper.get(i).setLon(response.getListaEESSPrecio().get(i).getLongitud());
//        }
        //String resultado = response.getListaEESSPrecio().toString();
        presenter.showResultMap("resultado");
    }
}
