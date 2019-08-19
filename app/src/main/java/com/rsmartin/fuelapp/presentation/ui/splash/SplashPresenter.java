package com.rsmartin.fuelapp.presentation.ui.splash;

import com.rsmartin.fuelapp.data.model.ApiError;
import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.domain.executor.UseCaseCallback;
import com.rsmartin.fuelapp.domain.executor.UseCaseHandler;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.domain.usecase.GetOilsUseCase;
import com.rsmartin.fuelapp.presentation.ui.AbstractPresenter;

import javax.inject.Inject;

public class SplashPresenter extends AbstractPresenter<SplashPresenter.View> {

    private final String TAG = "SplashPresenter";

    private UseCaseHandler useCaseHandler;
    private GetOilsUseCase getOilsUseCase;

    @Inject
    public SplashPresenter(UseCaseHandler useCaseHandler,
                           GetOilsUseCase getOilsUseCase,
                           ErrorHandler errorHandler) {
        super(errorHandler);
        this.useCaseHandler = useCaseHandler;
        this.getOilsUseCase = getOilsUseCase;
    }

    public void getOils() {
        getView().showLoader();
        getOilsUseCase.customize(new UseCaseCallback<ListaDatosGasolineras>() {
            @Override
            public void onResult(ListaDatosGasolineras result) {
                getView().hideLoader();
                getView().showResult(result);
            }

            @Override
            public void onError(Throwable error) {
                getView().hideLoader();
                ApiError apiError = new ApiError();
                apiError.setMessage(error.getMessage());
                // lanzar popup con el error
            }
        });
        useCaseHandler.execute(getOilsUseCase);

    }

    public interface View {
        void showResult(ListaDatosGasolineras listaDatosGasolineras);

        void showLoader();

        void hideLoader();
    }
}
