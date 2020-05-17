package com.example.favorite_places.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.android.gms.maps.model.LatLng;

import java.net.URL;

import lombok.Data;

@Data
@Entity
public class Place {

    @PrimaryKey(autoGenerate = true) @NonNull
    private int id;

    @NonNull
    private LatLng latLng;

    private String name;

    private String desc;

    private URL imgUrl;

    public Place(LatLng latLng) {
        this.latLng = latLng;
    }
}
