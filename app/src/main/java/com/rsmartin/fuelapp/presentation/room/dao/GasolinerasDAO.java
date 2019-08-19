package com.rsmartin.fuelapp.presentation.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.fuelapp.presentation.room.entity.DatosGasolineraEntity;

import java.util.List;

@Dao
public interface GasolinerasDAO {

    @Insert
    void insertPrecioGasolinera(DatosGasolineraEntity datosGasolineraEntity);

    @Query("SELECT * FROM fuelapp")
    List<DatosGasolineraEntity> findAllPreciosGasolineras();

    @Update
    void updatePreciosGasolinera(DatosGasolineraEntity datosGasolineraEntity);


}
