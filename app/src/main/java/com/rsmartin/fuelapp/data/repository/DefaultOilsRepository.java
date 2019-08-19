package com.rsmartin.fuelapp.data.repository;

import com.rsmartin.fuelapp.ApiConstants;
import com.rsmartin.fuelapp.data.mapper.OilsDataMapper;
import com.rsmartin.fuelapp.data.model.ApiError;
import com.rsmartin.fuelapp.data.model.ObtenerGasolinerasDTO;
import com.rsmartin.fuelapp.data.repository.datasource.RestApiOilsDataSource;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.domain.repository.OilsRepository;
import com.rsmartin.fuelapp.presentation.internal.retrofit.RetrofitClient;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

@Singleton
public class DefaultOilsRepository implements OilsRepository {

    private RestApiOilsDataSource restApiOilsDataSource;
    private OilsDataMapper oilsDataMapper;
    private Converter<ResponseBody, ApiError> errorConverter;

    @Inject
    public DefaultOilsRepository() {
        Retrofit retrofit = RetrofitClient.getClient(ApiConstants.BASE_URL);

        this.restApiOilsDataSource = retrofit.create(RestApiOilsDataSource.class);
        this.oilsDataMapper = new OilsDataMapper();

        errorConverter = retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
    }


    @Override
    public ListaDatosGasolineras getListOils() {
        ListaDatosGasolineras listaDatosGasolineras = new ListaDatosGasolineras(new ArrayList<>());

        try {
            Response<ObtenerGasolinerasDTO> response = restApiOilsDataSource.getStationsGob().execute();

            if (response.isSuccessful()) {
                listaDatosGasolineras = oilsDataMapper.map(response.body().getListaEESSPrecioDto());

            } else {
                ApiError apiError = errorConverter.convert(response.errorBody());
                // Lanzar mi exception error factory propia.
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDatosGasolineras;
    }
}
