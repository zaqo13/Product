package com.example.product.Dashboard;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.product.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap gMap;
    private List<MarkerOptions> markerOptionList;
    private Polygon polygaon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        markerOptionList = new ArrayList<>();

        // Add marker location
        markerOptionList.add(new MarkerOptions().position(new LatLng(19.9975, 73.7898)).title("Marker 1"));
        markerOptionList.add(new MarkerOptions().position(new LatLng(20.0109, 73.7868)).title("Marker 2"));
        markerOptionList.add(new MarkerOptions().position(new LatLng(20.004482, 73.8034)).title("Marker 3"));
        markerOptionList.add(new MarkerOptions().position(new LatLng(19.995365, 73.798791)).title("Marker 4"));


        // Get a handle to the fragment and register the callback.
        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gMap = googleMap;

    /*    gMap.addMarker(new MarkerOptions()
                .position(new LatLng(12.3456, 64.7890))
                .title("Arabian Sea"));*/

/*      LatLng mapPoint = new LatLng(18.5211, 73.8549);
        gMap.addMarker(new MarkerOptions().position(mapPoint).title("Pune"));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(mapPoint));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mapPoint, 20f)); // zoom in/ zoom out length; */

        // Additional map function buttons
        gMap.getUiSettings().setZoomControlsEnabled(true);      // show zoom control
        gMap.getUiSettings().setCompassEnabled(true);       // show compass
        gMap.getUiSettings().setMapToolbarEnabled(true);

//        gMap.setMyLocationEnabled(true);
        gMap.getUiSettings().setMyLocationButtonEnabled(true);      // show "My Location" button

        // Add marker to the map
        for (MarkerOptions markerOptions : markerOptionList) {
            gMap.addMarker(markerOptions);
        }

        // move the camera to first location and zoom the view to 14f
        if (!markerOptionList.isEmpty()) {
            LatLng firstMarkerLocation = markerOptionList.get(0).getPosition();
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(firstMarkerLocation, 14f));
        }


        PolygonOptions polygonOptions = new PolygonOptions();
        for (MarkerOptions markerOptions : markerOptionList) {
            polygonOptions.add(markerOptions.getPosition());
        }

        polygonOptions.strokeWidth(5f);
        polygonOptions.strokeColor(getResources().getColor(R.color.purple_700, null));
        polygonOptions.fillColor(getResources().getColor(R.color.grey_fill, null));
        polygaon = gMap.addPolygon(polygonOptions);

        Polyline polyline = gMap.addPolyline(new PolylineOptions()
                .add(new LatLng(19.996148, 73.801383), new LatLng(19.997251, 73.781038))
                .width(5f)
                .color(Color.RED));
    }

    /**
     * @param hasCapture True if the window has pointer capture.
     */
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}