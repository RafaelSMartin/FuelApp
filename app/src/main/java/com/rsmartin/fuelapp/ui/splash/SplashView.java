package com.rsmartin.fuelapp.ui.splash;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.R;
import com.rsmartin.fuelapp.Utils.SharedPref;
import com.rsmartin.fuelapp.db.database.AppDB;
import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;
import com.rsmartin.fuelapp.remote.ApiDataGob.ModeloListaGasolineras;
import com.rsmartin.fuelapp.ui.maps.MapsActivity;

import java.util.ArrayList;
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

        if (!SharedPref.getInstance().getBooleanPreferences(IExtras.IS_NOT_FIRST_TIME)) {
            SharedPref.getInstance().saveBooleanPreferences(IExtras.IS_NOT_FIRST_TIME, true);
            presenter.getOilsGob();
        } else {
            FindAllListaPrecioWraperTask findAllListaPrecioWraperTask = new FindAllListaPrecioWraperTask();
            findAllListaPrecioWraperTask.execute();
        }
    }

    @Override
    public void showResult(List<ListaEESSPrecioWraper> wraperList) {
        InsertListaPrecioWraperTask insertListaPrecioWraperTask = new InsertListaPrecioWraperTask();
        insertListaPrecioWraperTask.execute(wraperList);

        showResultFromRoom(new ModeloListaGasolineras(wraperList));
    }

    public void showResultFromRoom(ModeloListaGasolineras list) {

        ModeloListaGasolineras newModelpList = new ModeloListaGasolineras(new ArrayList<>());
        List<ListaEESSPrecioWraper> lista = new ArrayList<>();
        int iterator = 0;

        for (ListaEESSPrecioWraper item : list.getListaEESSPrecioWrapers()) {
            if (iterator <= 100) {
                lista.add(item);
                iterator++;
            }
        }
        newModelpList.setListaEESSPrecioWrapers(lista);

        tvResponse.setText("finalizado");
        hideProgress();


        Intent i = new Intent(this, MapsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("value", newModelpList);
        i.putExtras(bundle);
        startActivity(i);
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

    public class FindAllListaPrecioWraperTask extends AsyncTask<Void, Void, List<ListaEESSPrecioWraper>> {

        @Override
        protected List<ListaEESSPrecioWraper> doInBackground(Void... voids) {
            return AppDB.getInstance(App.getInstance().getApplicationContext())
                    .listaEESSPrecioWraperDAO().findAllListaPrecioWraper();
        }

        @Override
        protected void onPostExecute(List<ListaEESSPrecioWraper> lists) {
            showResultFromRoom(new ModeloListaGasolineras(lists));
        }
    }

    public class InsertListaPrecioWraperTask extends AsyncTask<List<ListaEESSPrecioWraper>, Void, Void> {
        @Override
        protected Void doInBackground(List<ListaEESSPrecioWraper>... lists) {

            for (ListaEESSPrecioWraper item : lists[0]) {
                AppDB.getInstance(App.getInstance().getApplicationContext())
                        .listaEESSPrecioWraperDAO().insertListaPrecioWraper(item);
            }
            return null;
        }
    }



}
