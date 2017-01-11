package gwicks.com.ebayproject;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by gwicks on 9/01/2017.
 */

// Class to show map of earthquake
// receives intent from MainActivity


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    double latitude;
    double longitude;
    LatLng position;
    String iD;
    double magitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Receiving latitude from MainActivity screen
        latitude = getIntent().getDoubleExtra("lat", 0);

        // Receiving longitude from MainActivity screen
        longitude = getIntent().getDoubleExtra("long", 0);

        // Get extras passed with intent
        iD = getIntent().getStringExtra("id");
        magitude = getIntent().getDoubleExtra("mag", 0);

        position = new LatLng(latitude, longitude);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        GoogleMap mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));

        // Customer marker to display epicenter location

        Marker epicenter = mMap.addMarker(new MarkerOptions()
                .anchor(0.5f, 0.5f)
                .position(position)
                .snippet("Eqid: " + iD)
                .snippet("Magitude: " + magitude)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.epicenter)));

    }

}
