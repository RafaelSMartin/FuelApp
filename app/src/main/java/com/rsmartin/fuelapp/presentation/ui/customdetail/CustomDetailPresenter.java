package com.rsmartin.fuelapp.presentation.ui.customdetail;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.executor.ErrorHandler;
import com.rsmartin.fuelapp.presentation.ui.AbstractPresenter;

import javax.inject.Inject;

public class CustomDetailPresenter extends AbstractPresenter<CustomDetailPresenter.View> {

    @Inject
    public CustomDetailPresenter(ErrorHandler errorHandler) {
        super(errorHandler);
    }

    public Drawable mapIconOils(Context context, String name) {
        Drawable resource = null;

        if (name.contains("ALCAMPO")) {
            resource = context.getResources().getDrawable(R.drawable.marker_alcampo);
        } else if (name.contains("AGLA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_agla);
        } else if (name.contains("ANDAMUR")) {
            resource = context.getResources().getDrawable(R.drawable.marker_andamur);
        } else if (name.contains("AVIA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_avia);
        } else if (name.contains("BALLENOIL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_ballenoil);
        } else if (name.contains("BP")) {
            resource = context.getResources().getDrawable(R.drawable.marker_bp);
        } else if (name.contains("CAMPSA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_campsa);
        } else if (name.contains("CARREFOUR")) {
            resource = context.getResources().getDrawable(R.drawable.marker_carrefour);
        } else if (name.contains("CEPSA")) {
            resource = context.getResources().getDrawable(R.drawable.marker_cepsa);
        } else if (name.contains("EASYGAS")) {
            resource = context.getResources().getDrawable(R.drawable.marker_easygas);
        } else if (name.contains("EROSKI")) {
            resource = context.getResources().getDrawable(R.drawable.marker_eroski);
        } else if (name.contains("EUROCAM")) {
            resource = context.getResources().getDrawable(R.drawable.marker_eurocam);
        } else if (name.contains("GALP")) {
            resource = context.getResources().getDrawable(R.drawable.marker_galp);
        } else if (name.contains("PETRONOR")) {
            resource = context.getResources().getDrawable(R.drawable.marker_petronor);
        } else if (name.contains("PLENOIL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_plenoil);
        } else if (name.contains("REPSOL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_repsol);
        } else if (name.contains("SARAS")) {
            resource = context.getResources().getDrawable(R.drawable.marker_saras);
        } else if (name.contains("SHELL")) {
            resource = context.getResources().getDrawable(R.drawable.marker_shell);
        } else {
            resource = context.getResources().getDrawable(R.mipmap.ic_launcher_fuelapp);
        }

        return resource;
    }

    public interface View {
    }
}
