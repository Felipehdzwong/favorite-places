package com.example.favorite_places.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.favorite_places.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private final Context mContext;

    public CustomInfoWindowAdapter(Context mContext) {
        this.mContext = mContext;
        mWindow = LayoutInflater.from(mContext).inflate(R.layout.custom_info_window, null);
    }

    private void renderWindowText(Marker marker, View view){
        String title = marker.getTitle();
        TextView tvTitle = view.findViewById(R.id.custom_info_window__tv_title);
        if(!title.equals("")){
            tvTitle.setText(title);
        }

        String snippet = marker.getSnippet();
        TextView tvSnippet = view.findViewById(R.id.custom_info_window__tv_snippet);
        if(!snippet.equals("")){
            tvSnippet.setText(snippet);
        }


    }

    @Override
    public View getInfoWindow(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        renderWindowText(marker, mWindow);
        return mWindow;
    }
}
