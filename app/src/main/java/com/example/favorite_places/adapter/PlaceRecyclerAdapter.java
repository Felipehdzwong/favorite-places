package com.example.favorite_places.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favorite_places.R;
import com.example.favorite_places.data.Place;
import com.example.favorite_places.repo.PlaceRepo;

import java.util.List;

public class PlaceRecyclerAdapter extends RecyclerView.Adapter<PlaceRecyclerAdapter.ViewHolder> {

    private Context context;
    private int layoutResource ;
    private List<Place> placeList;

    public PlaceRecyclerAdapter(Context context, int layoutResource, List<Place> placeList) {
        this.context = context;
        this.layoutResource = layoutResource;
        this.placeList = placeList;
    }

    public void setData(List<Place> places){
        this.placeList = places;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // creates the 'ViewHolder' to be used for the whole list
        View view = LayoutInflater.from(context).inflate(layoutResource, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // allows to fill in the row widget with actual data
        holder.bindData(position, placeList.get(position));
    }

    @Override
    public int getItemCount() {
        // sets up the amount of elements to display on the list
        return placeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgPlace;
        private ImageView imgDelete;
        private TextView tvPlaceName;
        private TextView tvPlaceDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imgPlace = itemView.findViewById(R.id.place_row__img_place);
            this.imgDelete = itemView.findViewById(R.id.place_row__img_delete);
            this.tvPlaceName = itemView.findViewById(R.id.place_row__tv_name);
            this.tvPlaceDesc = itemView.findViewById(R.id.place_row__tv_desc);

            imgDelete.setOnClickListener(this);
        }

        public void bindData(int position, Place place) {
            imgPlace.setImageResource(R.drawable.ic_image_black_24dp);
            imgDelete.setImageResource(R.drawable.ic_delete_black_24dp);
            tvPlaceName.setText(place.getName());
            tvPlaceDesc.setText(place.getDesc());
        }

        @Override
        public void onClick(View v) {
            Place place = placeList.get(getAdapterPosition());
            PlaceRepo placeRepo = new PlaceRepo(v.getContext());
            placeRepo.deletePlace(place);
            Toast.makeText(v.getContext(), "Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}
