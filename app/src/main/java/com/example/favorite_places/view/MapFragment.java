package com.example.favorite_places.view;

import android.os.Bundle;
import android.util.Log;
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

    private double lat, lng;
    private GoogleMap mMap;
    private static final String MARKER_DEFAULT_TITLE = "Click me!";
    private static final String MARKER_DEFAULT_SNIPPET = "Click to edit and save place details.";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final PlaceViewModel placeViewModel = new PlaceViewModel(getActivity().getApplication());
        fillMapLocations(placeViewModel);
        mMap = googleMap;
        mMap.setOnMapClickListener(latLng -> {
            // Add marker in map
            mMap.addMarker(new MarkerOptions().position(latLng).title(MARKER_DEFAULT_TITLE).snippet(MARKER_DEFAULT_SNIPPET)).showInfoWindow();
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

            lat = latLng.latitude;
            lng = latLng.longitude;
        });
        mMap.setOnInfoWindowClickListener(this);
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if(marker.getTitle().equals(MARKER_DEFAULT_TITLE) && marker.getSnippet().equals(MARKER_DEFAULT_SNIPPET)){
            marker.hideInfoWindow();
            Bundle result = new Bundle();
            result.putDouble("key_lat", lat);
            result.putDouble("key_lng", lng);
            getParentFragmentManager().setFragmentResult("requestKey", result);

            DetailsDialog detailsDialog = new DetailsDialog();
            detailsDialog.show(getParentFragmentManager(), "detailsDialog");
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        MapView mapView = (MapView) view.findViewById(R.id.fragment_map__map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }

    private void fillMapLocations(PlaceViewModel placeViewModel) {
        placeViewModel.getAllPlaces().observe(this, places -> {
            for (Place place : places){
                mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(getContext(), place));
                LatLng location = new LatLng(place.getLat(), place.getLng());
                mMap.addMarker(new MarkerOptions().position(location).title(place.getName()).snippet(place.getDesc()));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            }
        });
    }
}