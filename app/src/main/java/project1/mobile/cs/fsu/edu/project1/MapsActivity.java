package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private User mapUser;
    private User loginUser;
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
        Intent intent = getIntent();
        mapUser = intent.getParcelableExtra("mapUser");
        loginUser = intent.getParcelableExtra("login_user");

        String[] latlngstr = mapUser.getLocation().split(",");

        lat = Double.parseDouble(latlngstr[0]);
        lng = Double.parseDouble(latlngstr[1]);

        Switch notify = findViewById(R.id.add_as_notified);

        notify.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    DBHelper dbHelper = new DBHelper();
                    //dbHelper.setFriendList(String.valueOf(loginUser.getId()));
                    dbHelper.addFriendList(getApplicationContext(),String.valueOf(loginUser.getId()),
                            String.valueOf(mapUser.getId()));
                    dbHelper.GetUserLocation(String.valueOf(mapUser.getId()), getApplicationContext());
                }
                else{
                    DBHelper dbHelper = new DBHelper();
                    dbHelper.removeFromFriendList(getApplicationContext(),String.valueOf(loginUser.getId()),
                            String.valueOf(mapUser.getId()));
                }
            }
        });
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
