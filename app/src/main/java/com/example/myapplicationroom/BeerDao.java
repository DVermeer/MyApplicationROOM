package com.example.myapplicationroom;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface BeerDao {
    @Query("SELECT * FROM beer")
    List<Beer> getAllBeers();

    @Query("SELECT * FROM beer where Amount > 0")
    List<Beer> getOnStockBeers();


    @Insert
    void insertALL(Beer... beers);

    @Update
    void updateBeers(Beer... beers);
}
