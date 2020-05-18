package com.example.favorite_places.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.favorite_places.R;
import com.example.favorite_places.adapter.PlaceRecyclerAdapter;
import com.example.favorite_places.data.Place;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        // Instantiate view model
        PlaceViewModel placeViewModel = new PlaceViewModel(getActivity().getApplication());
        // Create places list
        List<Place> placeList = new ArrayList<>();

        // Recycler view set up
        final PlaceRecyclerAdapter adapter = new PlaceRecyclerAdapter(getContext(), R.layout.place_row, placeList);
        RecyclerView recyclerView = view.findViewById(R.id.fragment_list__rv_places);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        placeViewModel.getAllPlaces().observe(getViewLifecycleOwner(), new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> places) {
                adapter.setData(places);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
