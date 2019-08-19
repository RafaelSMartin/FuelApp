package com.rsmartin.fuelapp.presentation.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.fuelapp.domain.model.DatosGasolinera;

import java.util.List;

@Dao
public interface GasolinerasDAO {

    @Insert
    void insertPrecioGasolinera(DatosGasolinera datosGasolinera);

    @Query("SELECT * FROM fuelapp")
    List<DatosGasolinera> findAllPreciosGasolineras();

    @Update
    void updatePreciosGasolinera(DatosGasolinera datosGasolinera);


}
