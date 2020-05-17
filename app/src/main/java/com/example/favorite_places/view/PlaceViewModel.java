package com.example.favorite_places.view;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.favorite_places.data.Place;
import com.example.favorite_places.repo.PlaceRepo;

import java.util.List;

public class PlaceViewModel extends AndroidViewModel {

    private PlaceRepo placeRepo;
    private LiveData<List<Place>> places;

    public PlaceViewModel(@NonNull Application application) {
        super(application);
        placeRepo = new PlaceRepo(application.getApplicationContext());
        places = placeRepo.getPlaces();
    }

    public void insertNewPlace(Place place){
        placeRepo.addPlace(place);
    }

    public LiveData<List<Place>> getAllPlaces(){
        return places;
    }

    public LiveData<Place> getPlaceById(int id){
        return placeRepo.getPlaceById(id);
    }

    public void updatePlace(Place place){
        placeRepo.updatePlace(place);
    }

    public void deletePlace(Place place) {
        placeRepo.deletePlace(place);
    }
}
