package com.example.favorite_places.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.favorite_places.data.Place;
import com.example.favorite_places.data.PlaceDao;

@Database(entities = {Place.class}, version = 1)
public abstract class PlaceDb extends RoomDatabase {

    private static PlaceDb placeDbInstance;

    public static synchronized PlaceDb getInstance(Context context){

        if (placeDbInstance == null){
            placeDbInstance = Room.databaseBuilder(context.getApplicationContext(),PlaceDb.class, "placedb").build();
        }

        return placeDbInstance;
    }

    public abstract PlaceDao getPlaceDao();
}
