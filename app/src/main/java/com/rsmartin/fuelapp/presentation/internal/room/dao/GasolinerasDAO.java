package com.rsmartin.fuelapp.presentation.internal.room.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.fuelapp.domain.model.DatosGasolinera;

import java.util.List;

@Dao
public interface GasolinerasDAO {

    @Insert
    void insertPrecioGasolinera(DatosGasolinera datosGasolinera);

    @Query("SELECT * FROM fuelapp ")
    List<DatosGasolinera> findAllPreciosGasolineras();

    @Query("DELETE FROM fuelapp")
    void deleteAllPreciosGasolineras();

    @Query("SELECT * FROM fuelapp WHERE favorite = :favorite")
    List<DatosGasolinera> findAllFavorites(boolean favorite);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updatePreciosGasolinera(DatosGasolinera datosGasolinera);

}
