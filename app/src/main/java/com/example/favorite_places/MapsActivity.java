package com.example.favorite_places;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;

        switch (item.getItemId()) {
            case R.id.mi_map:
                Toast.makeText(this, "Map item selected!", Toast.LENGTH_SHORT).show();
                selectedFragment = new MapFragment();
                break;
            case R.id.mi_list:
                Toast.makeText(this, "List item selected!", Toast.LENGTH_SHORT).show();
                selectedFragment = new ListFragment();
                break;
            case R.id.mi_info:
                Toast.makeText(this, "Info item selected!", Toast.LENGTH_SHORT).show();
                selectedFragment = new InfoFragment();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

        return true;
    }
}
