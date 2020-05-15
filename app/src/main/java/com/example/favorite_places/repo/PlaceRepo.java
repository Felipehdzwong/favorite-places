package com.example.favorite_places.repo;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.favorite_places.data.Place;
import com.example.favorite_places.db.PlaceDb;

import java.util.List;

public class PlaceRepo {

    private Context context;

    public PlaceRepo(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addPlace(final Place place){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() { PlaceDb.getInstance(context).getPlaceDao().insert(place); }
        });
    }

    public LiveData<List<Place>> getPlaces(){
        return PlaceDb.getInstance(context).getPlaceDao().getAll();
    }

    public void updatePlace(final Place place){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() { PlaceDb.getInstance(context).getPlaceDao().update(place); }
        });
    }

    public void deletePlace(final Place place){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() { PlaceDb.getInstance(context).getPlaceDao().delete(place); }
        });
    }
}
