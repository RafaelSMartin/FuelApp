package com.rsmartin.fuelapp.presentation.ui;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.presentation.internal.dagger.component.ApplicationComponent;

public abstract class AbstractFragmentActivity extends FragmentActivity {
    private ProgressDialog loaderDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return ((App) getApplication()).getApplicationComponent();
    }

    public void showProgress() {
        if (loaderDialog != null && loaderDialog.isShowing())
            hideProgress();

        loaderDialog = ProgressDialog.show(this, null,
                null, true);
    }

    public void hideProgress() {
        loaderDialog.dismiss();
    }
}
