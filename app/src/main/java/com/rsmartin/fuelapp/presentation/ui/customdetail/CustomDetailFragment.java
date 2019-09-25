package com.rsmartin.fuelapp.presentation.ui.customdetail;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.presentation.internal.room.database.AppDB;
import com.rsmartin.fuelapp.presentation.ui.AbstractFragment;
import com.rsmartin.fuelapp.utils.Utils;

import java.text.DecimalFormat;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomDetailFragment extends AbstractFragment implements CustomDetailPresenter.View {

//    private Listener mListener;

    @BindView(R.id.favorite)
    ImageView detailFavorite;

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

    @Inject
    CustomDetailPresenter customDetailPresenter;

    private DatosGasolinera datosGasolinera;
    private Context context;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = CustomDetailFragment.this.getContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.custom_detail, container, false);
        ButterKnife.bind(this, view);

        ((AbstractFragment) this).getApplicationComponent().inject(this);
        customDetailPresenter.setView(this);

        Bundle args = getArguments();
        if (args != null) {
            datosGasolinera = (DatosGasolinera) args.getSerializable(IExtras.EXTRAS_CUSTOM_DETAIL);
            if (datosGasolinera != null) {

                detailTitleImage.setImageDrawable(customDetailPresenter.mapIconOils(context,
                        datosGasolinera.getRotulo().toString().trim()));

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
                String addressCompleted = datosGasolinera.getAddress().toString().trim() + "\n" +
                        datosGasolinera.getProvincia().toString().trim() + ", " +
                        datosGasolinera.getMunicipio().toString().trim();
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

                boolean isFavorite = datosGasolinera.isFavorite();
                int resource = isFavorite ? android.R.drawable.star_big_on : android.R.drawable.star_big_off;
                detailFavorite.setImageResource(resource);

                detailFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isFavorite) {
                            datosGasolinera.setFavorite(false);
                            detailFavorite.setImageResource(android.R.drawable.star_big_off);
                        } else {
                            datosGasolinera.setFavorite(true);
                            detailFavorite.setImageResource(android.R.drawable.star_big_on);
                        }
                        AddFavoriteWraperTask addFavoriteWraperTask = new AddFavoriteWraperTask();
                        addFavoriteWraperTask.execute(datosGasolinera);
                    }
                });

            }
        }
        return view;
    }

    private class AddFavoriteWraperTask extends AsyncTask<DatosGasolinera, Void, Void> {
        @Override
        protected Void doInBackground(DatosGasolinera... datosGasolineras) {
            AppDB.getInstance(App.getInstance().getApplicationContext())
                    .gasolinerasDAO().updatePreciosGasolinera(datosGasolineras[0]);
            return null;
        }
    }


}
