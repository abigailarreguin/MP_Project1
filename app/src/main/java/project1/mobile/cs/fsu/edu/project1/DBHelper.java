package project1.mobile.cs.fsu.edu.project1;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.security.AccessController.getContext;

public class DBHelper {
    public final static String TAG = "DBHELPER";
    private FirebaseDatabase db;

    DBHelper() {
        db = FirebaseDatabase.getInstance();
    }


    public void getUserName(String userID) {
        db.getReference(userID).child("fName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String uname = dataSnapshot.getValue().toString();
                Log.d(TAG, "user firstname: " + uname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(TAG, "Loading username failed");
            }
        });
    }

    public void getUser(String userID) {

        db.getReference(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    User temp = userSnapshot.getValue(User.class);
                    Log.d(TAG, "user got=" + temp);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void setEmergency(Context context, String id, String location) {

        //// Variables
        final String status = "true";
        final Context emergencyContext = context;
        final String emergencyID = id;
        final String emergencyLocation = location;

        //// Set the database to status of current user
        setEmergencyStatus(status + "," + emergencyID + "," + emergencyLocation);

        // Perform Event Listen On Update
        db.getInstance().getReference().child("emergencyStatus").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get String from the key
                String value = dataSnapshot.getValue(String.class);

                // Split the string into pieces
                String[] valueArr = value.split(",");

                // Perform checking function within onDataChange (because it will become a null outside of the funct)
                if (valueArr[0].matches("true")) {
                    NotificationHelper noti;
                    noti = new NotificationHelper(emergencyContext);

                    // Change these strings for the msg
                    String title = "Emergency";
                    String content = valueArr[1] + " IS HAVING AN EMERGENCY AT " + valueArr[2];

                    // Notification Class
                    Notification.Builder nb = noti.getNotification1(title, content);
                    noti.notify(911, nb);

                    // Set emergency off
                    unSetEmergency();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void setEmergencyStatus(String status) {
        db.getInstance().getReference("emergencyStatus").setValue(status);
    }

    public void unSetEmergency() {
        db.getInstance().getReference("emergencyStatus").setValue("false,location");
    }

    public void putUser(int userID, User inUser) {
        db.getReference("users/" + userID).setValue(inUser);
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

    public void setFriendList(String id) {
        db.getInstance().getReference().child(id).push().setValue("friend list");
    }

    public void addFriendList(Context context, String ID, String newid) {
        final Context emergencyContext = context;
        final String newID = newid;
        final String curID = ID;

        // Perform Event Listen On Update
        db.getInstance().getReference().child("users/" + curID + "/friendList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get String from the key and append new friend
                String value = dataSnapshot.getValue(String.class);

                // Split the string into pieces
                String[] valueArr = value.split(",");

                // Concate new ID from previous value
                String newValue = value.concat("," + newID);

                // Loop that checks if current ID exists or not, then adds it to the database list
                for (int i = 0; i < valueArr.length; ++i) {
                    if (valueArr[i].matches(newID)) {
                        break;
                    }

                    if (!(valueArr[valueArr.length - 1].matches(newID))) {
                        db.getInstance().getReference(curID).setValue(newValue);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void removeFromFriendList(Context context, String ID, String newid) {
        final Context emergencyContext = context;
        final String newID = newid;
        final String curID = ID;

        // Perform Event Listen On Update
        db.getInstance().getReference().child("users/" + curID + "/friendList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                // Get String from the key and append new friend
                String value = dataSnapshot.getValue(String.class);

                // Split the string into pieces
                String[] valueArr = value.split(",");

                // This will combine all the strings together
                ArrayList<String> newValue = new ArrayList<String>();

                // Loop that checks if current ID exists or not, then adds it to the database list
                for (int i = 0; i < valueArr.length; ++i) {
                    if (valueArr[i].matches(newID)) {
                        continue;
                    } else {
                        newValue.add(valueArr[i]);
                    }
                }

                String completedStr = String.join(",", newValue);

                //GetUserLocation("1",emergencyContext);
                // Push the new list
                db.getInstance().getReference().child("users/" + curID + "/friendList").setValue(completedStr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    public void GetUserLocation(String userID, Context context) {
        // Context is for the notification, where to place it
        final Context curContext = context;
        final String ID = userID;

        // table/users/userID
        db.getInstance().getReference().child("users").child(userID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String city = dataSnapshot.child("location").getValue(String.class);

                // Notification
                NotificationHelper noti;
                noti = new NotificationHelper(curContext);

                // Change these strings for the msg
                String title = "This person is nearby you!";
                String content = city;

                String[] latlngstr = content.split(",");
                double lat = Double.parseDouble(latlngstr[0]);
                double lng = Double.parseDouble(latlngstr[1]);

                List<Address> addresses;
                Geocoder geocoder = new Geocoder(curContext, Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(lat, lng, 1);
                    content = addresses.get(0).getAddressLine(0);

                } catch (IOException e) {
                    e.printStackTrace();
                }



                // Sleep
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                // Notification Class
                Notification.Builder nb = noti.getNotification1(title, content);
                noti.notify(912, nb);

                //String temp = userSnapshot.getValue(User.location);
                //Log.d(TAG, "user got=" + temp);

            }

            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // Fixed function, is not able to add
    public void updateUserLocation(String userID, String latlng) {
        db.getInstance().getReference("users/" + userID + "/location").setValue(latlng);
    }

}