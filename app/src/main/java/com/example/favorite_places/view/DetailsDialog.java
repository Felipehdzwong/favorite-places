package com.example.favorite_places.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentResultListener;

import com.example.favorite_places.R;
import com.example.favorite_places.data.Place;

public class DetailsDialog extends DialogFragment {

    private Place place = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final PlaceViewModel placeViewModel = new PlaceViewModel(getActivity().getApplication());
        View view = inflater.inflate(R.layout.dialog_details, container, false);

        // widgets
        EditText etPlaceName = view.findViewById(R.id.et_name);
        EditText etPlaceDesc = view.findViewById(R.id.et_desc);
        EditText etPlaceImgUrl = view.findViewById(R.id.et_image);
        Button btnSave = view.findViewById(R.id.btn_save);
        Button btnCancel = view.findViewById(R.id.btn_cancel);

        btnCancel.setOnClickListener(v -> getDialog().dismiss());

        btnSave.setOnClickListener(v -> {
            String name = etPlaceName.getText().toString();
            String desc = etPlaceDesc.getText().toString();
            String imgUrl = etPlaceImgUrl.getText().toString();

            if (!name.isEmpty() && !name.trim().equals("")){
                place.setName(name);
                place.setDesc(desc);
                place.setImgUrl(imgUrl);
                placeViewModel.insertNewPlace(place);
                getDialog().dismiss();
                Toast.makeText(getContext(), "Place saved.", Toast.LENGTH_SHORT).show();
                // maybe this fragment shouldn't be call here. MapFragment should update itself after closing dialog
                getParentFragmentManager().beginTransaction().replace(R.id.fragment_container, new MapFragment()).commit();
            }else {
                Toast.makeText(getContext(), "Enter a valid name.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                double lat = result.getDouble("key_lat");
                double lng = result.getDouble("key_lng");

                place = new Place(lat, lng);
            }
        });
    }
}
