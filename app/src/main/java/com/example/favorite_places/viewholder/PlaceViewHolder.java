package com.example.favorite_places.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favorite_places.R;
import com.example.favorite_places.data.Place;

public class PlaceViewHolder extends RecyclerView.ViewHolder {

    private ImageView imgPlace;
    private ImageView imgDelete;
    private TextView tvPlaceName;
    private TextView tvPlaceDesc;

    public PlaceViewHolder(@NonNull View itemView) {
        super(itemView);
        this.imgPlace = itemView.findViewById(R.id.place_row__img_place);
        this.imgDelete = itemView.findViewById(R.id.place_row__img_delete);
        this.tvPlaceName = itemView.findViewById(R.id.place_row__tv_name);
        this.tvPlaceDesc = itemView.findViewById(R.id.place_row__tv_desc);
    }

    public void bindData(int position, Place place) {
        imgPlace.setImageResource(R.drawable.ic_image_black_24dp);
        imgDelete.setImageResource(R.drawable.ic_delete_black_24dp);
        tvPlaceName.setText(place.getName());
        tvPlaceDesc.setText(place.getDesc());
    }

}
