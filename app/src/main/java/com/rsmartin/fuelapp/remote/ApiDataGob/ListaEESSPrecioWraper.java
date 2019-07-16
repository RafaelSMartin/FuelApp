package com.rsmartin.fuelapp.remote.ApiDataGob;

import java.io.Serializable;
import java.util.Objects;

public class ListaEESSPrecioWraper implements Serializable {

    private String rotulo;
    private String address;
    private String horary;
    private String lat;
    private String lon;
    private Object precioBiodiesel;
    private String precioGasoleoA;
    private String precioGasoleoB;
    private String precioGasolina95Proteccion;
    private Object precioGasolina98;
    private String precioNuevoGasoleoA;
    private String provincia;
    private String localidad;
    private String municipio;

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHorary() {
        return horary;
    }

    public void setHorary(String horary) {
        this.horary = horary;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Object getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public void setPrecioBiodiesel(Object precioBiodiesel) {
        this.precioBiodiesel = precioBiodiesel;
    }

    public String getPrecioGasoleoA() {
        return precioGasoleoA;
    }

    public void setPrecioGasoleoA(String precioGasoleoA) {
        this.precioGasoleoA = precioGasoleoA;
    }

    public String getPrecioGasoleoB() {
        return precioGasoleoB;
    }

    public void setPrecioGasoleoB(String precioGasoleoB) {
        this.precioGasoleoB = precioGasoleoB;
    }

    public String getPrecioGasolina95Proteccion() {
        return precioGasolina95Proteccion;
    }

    public void setPrecioGasolina95Proteccion(String precioGasolina95Proteccion) {
        this.precioGasolina95Proteccion = precioGasolina95Proteccion;
    }

    public Object getPrecioGasolina98() {
        return precioGasolina98;
    }

    public void setPrecioGasolina98(Object precioGasolina98) {
        this.precioGasolina98 = precioGasolina98;
    }

    public String getPrecioNuevoGasoleoA() {
        return precioNuevoGasoleoA;
    }

    public void setPrecioNuevoGasoleoA(String precioNuevoGasoleoA) {
        this.precioNuevoGasoleoA = precioNuevoGasoleoA;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaEESSPrecioWraper that = (ListaEESSPrecioWraper) o;
        return Objects.equals(rotulo, that.rotulo) &&
                Objects.equals(address, that.address) &&
                Objects.equals(horary, that.horary) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon) &&
                Objects.equals(precioBiodiesel, that.precioBiodiesel) &&
                Objects.equals(precioGasoleoA, that.precioGasoleoA) &&
                Objects.equals(precioGasoleoB, that.precioGasoleoB) &&
                Objects.equals(precioGasolina95Proteccion, that.precioGasolina95Proteccion) &&
                Objects.equals(precioGasolina98, that.precioGasolina98) &&
                Objects.equals(precioNuevoGasoleoA, that.precioNuevoGasoleoA) &&
                Objects.equals(provincia, that.provincia) &&
                Objects.equals(localidad, that.localidad) &&
                Objects.equals(municipio, that.municipio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotulo, address, horary, lat, lon, precioBiodiesel, precioGasoleoA, precioGasoleoB, precioGasolina95Proteccion, precioGasolina98, precioNuevoGasoleoA, provincia, localidad, municipio);
    }

    @Override
    public String toString() {
        return "ListaEESSPrecioWraper{" +
                "rotulo='" + rotulo + '\'' +
                ", address='" + address + '\'' +
                ", horary='" + horary + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", precioBiodiesel=" + precioBiodiesel +
                ", precioGasoleoA='" + precioGasoleoA + '\'' +
                ", precioGasoleoB='" + precioGasoleoB + '\'' +
                ", precioGasolina95Proteccion='" + precioGasolina95Proteccion + '\'' +
                ", precioGasolina98=" + precioGasolina98 +
                ", precioNuevoGasoleoA='" + precioNuevoGasoleoA + '\'' +
                ", provincia='" + provincia + '\'' +
                ", localidad='" + localidad + '\'' +
                ", municipio='" + municipio + '\'' +
                '}';
    }


}
