package project1.mobile.cs.fsu.edu.project1;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity implements FriendListFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener{

    // Notifications
    NotificationManager nm;
    Notification.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ///// Starting fragment is FriendList
        //intentFriendListFragment();
        intentSettingsFragment();


        ///// Notifications
        nm = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        // 1st Notification && 2nd Notifications Declaration
        builder = new Notification.Builder(getApplicationContext());
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

    public void notifySingle()
    {
        builder.setContentTitle("Friend is Found");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setWhen(System.currentTimeMillis());
        nm.notify(1,builder.build());
    }
}
