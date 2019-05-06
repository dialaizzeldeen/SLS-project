package com.example.seamlessshopping;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    static final LatLng HAMBURG = new LatLng(53.558, 9.927);
    static final LatLng KIEL = new LatLng(Coordinates.FirstlatCoordinates, Coordinates.FirstlongCoordinates);
    private SupportMapFragment map;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Toast.makeText(this, "start", Toast.LENGTH_LONG).show();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(new MarkerOptions()
                .position(KIEL)
                .title("Marker"));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(KIEL, 15));

// Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                // Creating a marker
                MarkerOptions markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Setting the title for the marker.
                // This will be displayed on taping the marker
                markerOptions.title(latLng.latitude + " : " + latLng.longitude);

              //  Log.d("myLatitudeeeeeemap", "d" + latLng.latitude + " : " + latLng.longitude);
                //   Coordinates.FirstlatCoordinates=location.getLatitude();
                // Coordinates.FirstlongCoordinates=location.getLongitude();
                //Log.d("dddddd",""+Coordinates.FirstAddressCoordinates);
                Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                List<Address> addresses;

                try {
                    addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);

        String address = addresses.get(0).getAddressLine(0);
String area =addresses.get(0).getSubAdminArea();
                     String city = addresses.get(0).getLocality();
                    String add = addresses.get(0) + "";
                    Log.d("city", "d" + "addresses" + add);
                    Coordinates.AddressCoordinates = "City"+ city+"address"+address +area;
                    Coordinates.distance = distance(Coordinates.FirstlatCoordinates, Coordinates.FirstlongCoordinates,32.111, 33.123);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                // Coordinates.AddressCoordinates=new LatLng(latLng.latitude,latLng.longitude);
                //Toast.makeText(getApplicationContext(),"address"+Coordinates.AddressCoordinates,Toast.LENGTH_LONG).show();
                // Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);
            }
        });

    }

    public void onsave(View v) {
        Intent i = new Intent(this, questions.class);
       Toast.makeText(this,"Coordinates.distance"+"d" +Coordinates.distance,Toast.LENGTH_LONG).show();

        startActivity(i);


    }


    private double distance(double lat1, double lng1, double lat2, double lng2) {

        double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);

        double sindLat = Math.sin(dLat / 2);
        double sindLng = Math.sin(dLng / 2);

        double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        double dist = earthRadius * c;

        return dist; // output distance, in MILES
    }

}
