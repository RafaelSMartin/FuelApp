package com.rsmartin.fuelapp.db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.db.dao.ListaEESSPrecioWraperDAO;
import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;

@Database(entities = {ListaEESSPrecioWraper.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    public abstract ListaEESSPrecioWraperDAO listaEESSPrecioWraperDAO();

    public static AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class,
                    IExtras.NAME_TABLE)
//                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destryInstance() {
        instance = null;
    }

}
