package project1.mobile.cs.fsu.edu.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        DBHelper dbhelp=new DBHelper();
        dbhelp.getUserName("iuU44hGA33c4A1uBnxcL");
    }
}
