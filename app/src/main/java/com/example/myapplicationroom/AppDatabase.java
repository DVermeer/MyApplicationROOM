package com.example.myapplicationroom;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Beer.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BeerDao beerDao();


}
