package com.rsmartin.fuelapp.remote;

public class ApiUtils {

    private ApiUtils(){}

    public static final String BASE_URL = "https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/";

    public static APIService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }

}
