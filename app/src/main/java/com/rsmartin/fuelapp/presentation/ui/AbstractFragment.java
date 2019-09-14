package com.rsmartin.fuelapp.presentation.ui;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.presentation.internal.dagger.component.ApplicationComponent;
import com.rsmartin.fuelapp.presentation.navigator.Navigator;

import javax.inject.Inject;

public class AbstractFragment extends BottomSheetDialogFragment {

    public static final String TAG = "AbstractFragment";

    @Inject
    public Navigator navigator;

    public ApplicationComponent getApplicationComponent() {
        return ((App) getActivity().getApplication()).getApplicationComponent();
    }

}
