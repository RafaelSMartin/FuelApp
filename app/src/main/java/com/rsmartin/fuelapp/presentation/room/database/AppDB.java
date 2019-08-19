package com.rsmartin.fuelapp.presentation.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rsmartin.fuelapp.IExtras;
import com.rsmartin.fuelapp.presentation.room.dao.GasolinerasDAO;
import com.rsmartin.fuelapp.presentation.room.entity.DatosGasolineraEntity;

@Database(entities = {DatosGasolineraEntity.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    private static AppDB instance;

    public abstract GasolinerasDAO gasolinerasDAO();

    public static AppDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDB.class,
                    IExtras.NAME_TABLE)
//                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public static void destroyInstance() {
        instance = null;
    }

}
