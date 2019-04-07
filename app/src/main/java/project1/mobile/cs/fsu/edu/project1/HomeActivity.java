package project1.mobile.cs.fsu.edu.project1;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity implements FriendListFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Starting fragment is FriendList
        intentFriendListFragment();
        //intentSettingsFragment();

    }

    @Override
    public void intentFriendListFragment() {
        // Starting variables//////////////
        FriendListFragment viewFragment = new FriendListFragment();

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();

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
}
