
package com.rsmartin.fuelapp.remote.ApiDataGob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Model {

    @SerializedName("Fecha")
    @Expose
    private String fecha;
    @SerializedName("ListaEESSPrecio")
    @Expose
    private List<ListaEESSPrecio> listaEESSPrecio = null;
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

    public List<ListaEESSPrecio> getListaEESSPrecio() {
        return listaEESSPrecio;
    }

    public void setListaEESSPrecio(List<ListaEESSPrecio> listaEESSPrecio) {
        this.listaEESSPrecio = listaEESSPrecio;
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
