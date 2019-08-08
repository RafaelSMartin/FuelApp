package com.rsmartin.fuelapp.remote.ApiDataGob;

import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;

import java.io.Serializable;
import java.util.List;

public class ModeloListaGasolineras implements Serializable {

    private List<ListaEESSPrecioWraper> listaEESSPrecioWrapers = null;

    public ModeloListaGasolineras(List<ListaEESSPrecioWraper> listaEESSPrecioWrapers) {
        this.listaEESSPrecioWrapers = listaEESSPrecioWrapers;
    }

    public List<ListaEESSPrecioWraper> getListaEESSPrecioWrapers() {
        return listaEESSPrecioWrapers;
    }

    public void setListaEESSPrecioWrapers(List<ListaEESSPrecioWraper> listaEESSPrecioWrapers) {
        this.listaEESSPrecioWrapers = listaEESSPrecioWrapers;
    }

}
