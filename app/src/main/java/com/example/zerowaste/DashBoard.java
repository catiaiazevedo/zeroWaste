package com.example.zerowaste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DashBoard extends AppCompatActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback{
    private static final String MORADA = "morada";
    private static final String MAGIC_BOX = "magic_box";
    private static final String PRECO = "preco";
    private static final String TEL = "tel";

    String morada;
    String magic_box;
    String preco;
    String tel;
    GoogleMap gMap;

    private final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);

    private Marker markerPerth;
    private Marker markerSydney;
    private Marker markerBrisbane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        mapFragment.getMapAsync(this);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        //id.fragment
        /*SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragment);
        supportMapFragment.getMapAsync(this);*/

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId())
                {
                    case R.id.dashboard:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.about:
                        startActivity(new Intent(getApplicationContext()
                                ,About.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
        magic_box = getIntent().getStringExtra(MAGIC_BOX);
        morada = getIntent().getStringExtra(MORADA);
        preco = getIntent().getStringExtra(PRECO);
        tel = getIntent().getStringExtra(TEL);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.content_frame, TrayFragmentFinal.newInstance(magic_box,preco,morada,tel)).commit();
    }

    @Override
    public void onMapReady(GoogleMap map) {
        // Add some markers to the map, and add a data object to each marker.
        markerPerth = map.addMarker(new MarkerOptions()
                .position(PERTH)
                .title("Perth"));
        markerPerth.setTag(0);

        markerSydney = map.addMarker(new MarkerOptions()
                .position(SYDNEY)
                .title("Sydney"));
        markerSydney.setTag(0);

        markerBrisbane = map.addMarker(new MarkerOptions()
                .position(BRISBANE)
                .title("Brisbane"));
        markerBrisbane.setTag(0);

        // Set a listener for marker click.
        map.setOnMarkerClickListener(this);
    }

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        Integer clickCount = (Integer) marker.getTag();

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            Toast.makeText(this,
                    marker.getTitle() +
                            " has been clicked " + clickCount + " times.",
                    Toast.LENGTH_SHORT).show();
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
    }
}

  /*  @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        gMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //Marker
                MarkerOptions markerOptions = new MarkerOptions();

                //Set Marker Position
                markerOptions.position(latLng);
                //Set Latitude and Longitude On MArker
                markerOptions.title(latLng.latitude + " : "+ latLng.longitude);
                //clear prev pos
                gMap.clear();
                //zoom
                gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                //add Marker
                gMap.addMarker(markerOptions);
            }
        });
    }*/
