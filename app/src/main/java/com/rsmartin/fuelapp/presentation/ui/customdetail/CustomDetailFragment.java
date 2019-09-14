package com.rsmartin.fuelapp.presentation.ui.customdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.utils.Utils;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDetailFragment extends BottomSheetDialogFragment {

//    private Listener mListener;

    @BindView(R.id.detail_title_image)
    ImageView detailTitleImage;

    @BindView(R.id.detail_title)
    TextView detailTitle;

    @BindView(R.id.detail_distance)
    TextView detailDistance;

    @BindView(R.id.detail_horary)
    TextView detailHorary;

    @BindView(R.id.detail_address)
    TextView detailAddress;

    @BindView(R.id.detail_prices)
    TextView detailPrices;

    private DatosGasolinera datosGasolinera;

    public static CustomDetailFragment newInstance(DatosGasolinera datosGasolinera,
                                                   double currentLat, double currentLong) {
        final CustomDetailFragment fragment = new CustomDetailFragment();
        final Bundle args = new Bundle();
        args.putSerializable(IExtras.EXTRAS_CUSTOM_DETAIL, datosGasolinera);
        args.putDouble(IExtras.CURRENT_LAT, currentLat);
        args.putDouble(IExtras.CURRENT_LONG, currentLong);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_detail, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        if (args != null) {
            datosGasolinera = (DatosGasolinera) args.getSerializable(IExtras.EXTRAS_CUSTOM_DETAIL);
            if (datosGasolinera != null) {
                if (datosGasolinera.getRotulo().contains("REPSOL")) {
                    detailTitleImage.setImageDrawable(getResources().getDrawable(R.drawable.marker_repsol));
                }
                detailTitle.setText(datosGasolinera.getRotulo().trim());
                Double currentLat = args.getDouble(IExtras.CURRENT_LAT);
                Double currentLong = args.getDouble(IExtras.CURRENT_LONG);
                if (currentLat != null && currentLong != null) {
                    double distance = Utils.distance(
                            Utils.replaceComaToDot(datosGasolinera.getLat()),
                            Utils.replaceComaToDot(datosGasolinera.getLon()),
                            currentLat, currentLong);
                    String distanceFormatted = new DecimalFormat("##.##").format(distance);
                    detailDistance.setText(distanceFormatted + " Km.");
                }
                detailHorary.setText(datosGasolinera.getHorary().trim());
                String addressCompleted = datosGasolinera.getAddress() + " " + datosGasolinera.getProvincia() + ", " + datosGasolinera.getMunicipio();
                detailAddress.setText(addressCompleted);
                String allPrices = new StringBuilder()
                        .append("\n").append("Gasolina 95: " + datosGasolinera.getPrecioGasolina95Proteccion())
                        .append("\n").append("Gasolina 98: " + datosGasolinera.getPrecioGasolina98())
                        .append("\n").append("Gasoleo A: " + datosGasolinera.getPrecioGasoleoA())
                        .append("\n").append("Gasoleo B: " + datosGasolinera.getPrecioGasoleoB())
                        .append("\n").append("Nuevo Gasoleo A: " + datosGasolinera.getPrecioNuevoGasoleoA())
                        .append("\n").append("Biodiesel: " + datosGasolinera.getPrecioBiodiesel())
                        .toString().trim();
                detailPrices.setText(allPrices);
            }
        }
        return view;
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        final Fragment parent = getParentFragment();
//        if (parent != null) {
//            mListener = (Listener) parent;
//        } else {
//            mListener = (Listener) context;
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        mListener = null;
//        super.onDetach();
//    }
//
//    public interface Listener {
//        void oncustomDetailFragmentClicked(int position);
//    }


}
