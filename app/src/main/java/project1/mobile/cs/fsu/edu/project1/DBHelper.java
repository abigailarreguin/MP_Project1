package project1.mobile.cs.fsu.edu.project1;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
//import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.Console;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DBHelper {
    public final static String TAG="DBHELPER";
    private FirebaseDatabase db;

    DBHelper(){
        db=FirebaseDatabase.getInstance();
    }



    public void getUserName( String userID){
        db.getReference(userID).child("fName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uname =dataSnapshot.getValue().toString();
                Log.d(TAG, "user firstname: "+uname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Loading username failed");
            }
        });
    }
    public void getUser(String userID){

        db.getReference(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              for (DataSnapshot userSnapshot:dataSnapshot.getChildren()){
                  User temp=userSnapshot.getValue(User.class);
                  Log.d(TAG, "user got="+temp);
              }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void putUser(int userID, User inUser){
        db.getReference("users/"+userID).setValue(inUser);
    }
    public void GetUserLocation(String userID) {
        db.getReference(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    //LatLng temp = userSnapshot.getValue(User.location);
                    //Log.d(TAG, "user got=" + temp);
                }
            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void updateUserLocation(String userID, String inloc){
        db.getReference("users/"+userID+"/location").setValue(inloc.toString());

    }
}
