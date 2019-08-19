package com.rsmartin.fuelapp.data.repository.datasource;

import com.rsmartin.fuelapp.data.model.ObtenerGasolinerasDTO;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiOilsDataSource {

    @GET("EstacionesTerrestres/")
    Call<ObtenerGasolinerasDTO> getStationsGob();

}
