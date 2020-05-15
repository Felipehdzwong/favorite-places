package com.example.favorite_places.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favorite_places.data.Place;
import com.example.favorite_places.viewholder.PlaceViewHolder;

import java.util.List;

public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceViewHolder> {

    private Context context;
    private int layoutResource ;
    private List<Place> placeList;

    public PlaceRecyclerAdapter(Context context, int layoutResource, List<Place> placeList) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public PlaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creates the 'ViewHolder' to be used for the whole list
        View view = LayoutInflater.from(context).inflate(layoutResource, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaceViewHolder holder, int position) {
        // allows to fill in the row widget with actual data
        holder.bindData(position, placeList.get(position));
    }

    @Override
    public int getItemCount() {
        // sets up the amount of elements to display on the list
        return placeList.size();
    }
}
