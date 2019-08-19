package com.rsmartin.fuelapp.domain.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;
import com.rsmartin.fuelapp.IExtras;

import java.io.Serializable;
import java.util.Objects;

import static com.rsmartin.fuelapp.utils.Utils.replaceComaToDot;

@Entity(tableName = IExtras.NAME_TABLE)
public class DatosGasolinera implements ClusterItem, Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "rotulo")
    private String rotulo;

    @ColumnInfo(name = "address")
    private String address;

    @ColumnInfo(name = "horary")
    private String horary;

    @ColumnInfo(name = "lat")
    private String lat;

    @ColumnInfo(name = "lon")
    private String lon;

    @ColumnInfo(name = "precioBiodiesel")
    private String precioBiodiesel;

    @ColumnInfo(name = "precioGasoleoA")
    private String precioGasoleoA;

    @ColumnInfo(name = "precioGasoleoB")
    private String precioGasoleoB;

    @ColumnInfo(name = "precioGasolina95Proteccion")
    private String precioGasolina95Proteccion;

    @ColumnInfo(name = "precioGasolina98")
    private String precioGasolina98;

    @ColumnInfo(name = "precioNuevoGasoleoA")
    private String precioNuevoGasoleoA;

    @ColumnInfo(name = "provincia")
    private String provincia;

    @ColumnInfo(name = "localidad")
    private String localidad;

    @ColumnInfo(name = "municipio")
    private String municipio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public void setPrecioBiodiesel(String precioBiodiesel) {
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

    public String getPrecioGasolina98() {
        return precioGasolina98;
    }

    public void setPrecioGasolina98(String precioGasolina98) {
        this.precioGasolina98 = precioGasolina98;
    }

    public String getPrecioNuevoGasoleoA() {
        return precioNuevoGasoleoA;
    }

    public void setPrecioNuevoGasoleoA(String precioNuevoGasoleoA) {
        this.precioNuevoGasoleoA = precioNuevoGasoleoA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DatosGasolinera that = (DatosGasolinera) o;
        return Objects.equals(rotulo, that.rotulo) &&
                Objects.equals(address, that.address) &&
                Objects.equals(horary, that.horary) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon) &&
                Objects.equals(provincia, that.provincia) &&
                Objects.equals(localidad, that.localidad) &&
                Objects.equals(municipio, that.municipio) &&
                Objects.equals(precioBiodiesel, that.precioBiodiesel) &&
                Objects.equals(precioGasoleoA, that.precioGasoleoA) &&
                Objects.equals(precioGasoleoB, that.precioGasoleoB) &&
                Objects.equals(precioGasolina95Proteccion, that.precioGasolina95Proteccion) &&
                Objects.equals(precioGasolina98, that.precioGasolina98) &&
                Objects.equals(precioNuevoGasoleoA, that.precioNuevoGasoleoA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rotulo, address, horary, lat, lon, provincia, localidad, municipio, precioBiodiesel, precioGasoleoA, precioGasoleoB, precioGasolina95Proteccion, precioGasolina98, precioNuevoGasoleoA);
    }

    @Override
    public String toString() {
        return "DatosGasolinera{" +
                "rotulo='" + rotulo + '\'' +
                ", address='" + address + '\'' +
                ", horary='" + horary + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", provincia='" + provincia + '\'' +
                ", localidad='" + localidad + '\'' +
                ", municipio='" + municipio + '\'' +
                ", precioBiodiesel='" + precioBiodiesel + '\'' +
                ", precioGasoleoA='" + precioGasoleoA + '\'' +
                ", precioGasoleoB='" + precioGasoleoB + '\'' +
                ", precioGasolina95Proteccion='" + precioGasolina95Proteccion + '\'' +
                ", precioGasolina98='" + precioGasolina98 + '\'' +
                ", precioNuevoGasoleoA='" + precioNuevoGasoleoA + '\'' +
                '}';
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(replaceComaToDot(lat), replaceComaToDot(lon));
    }

    @Override
    public String getTitle() {
        return rotulo;
    }

    @Override
    public String getSnippet() {
        return null;
    }
}
