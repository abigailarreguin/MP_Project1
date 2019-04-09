package project1.mobile.cs.fsu.edu.project1;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity implements FriendListFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener{

    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    ArrayList <User>  users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        User login_user = getIntent().getParcelableExtra("login_user");
        getUsers();
        // Starting fragment is FriendList
        intentFriendListFragment();
        //intentSettingsFragment();

    }

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
                // BRANDON - start settings fragment here
                return true;
            case R.id.menuLogout:
                Intent myOtherIntent = new Intent(this, StartActivity.class);
                startActivity(myOtherIntent);
                return true;
        }
        return true;
    }

}
