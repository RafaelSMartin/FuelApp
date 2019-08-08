package com.rsmartin.fuelapp.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.db.database.InsertListaPrecioWraperTask;
import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;
import com.rsmartin.fuelapp.ui.maps.MapsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashView extends AppCompatActivity implements ContractSplash.SplashView {

    private final String TAG = "SplashView";

    private SplashPresenter presenter;

    public static List<ListaEESSPrecioWraper> listSplash;

    @BindView(R.id.response)
    TextView tvResponse;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_view);
        ButterKnife.bind(this);

        presenter = new SplashPresenter(this);

//        if (AppDB.getInstance(getApplicationContext()).getOpenHelper().getDatabaseName() == null ||
//                !AppDB.getInstance(getApplicationContext()).getOpenHelper().getDatabaseName().equals(IExtras.NAME_TABLE)) {
//            presenter.getOilsGob();
//        } else {
//            tvResponse.setText("cargado ya de antes");
//            hideProgress();
//            startActivity(new Intent(this, MainActivity.class));
//        }
        presenter.getOilsGob();
    }

    @Override
    public void showResult(List<ListaEESSPrecioWraper> wraperList) {
        InsertListaPrecioWraperTask insertListaPrecioWraperTask = new InsertListaPrecioWraperTask();
        insertListaPrecioWraperTask.execute(wraperList);

//        for (ListaEESSPrecioWraper aux : wraperList) {
//            AppDB.getInstance(getApplicationContext()).listaEESSPrecioWraperDAO().insertListaPrecioWraper(aux);
//        }


        int iterator = 0;
        listSplash = new ArrayList<>();
        for (ListaEESSPrecioWraper item : wraperList) {
            if (iterator <= 50) {
                listSplash.add(item);
                iterator++;
            }
        }
        tvResponse.setText("finalizado");
        hideProgress();
        startActivity(new Intent(this, MapsActivity.class));
    }

    @Override
    public void showProgress() {
        if (progressBar.getVisibility() == View.INVISIBLE)
            progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        if (progressBar.getVisibility() == View.VISIBLE)
            progressBar.setVisibility(View.INVISIBLE);
    }
}
