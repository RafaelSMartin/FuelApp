package com.rsmartin.fuelapp.db.database;

import android.os.AsyncTask;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;

import java.util.List;

public class FindAllListaPrecioWraperTask extends AsyncTask<Void, Void, List<ListaEESSPrecioWraper>> {

    @Override
    protected List<ListaEESSPrecioWraper> doInBackground(Void... voids) {
        return AppDB.getInstance(App.getInstance().getApplicationContext())
                .listaEESSPrecioWraperDAO().findAllListaPrecioWraper();
    }
}
