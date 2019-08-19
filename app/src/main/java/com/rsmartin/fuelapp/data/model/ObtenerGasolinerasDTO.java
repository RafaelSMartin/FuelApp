
package com.rsmartin.fuelapp.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ObtenerGasolinerasDTO {

    @SerializedName("Fecha")
    @Expose
    private String fecha;
    @SerializedName("ListaEESSPrecio")
    @Expose
    private List<ListaEESSPrecioDTO> listaEESSPrecioDto = null;
    @SerializedName("Nota")
    @Expose
    private String nota;
    @SerializedName("ResultadoConsulta")
    @Expose
    private Object resultadoConsulta;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<ListaEESSPrecioDTO> getListaEESSPrecioDto() {
        return listaEESSPrecioDto;
    }

    public void setListaEESSPrecioDto(List<ListaEESSPrecioDTO> listaEESSPrecioDto) {
        this.listaEESSPrecioDto = listaEESSPrecioDto;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public Object getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(Object resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }

}
