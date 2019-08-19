package com.rsmartin.fuelapp.data.mapper;

import com.rsmartin.fuelapp.data.model.ListaEESSPrecioDTO;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;

import java.util.ArrayList;
import java.util.List;

public class OilsDataMapper {

    public ListaDatosGasolineras map(List<ListaEESSPrecioDTO> dtoList) {

        ListaDatosGasolineras list = new ListaDatosGasolineras(new ArrayList<>());
        List<DatosGasolinera> datosGasolineraList = new ArrayList<>();

        for (ListaEESSPrecioDTO item : dtoList) {
            DatosGasolinera datosGasolinera = new DatosGasolinera();

            datosGasolinera.setRotulo(item.getRotulo());
            datosGasolinera.setAddress(item.getDirecciN());
            datosGasolinera.setHorary(item.getHorario());
            datosGasolinera.setLat(item.getLatitud());
            datosGasolinera.setLon(item.getLongitud());
            datosGasolinera.setProvincia(item.getProvincia());
            datosGasolinera.setLocalidad(item.getLocalidad());
            datosGasolinera.setMunicipio(item.getMunicipio());

            datosGasolinera.setPrecioBiodiesel(
                    item.getPrecioBiodiesel() != null ? item.getPrecioBiodiesel().toString() : "0");
            datosGasolinera.setPrecioGasoleoA(
                    item.getPrecioGasoleoA() != null ? item.getPrecioGasoleoA() : "0");
            datosGasolinera.setPrecioGasoleoB(
                    item.getPrecioGasoleoB() != null ? item.getPrecioGasoleoB() : "0");
            datosGasolinera.setPrecioGasolina95Proteccion(
                    item.getPrecioGasolina95ProtecciN() != null ? item.getPrecioGasolina95ProtecciN() : "0");
            datosGasolinera.setPrecioGasolina98(
                    item.getPrecioGasolina98() != null ? item.getPrecioGasolina98().toString() : "0");
            datosGasolinera.setPrecioNuevoGasoleoA(
                    item.getPrecioNuevoGasoleoA() != null ? item.getPrecioNuevoGasoleoA() : "0");

            datosGasolineraList.add(datosGasolinera);
        }

        list.setDatosGasolineraList(datosGasolineraList);
        return list;
    }
}
