package com.example.myapplicationroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Beer.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;
    public abstract BeerDao beerDao();


}
