package com.example.favorite_places.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.favorite_places.R;
import com.example.favorite_places.data.Place;

import java.util.ArrayList;

public class PlaceAdapter extends ArrayAdapter<Place> {

    public PlaceAdapter(@NonNull Context context, ArrayList<Place> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.place_row, parent, false);
        }

        // Get the data item for this position
        Place place = getItem(position);

        // Lookup view for data population
        TextView tvName = (TextView) convertView.findViewById(R.id.place_row__tv_name);
        TextView tvDesc = (TextView) convertView.findViewById(R.id.place_row__tv_desc);
        // Populate the data into the template view using the data object
        tvName.setText(place.getName());
        tvDesc.setText(place.getDesc());
        // Return the completed view to render on screen
        return convertView;
    }
}
