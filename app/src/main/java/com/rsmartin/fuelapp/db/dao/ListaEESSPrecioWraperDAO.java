package com.rsmartin.fuelapp.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rsmartin.fuelapp.db.entity.ListaEESSPrecioWraper;

import java.util.List;

@Dao
public interface ListaEESSPrecioWraperDAO {

    @Insert
    void insertListaPrecioWraper(ListaEESSPrecioWraper listaEESSPrecioWraper);

    @Query("SELECT * FROM fuelapp")
    List<ListaEESSPrecioWraper> findAllListaPrecioWraper();

    @Update
    void updateListaPrecioWraper(ListaEESSPrecioWraper listaEESSPrecioWraper);


}
