package gwicks.com.ebayproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    double latitude;
    double longitude;
    LatLng position;
    String iD;
    double magitude;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

//        Intent receiveIntent = this.getIntent();
//
//        Bundle b = receiveIntent.getExtras();
//
//        double latitude = b.getDouble("lat");
//        double longitude = b.getDouble("long");
//        LatLng position = new LatLng(latitude, longitude);
//        Toast.makeText(MapActivity.this, "The position is: " + position, Toast.LENGTH_LONG).show();



        // Receiving latitude from MainActivity screen
        latitude = getIntent().getDoubleExtra("lat", 0);

        // Receiving longitude from MainActivity screen
        longitude = getIntent().getDoubleExtra("long", 0);

        iD = getIntent().getStringExtra("id");
        magitude = getIntent().getDoubleExtra("mag", 0);

        position = new LatLng(latitude, longitude);
        Toast.makeText(MapActivity.this, "The position is: " + position, Toast.LENGTH_LONG).show();
//        MarkerOptions options = new MarkerOptions();
//
//        options.position(position);
//        options.title("Position");



        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent i = getIntent();


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.setMyLocationEnabled(true);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.addMarker(new MarkerOptions().position(position).title("Current Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5));;

        Marker epicenter =  mMap.addMarker(new MarkerOptions()
                                    .anchor(0.5f,0.5f)
                                    .position(position)
                                    .snippet("Eqid: " + iD)
                                    .snippet("Magitude: " + magitude)
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.epicenter)));




    }


}
