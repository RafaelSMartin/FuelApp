package com.rsmartin.fuelapp.db.database;

import android.os.AsyncTask;

import com.rsmartin.fuelapp.App;
import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;

import java.util.List;

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
