package com.example.favorite_places.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Data
@Entity
public class Place {

    @PrimaryKey(autoGenerate = true) @NonNull
    private int id;

    @NonNull
    private double lat;

    @NonNull
    private double lng;

    private String name;

    private String desc;

    private String imgUrl;

    public Place(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
