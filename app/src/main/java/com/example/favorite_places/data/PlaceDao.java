package com.example.favorite_places.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlaceDao {

    @Insert
    void insert(Place... places);

    @Update
    void update(Place... places);

    @Delete
    void delete(Place... places);

    @Query("Select * from place")
    LiveData<List<Place>> getAll();

    @Query("Select * from place where id = :id")
    LiveData<Place> getById(int id);
}
