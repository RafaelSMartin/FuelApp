package com.rsmartin.fuelapp.domain.usecase;

import com.rsmartin.fuelapp.domain.exception.AppException;
import com.rsmartin.fuelapp.domain.executor.UseCaseCallback;
import com.rsmartin.fuelapp.domain.executor.UseCaseCallbackHandler;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.domain.repository.OilsRepository;

import javax.inject.Inject;

public class GetOilsUseCase extends UseCase<ListaDatosGasolineras> {

    OilsRepository oilsRepository;

    @Inject
    public GetOilsUseCase(UseCaseCallbackHandler callbackHandler, OilsRepository oilsRepository) {
        super(callbackHandler);
        this.oilsRepository = oilsRepository;
    }

    public void customize(UseCaseCallback<ListaDatosGasolineras> callback) {
        setCallback(callback);
    }

    @Override
    public void run() {

        try {
            ListaDatosGasolineras listaDatosGasolineras = oilsRepository.getListOils();
            notifyResult(listaDatosGasolineras);

        } catch (AppException appException) {
            notifyError(appException);
        }

    }
}
