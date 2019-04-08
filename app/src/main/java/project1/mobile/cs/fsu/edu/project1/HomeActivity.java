package project1.mobile.cs.fsu.edu.project1;

import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import java.util.HashMap;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
public final static String TAG="MAINACT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

      // DBHelper dbhelp=new DBHelper();
      // dbhelp.getUserName("iuU44hGA33c4A1uBnxcL");
       //Toast.makeText(this, "Hello "+s+"!", Toast.LENGTH_SHORT).show();
    }
}
