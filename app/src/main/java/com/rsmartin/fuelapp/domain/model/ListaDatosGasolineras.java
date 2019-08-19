package com.rsmartin.fuelapp.domain.model;

import java.io.Serializable;
import java.util.List;

public class ListaDatosGasolineras implements Serializable {

    private List<DatosGasolinera> datosGasolineraList;

    public ListaDatosGasolineras(List<DatosGasolinera> datosGasolineraList) {
        this.datosGasolineraList = datosGasolineraList;
    }

    public List<DatosGasolinera> getDatosGasolineraList() {
        return datosGasolineraList;
    }

    public void setDatosGasolineraList(List<DatosGasolinera> datosGasolineraList) {
        this.datosGasolineraList = datosGasolineraList;
    }
}
