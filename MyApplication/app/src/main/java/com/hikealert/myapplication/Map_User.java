package com.hikealert.myapplication;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.Manifest;
import android.support.v4.content.ContextCompat;
import android.content.pm.PackageManager;
import android.content.Context;
import android.location.Criteria;
import com.google.android.gms.maps.model.CameraPosition;
import android.location.LocationManager;

public class Map_User extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int TAG_CODE_PERMISSION_LOCATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map__user);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
      @Override
      public void onMapReady(GoogleMap googleMap) {
          mMap = googleMap;
          if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) ==
                  PackageManager.PERMISSION_GRANTED &&
                  ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) ==
                          PackageManager.PERMISSION_GRANTED) {
              googleMap.setMyLocationEnabled(true);
              googleMap.getUiSettings().setMyLocationButtonEnabled(true);
              LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
              Criteria criteria = new Criteria();
              mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

              Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
              if (location != null)
              {
                  googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(location.getLatitude(), location.getLongitude()), 13.0f));

                  CameraPosition cameraPosition = new CameraPosition.Builder()
                          .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                          .zoom(18)                   // Sets the zoom
                          .bearing(0)                // Sets the orientation of the camera to east
                          .tilt(0)                   // Sets the tilt of the camera (degrees)
                          .build();                   // Creates a CameraPosition from the builder
                  googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
              }
          }
          else {
              ActivityCompat.requestPermissions(this, new String[] {
                              Manifest.permission.ACCESS_FINE_LOCATION,
                              Manifest.permission.ACCESS_COARSE_LOCATION },
                      TAG_CODE_PERMISSION_LOCATION);
          }
      }
}