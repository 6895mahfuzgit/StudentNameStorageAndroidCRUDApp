package com.game.android.mahfuzcse11.gpsandmap;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //  mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng sydney2 = new LatLng(-34.01, 152);
        LatLng sydney3 = new LatLng(-34.02, 150);
        LatLng sydney4 = new LatLng(-34.04, 151.11);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney").snippet("Here Where i live"));


        mMap.addCircle(new CircleOptions()
                .center(sydney)
                .strokeColor(Color.RED)
                .radius(200)
                .fillColor(Color.BLUE));

//
//        mMap.addPolyline(new PolylineOptions()
//                .color(Color.BLACK)
//                .width(2)
//                .add(sydney, sydney2)
//                .geodesic(true));
//

        mMap.addPolygon(new PolygonOptions().add(sydney, sydney2, sydney3, sydney4).fillColor(Color.CYAN));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 14));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
    }
}
