
package com.rsmartin.fuelapp.remote.ApiDataGob;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class ListaEESSPrecio {

    @SerializedName("C.P.")
    @Expose
    private String cP;

    @SerializedName("Dirección")
    @Expose
    private String direcciN;

    @SerializedName("Horario")
    @Expose
    private String horario;

    @SerializedName("Latitud")
    @Expose
    private String latitud;

    @SerializedName("Localidad")
    @Expose
    private String localidad;

    @SerializedName("Longitud (WGS84)")
    @Expose
    private String longitud;

    @SerializedName("Margen")
    @Expose
    private String margen;

    @SerializedName("Municipio")
    @Expose
    private String municipio;

    @SerializedName("Precio Biodiesel")
    @Expose
    private Object precioBiodiesel;

    @SerializedName("Precio Bioetanol")
    @Expose
    private Object precioBioetanol;

    @SerializedName("Precio Gas Natural Comprimido")
    @Expose
    private Object precioGasNaturalComprimido;

    @SerializedName("Precio Gas Natural Licuado")
    @Expose
    private Object precioGasNaturalLicuado;

    @SerializedName("Precio Gases licuados del petróleo")
    @Expose
    private Object precioGasesLicuadosDelPetrLeo;

    @SerializedName("Precio Gasoleo A")
    @Expose
    private String precioGasoleoA;

    @SerializedName("Precio Gasoleo B")
    @Expose
    private String precioGasoleoB;

    @SerializedName("Precio Gasolina 95 Protección")
    @Expose
    private String precioGasolina95ProtecciN;

    @SerializedName("Precio Gasolina  98")
    @Expose
    private Object precioGasolina98;

    @SerializedName("Precio Nuevo Gasoleo A")
    @Expose
    private String precioNuevoGasoleoA;

    @SerializedName("Provincia")
    @Expose
    private String provincia;

    @SerializedName("Remisión")
    @Expose
    private String remisiN;
    @SerializedName("Rótulo")
    @Expose
    private String rotulo;
    @SerializedName("Tipo Venta")
    @Expose
    private String tipoVenta;

    @SerializedName("% BioEtanol")
    @Expose
    private String bioEtanol;

    @SerializedName("% Éster metílico")
    @Expose
    private String sterMetLico;

    @SerializedName("IDEESS")
    @Expose
    private String iDEESS;

    @SerializedName("IDMunicipio")
    @Expose
    private String iDMunicipio;

    @SerializedName("IDProvincia")
    @Expose
    private String iDProvincia;

    @SerializedName("IDCCAA")
    @Expose
    private String iDCCAA;


    public String getCP() {
        return cP;
    }

    public void setCP(String cP) {
        this.cP = cP;
    }

    public String getDirecciN() {
        return direcciN;
    }

    public void setDirecciN(String direcciN) {
        this.direcciN = direcciN;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMargen() {
        return margen;
    }

    public void setMargen(String margen) {
        this.margen = margen;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Object getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public void setPrecioBiodiesel(Object precioBiodiesel) {
        this.precioBiodiesel = precioBiodiesel;
    }

    public Object getPrecioBioetanol() {
        return precioBioetanol;
    }

    public void setPrecioBioetanol(Object precioBioetanol) {
        this.precioBioetanol = precioBioetanol;
    }

    public Object getPrecioGasNaturalComprimido() {
        return precioGasNaturalComprimido;
    }

    public void setPrecioGasNaturalComprimido(Object precioGasNaturalComprimido) {
        this.precioGasNaturalComprimido = precioGasNaturalComprimido;
    }

    public Object getPrecioGasNaturalLicuado() {
        return precioGasNaturalLicuado;
    }

    public void setPrecioGasNaturalLicuado(Object precioGasNaturalLicuado) {
        this.precioGasNaturalLicuado = precioGasNaturalLicuado;
    }

    public Object getPrecioGasesLicuadosDelPetrLeo() {
        return precioGasesLicuadosDelPetrLeo;
    }

    public void setPrecioGasesLicuadosDelPetrLeo(Object precioGasesLicuadosDelPetrLeo) {
        this.precioGasesLicuadosDelPetrLeo = precioGasesLicuadosDelPetrLeo;
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

    public String getPrecioGasolina95ProtecciN() {
        return precioGasolina95ProtecciN;
    }

    public void setPrecioGasolina95ProtecciN(String precioGasolina95ProtecciN) {
        this.precioGasolina95ProtecciN = precioGasolina95ProtecciN;
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

    public String getRemisiN() {
        return remisiN;
    }

    public void setRemisiN(String remisiN) {
        this.remisiN = remisiN;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getBioEtanol() {
        return bioEtanol;
    }

    public void setBioEtanol(String bioEtanol) {
        this.bioEtanol = bioEtanol;
    }

    public String getSterMetLico() {
        return sterMetLico;
    }

    public void setSterMetLico(String sterMetLico) {
        this.sterMetLico = sterMetLico;
    }

    public String getIDEESS() {
        return iDEESS;
    }

    public void setIDEESS(String iDEESS) {
        this.iDEESS = iDEESS;
    }

    public String getIDMunicipio() {
        return iDMunicipio;
    }

    public void setIDMunicipio(String iDMunicipio) {
        this.iDMunicipio = iDMunicipio;
    }

    public String getIDProvincia() {
        return iDProvincia;
    }

    public void setIDProvincia(String iDProvincia) {
        this.iDProvincia = iDProvincia;
    }

    public String getIDCCAA() {
        return iDCCAA;
    }

    public void setIDCCAA(String iDCCAA) {
        this.iDCCAA = iDCCAA;
    }

    public String getcP() {
        return cP;
    }

    public void setcP(String cP) {
        this.cP = cP;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getiDEESS() {
        return iDEESS;
    }

    public void setiDEESS(String iDEESS) {
        this.iDEESS = iDEESS;
    }

    public String getiDMunicipio() {
        return iDMunicipio;
    }

    public void setiDMunicipio(String iDMunicipio) {
        this.iDMunicipio = iDMunicipio;
    }

    public String getiDProvincia() {
        return iDProvincia;
    }

    public void setiDProvincia(String iDProvincia) {
        this.iDProvincia = iDProvincia;
    }

    public String getiDCCAA() {
        return iDCCAA;
    }

    public void setiDCCAA(String iDCCAA) {
        this.iDCCAA = iDCCAA;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListaEESSPrecio that = (ListaEESSPrecio) o;
        return Objects.equals(cP, that.cP) &&
                Objects.equals(direcciN, that.direcciN) &&
                Objects.equals(horario, that.horario) &&
                Objects.equals(latitud, that.latitud) &&
                Objects.equals(localidad, that.localidad) &&
                Objects.equals(longitud, that.longitud) &&
                Objects.equals(margen, that.margen) &&
                Objects.equals(municipio, that.municipio) &&
                Objects.equals(precioBiodiesel, that.precioBiodiesel) &&
                Objects.equals(precioBioetanol, that.precioBioetanol) &&
                Objects.equals(precioGasNaturalComprimido, that.precioGasNaturalComprimido) &&
                Objects.equals(precioGasNaturalLicuado, that.precioGasNaturalLicuado) &&
                Objects.equals(precioGasesLicuadosDelPetrLeo, that.precioGasesLicuadosDelPetrLeo) &&
                Objects.equals(precioGasoleoA, that.precioGasoleoA) &&
                Objects.equals(precioGasoleoB, that.precioGasoleoB) &&
                Objects.equals(precioGasolina95ProtecciN, that.precioGasolina95ProtecciN) &&
                Objects.equals(precioGasolina98, that.precioGasolina98) &&
                Objects.equals(precioNuevoGasoleoA, that.precioNuevoGasoleoA) &&
                Objects.equals(provincia, that.provincia) &&
                Objects.equals(remisiN, that.remisiN) &&
                Objects.equals(rotulo, that.rotulo) &&
                Objects.equals(tipoVenta, that.tipoVenta) &&
                Objects.equals(bioEtanol, that.bioEtanol) &&
                Objects.equals(sterMetLico, that.sterMetLico) &&
                Objects.equals(iDEESS, that.iDEESS) &&
                Objects.equals(iDMunicipio, that.iDMunicipio) &&
                Objects.equals(iDProvincia, that.iDProvincia) &&
                Objects.equals(iDCCAA, that.iDCCAA);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cP, direcciN, horario, latitud, localidad, longitud, margen, municipio, precioBiodiesel, precioBioetanol, precioGasNaturalComprimido, precioGasNaturalLicuado, precioGasesLicuadosDelPetrLeo, precioGasoleoA, precioGasoleoB, precioGasolina95ProtecciN, precioGasolina98, precioNuevoGasoleoA, provincia, remisiN, rotulo, tipoVenta, bioEtanol, sterMetLico, iDEESS, iDMunicipio, iDProvincia, iDCCAA);
    }

    @Override
    public String toString() {
        return "ListaEESSPrecio{" +
                "cP='" + cP + '\'' +
                ", direcciN='" + direcciN + '\'' +
                ", horario='" + horario + '\'' +
                ", latitud='" + latitud + '\'' +
                ", localidad='" + localidad + '\'' +
                ", longitud='" + longitud + '\'' +
                ", margen='" + margen + '\'' +
                ", municipio='" + municipio + '\'' +
                ", precioBiodiesel=" + precioBiodiesel +
                ", precioBioetanol=" + precioBioetanol +
                ", precioGasNaturalComprimido=" + precioGasNaturalComprimido +
                ", precioGasNaturalLicuado=" + precioGasNaturalLicuado +
                ", precioGasesLicuadosDelPetrLeo=" + precioGasesLicuadosDelPetrLeo +
                ", precioGasoleoA='" + precioGasoleoA + '\'' +
                ", precioGasoleoB='" + precioGasoleoB + '\'' +
                ", precioGasolina95ProtecciN='" + precioGasolina95ProtecciN + '\'' +
                ", precioGasolina98=" + precioGasolina98 +
                ", precioNuevoGasoleoA='" + precioNuevoGasoleoA + '\'' +
                ", provincia='" + provincia + '\'' +
                ", remisiN='" + remisiN + '\'' +
                ", rotulo='" + rotulo + '\'' +
                ", tipoVenta='" + tipoVenta + '\'' +
                ", bioEtanol='" + bioEtanol + '\'' +
                ", sterMetLico='" + sterMetLico + '\'' +
                ", iDEESS='" + iDEESS + '\'' +
                ", iDMunicipio='" + iDMunicipio + '\'' +
                ", iDProvincia='" + iDProvincia + '\'' +
                ", iDCCAA='" + iDCCAA + '\'' +
                '}';
    }
}
