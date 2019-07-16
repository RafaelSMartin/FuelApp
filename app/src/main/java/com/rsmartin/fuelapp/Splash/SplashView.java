package com.rsmartin.fuelapp.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rsmartin.fuelapp.Main.MainActivity;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.remote.ApiDataGob.ListaEESSPrecioWraper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashView extends AppCompatActivity implements ContractSplash.SplashView {

    private final String TAG = "SplashView";

    private SplashPresenter presenter;

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

        presenter.getOilsGob();

    }

    @Override
    public void showResult(List<ListaEESSPrecioWraper> wraperList) {
        tvResponse.setText("finalizado");
        hideProgress();
        startActivity(new Intent(this, MainActivity.class));
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
