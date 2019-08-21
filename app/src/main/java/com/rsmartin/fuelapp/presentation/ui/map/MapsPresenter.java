package com.rsmartin.fuelapp.presentation.ui.map;

import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.presentation.ui.AbstractPresenter;

import javax.inject.Inject;

public class MapsPresenter extends AbstractPresenter<MapsPresenter.View> {

    @Inject
    public MapsPresenter(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    public interface View {

    }
}
