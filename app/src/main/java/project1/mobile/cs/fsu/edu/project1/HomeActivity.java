package project1.mobile.cs.fsu.edu.project1;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
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
        Log.d(TAG, "Hello from HomeActivity "+mainUser.getName()+"!");
    }

}
