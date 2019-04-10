package project1.mobile.cs.fsu.edu.project1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements FriendListFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener{
    private FusedLocationProviderClient fusedLocationClient;
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    DBHelper dbHelper=new DBHelper();
    ArrayList <User>  users = new ArrayList<>();
    User login_user;

    // Notifications
    NotificationManager nm;
    Notification.Builder builder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        login_user = getIntent().getParcelableExtra("login_user");
        getUsers();
        fusedLocationClient= LocationServices.getFusedLocationProviderClient(this);

        // Starting fragment is FriendList
        intentFriendListFragment();

        ///// Notifications
        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        // 1st Notification && 2nd Notifications Declaration
        builder = new Notification.Builder(getApplicationContext());
        //check if user grants location permission, then update database accordingly
        if ( ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION ) == PackageManager.PERMISSION_GRANTED ) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    String temp=(Double.toString(location.getLatitude())+","+Double.toString(location.getLongitude()));
                    dbHelper.updateUserLocation(Integer.toString(login_user.getId()),temp);
                }
            });
        }
        else{
            //if user has not granted location permission give them dummy location
            String temploc=new String("48.067222"+","+"12.863611");
            dbHelper.updateUserLocation(Integer.toString(login_user.getId()),temploc);
        }


    }



    //Gets list of users from database
    public void getUsers(){
        db.getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot res : dataSnapshot.getChildren()){

                        users.add(res.getValue(User.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }



    @Override
    public void intentFriendListFragment() {
        // Starting variables//////////////
        FriendListFragment viewFragment = new FriendListFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("users", users);
        bundle.putParcelable("login_user", login_user);
        viewFragment.setArguments(bundle);

        // Added back stack
        trans.addToBackStack("friendFragment");
        trans.replace(R.id.frame_container, viewFragment);
        trans.commit();
    }

    @Override
    public void intentSettingsFragment() {
        // Starting variables//////////////
        SettingsFragment viewFragment = new SettingsFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

        // Added back stack
        trans.addToBackStack("settingsFragment");
        trans.replace(R.id.frame_container, viewFragment);
        trans.commit();

    }

    public void OnGoMap(View view)
    {
        Intent myintent = new Intent(this, MapsActivity.class);
        startActivity(myintent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuSettings:
                intentSettingsFragment();
                return true;
            case R.id.menuLogout:
                Intent myOtherIntent = new Intent(this, StartActivity.class);
                startActivity(myOtherIntent);
                return true;
        }
        return true;

    }

    public void notifySingle()
    {
        /*
        String friendMsg = "I See You";

        builder.setContentTitle("Friend is Found");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        builder.setContentText(friendMsg);
        Toast.makeText(this, "Notification Test", Toast.LENGTH_SHORT).show();
        nm.notify(1,builder.build());
        */

        // Notification Variables
        NotificationHelper noti;
        noti = new NotificationHelper(this);

        // Change these strings for the msg
        String title = "This is Title";
        String content = "This is content";

        Notification.Builder nb = noti.getNotification1(title, content);
        noti.notify(111,nb);

    }

    @Override
    public User getUserDB(User status) {
        return null;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
