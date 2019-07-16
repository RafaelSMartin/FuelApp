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
        List<ListaEESSPrecio> list = response.getListaEESSPrecio();
        List<ListaEESSPrecioWraper> wraperList = new ArrayList<>();

        for (ListaEESSPrecio aux : list) {
            ListaEESSPrecioWraper wrapperModel = new ListaEESSPrecioWraper();

            wrapperModel.setRotulo(aux.getRotulo());
            wrapperModel.setAddress(aux.getDirecciN());
            wrapperModel.setHorary(aux.getHorario());

            wrapperModel.setLat(aux.getLatitud());
            wrapperModel.setLon(aux.getLongitud());

            wrapperModel.setPrecioBiodiesel(aux.getPrecioBiodiesel());
            wrapperModel.setPrecioGasoleoA(aux.getPrecioGasoleoA());
            wrapperModel.setPrecioGasoleoB(aux.getPrecioGasoleoB());
            wrapperModel.setPrecioGasolina95Proteccion(aux.getPrecioGasolina95ProtecciN());
            wrapperModel.setPrecioGasolina98(aux.getPrecioGasolina98());
            wrapperModel.setPrecioNuevoGasoleoA(aux.getPrecioNuevoGasoleoA());

            wrapperModel.setProvincia(aux.getProvincia());
            wrapperModel.setLocalidad(aux.getLocalidad());
            wrapperModel.setMunicipio(aux.getMunicipio());

            wraperList.add(wrapperModel);
        }

        presenter.showResultMap(wraperList);
    }
}
