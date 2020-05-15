package com.example.favorite_places.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.favorite_places.data.Place;
import com.example.favorite_places.data.PlaceDao;

@Database(entities = {Place.class}, version = 1)
public abstract class PlaceDb extends RoomDatabase {
    public abstract PlaceDao getPlaceDao();
}
