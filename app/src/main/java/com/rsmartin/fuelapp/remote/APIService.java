package com.rsmartin.fuelapp.remote;

import com.rsmartin.fuelapp.remote.ApiDataGob.Model;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIService {

    @GET("EstacionesTerrestres/")
    Call<Model> getStationsGob();

}
