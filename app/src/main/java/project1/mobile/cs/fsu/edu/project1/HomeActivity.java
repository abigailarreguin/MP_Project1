package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
public final static String TAG="MAINACT";
private User mainUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        mainUser=intent.getParcelableExtra("loginuser");
        Toast.makeText(this, "Hello "+mainUser.getName()+"!", Toast.LENGTH_SHORT).show();
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
