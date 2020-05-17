package com.example.favorite_places.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.favorite_places.R;
import com.example.favorite_places.data.Place;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final PlaceViewModel placeViewModel = new PlaceViewModel(getActivity().getApplication());
        mMap = googleMap;
        mMap.setOnMapClickListener(latLng -> {
            // Add marker in map
            mMap.addMarker(new MarkerOptions().position(latLng).title("Click to edit details"));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            // Persist place in db
            Place place = new Place(latLng);
            placeViewModel.insertNewPlace(place);
        });
        mMap.setOnInfoWindowClickListener(this);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Fragment detailsFragment = new DetailsFragment();
        assert getFragmentManager() != null;
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, detailsFragment).commit();
        Toast.makeText(getActivity(), "Info!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MapView mapView = (MapView) view.findViewById(R.id.fragment_map__map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }
}