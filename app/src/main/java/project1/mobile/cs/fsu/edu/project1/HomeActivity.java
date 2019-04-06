package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    public void OnGoMap(View view)
    {
        Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(intent);
    }
}
