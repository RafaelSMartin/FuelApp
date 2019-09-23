package com.rsmartin.fuelapp.presentation.ui.splash;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.domain.model.DatosGasolinera;
import com.rsmartin.fuelapp.domain.model.ListaDatosGasolineras;
import com.rsmartin.fuelapp.presentation.internal.android.SharedPref;
import com.rsmartin.fuelapp.presentation.internal.room.database.AppDB;
import com.rsmartin.fuelapp.presentation.ui.AbstractActivity;
import com.rsmartin.fuelapp.utils.AppDialog;
import com.rsmartin.fuelapp.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AbstractActivity implements SplashPresenter.View {

    private final String TAG = "SplashActivity";

    private FusedLocationProviderClient fusedLocationClient;
    private Location lastLocation;
    private LatLng currentLatLon = new LatLng(0, 0);

    private AlertDialog.Builder dialogBuilder = null;

    @Inject
    SplashPresenter splashPresenter;

    @BindView(R.id.response)
    TextView tvResponse;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
        ButterKnife.bind(this);

        initAdMob();

        getApplicationComponent().inject(this);
        splashPresenter.setView(this);

        Dexter.withActivity(SplashActivity.this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        initLocation();
                        getOils();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {
                        if (response.isPermanentlyDenied()) {
                            AppDialog.showDialog(SplashActivity.this, "Permiso Localizacion",
                                    "Accede a Ajustes de Aplicaciones y concede permisos :)", true);
                        } else {
                            AppDialog.showDialog(SplashActivity.this, "Permiso Localizacion",
                                    "Sin el permiso FuelApp no funcionará correctamente :(", true);
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                })
                .onSameThread()
                .check();

    }

    private void initAdMob() {
        MobileAds.initialize(this, IExtras.ID_BANNER_APLICATION);
    }

    private void getOils() {
        boolean shouldFetch = shouldFetch();

        if (shouldFetch) {
            tvResponse.setText("Cargando desde internet");
            splashPresenter.getOils();
        } else {
            tvResponse.setText("Cargando desde BBDD");
            FindAllListaPrecioWraperTask findAllListaPrecioWraperTask = new FindAllListaPrecioWraperTask();
            findAllListaPrecioWraperTask.execute();
        }
    }

    private long now() {
        return SystemClock.uptimeMillis();
    }

    public boolean shouldFetch() {
        Long lastFetched = SharedPref.getInstance().getLongPreferences(IExtras.UPDATE_DB);
        long now = now();

        if (lastFetched == null || lastFetched == -1) {
            SharedPref.getInstance().saveLongPreferences(IExtras.UPDATE_DB, now);
            return true;
        }

        long timeout = TimeUnit.HOURS.toMillis(24);
        if (now - lastFetched > timeout) {
            SharedPref.getInstance().saveLongPreferences(IExtras.UPDATE_DB, now);
            return true;
        }

        return false;
    }

    @SuppressLint("MissingPermission")
    private void initLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    lastLocation = location;
                    currentLatLon = new LatLng(location.getLatitude(), location.getLongitude());
                    SharedPref.getInstance().saveLongPreferences(IExtras.CURRENT_LAT, Double.valueOf(location.getLatitude()).longValue());
                    SharedPref.getInstance().saveLongPreferences(IExtras.CURRENT_LONG, Double.valueOf(location.getLongitude()).longValue());
                }
            }
        });
    }

    @Override
    public void showResult(ListaDatosGasolineras listaDatosGasolineras) {
        boolean isFirstTime = SharedPref.getInstance().getBooleanPreferencesIsFristTime();

        if (isFirstTime) {
            SharedPref.getInstance().saveBooleanPreferences(IExtras.IS_FIRST_TIME, false);
            tvResponse.setText("Cargando desde BBDD Insertando");
            InsertListaPrecioWraperTask insert = new InsertListaPrecioWraperTask();
            insert.execute(listaDatosGasolineras.getDatosGasolineraList());
        } else {
            tvResponse.setText("Cargando desde BBDD Actualizando");
            UpdateListaPrecioWraperTask update = new UpdateListaPrecioWraperTask();
            update.execute(listaDatosGasolineras.getDatosGasolineraList());
        }

        showResultFromRoom(listaDatosGasolineras);
    }

    public void showResultFromRoom(ListaDatosGasolineras listaDatosGasolineras) {

        List<DatosGasolinera> lista = new ArrayList<>();
        boolean isDistanceMinima = false;

        for (DatosGasolinera item : listaDatosGasolineras.getDatosGasolineraList()) {

            if (item.getLat() != null && item.getLon() != null) {
                isDistanceMinima = Utils.distance(currentLatLon.latitude, currentLatLon.longitude,
                        Utils.replaceComaToDot(item.getLat()), Utils.replaceComaToDot(item.getLon()))
                        <= 50;
                if (isDistanceMinima && lista.size() < 300) {
                    lista.add(item);
                }
            }
        }

        ListaDatosGasolineras listaDatosGasolinerasShort = new ListaDatosGasolineras(lista);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            navigator.navigateToMaps(getApplicationContext(), listaDatosGasolinerasShort);
        } else {
            navigator.navigateToLogin(getApplicationContext());
        }

    }

    public class FindAllListaPrecioWraperTask extends AsyncTask<Void, Void, List<DatosGasolinera>> {

        @Override
        protected List<DatosGasolinera> doInBackground(Void... voids) {
            return AppDB.getInstance(App.getInstance().getApplicationContext())
                    .gasolinerasDAO().findAllPreciosGasolineras();
        }

        @Override
        protected void onPostExecute(List<DatosGasolinera> lists) {
            showResultFromRoom(new ListaDatosGasolineras(lists));
        }
    }

    public class InsertListaPrecioWraperTask extends AsyncTask<List<DatosGasolinera>, Void, Void> {
        @Override
        protected Void doInBackground(List<DatosGasolinera>... lists) {

            for (DatosGasolinera item : lists[0]) {
                AppDB.getInstance(App.getInstance().getApplicationContext())
                        .gasolinerasDAO().insertPrecioGasolinera(item);
            }
            return null;
        }
    }

    public class UpdateListaPrecioWraperTask extends AsyncTask<List<DatosGasolinera>, Void, Void> {
        @Override
        protected Void doInBackground(List<DatosGasolinera>... lists) {

            for (DatosGasolinera item : lists[0]) {
                AppDB.getInstance(App.getInstance().getApplicationContext())
                        .gasolinerasDAO().updatePreciosGasolinera(item);
            }
            return null;
        }
    }

    @Override
    public void showLoader() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        progressBar.setVisibility(View.INVISIBLE);
    }

}
