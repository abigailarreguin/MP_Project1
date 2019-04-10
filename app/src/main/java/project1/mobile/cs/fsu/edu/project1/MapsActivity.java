package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private User mapUser;
    private GoogleMap mMap;
    double lat,lng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Intent intent=getIntent();
        mapUser=intent.getParcelableExtra("mapUser");
        String[] latlngstr=mapUser.getLocation().split(",");
        //wizardry if userlocation comes in as string
        //latlngstr[0]=latlngstr[0].replaceAll("[^\\\\.0123456789]","");
        //latlngstr[1]=latlngstr[1].replaceAll("[^\\\\.0123456789]","");
       lat= Double.parseDouble(latlngstr[0]);
        lng=Double.parseDouble(latlngstr[1]);
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
        LatLng user = new LatLng(lat,lng);
        mMap.addMarker(new MarkerOptions().position(user).title("Heres "+mapUser.getName()+"!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(user));
    }
}
