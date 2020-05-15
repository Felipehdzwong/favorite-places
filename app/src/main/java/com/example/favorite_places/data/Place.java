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
    private String name;

    @NonNull
    private String desc;

    public Place(@NonNull String name, @NonNull String desc) {
        this.name = name;
        this.desc = desc;
    }
}
