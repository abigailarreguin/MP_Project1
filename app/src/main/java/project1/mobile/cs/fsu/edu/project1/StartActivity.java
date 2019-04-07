package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button loginButton = (Button)(findViewById(R.id.loginButton));
         //Write a message to the database
        FirebaseApp.initializeApp(this);
       FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("testMSG");
       myRef.setValue(Calendar.getInstance().getTime().toString());
       DBHelper dbHelper=new DBHelper();
       //dbHelper.getUserName("testUser");
        User testUser=new User(1234,"TestUser1","999-999-9999","Johnny TestUser","OsceolaCoords",true);
       dbHelper.putUser(testUser.getId(),testUser);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
