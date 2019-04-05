package project1.mobile.cs.fsu.edu.project1;

import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.type.LatLng;

import java.io.Console;

public class DBHelper {
    public final static String TAG="DBHELPER";
    private FirebaseFirestore db;
    DBHelper(){
        db=FirebaseFirestore.getInstance();
    }
    public void getUserName(String userID){
        DocumentReference documentReference= db.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot doc=task.getResult();
                if (doc.exists()){
                    String usrName=doc.getString("name");
                    Log.d(TAG, usrName);
                }
                else{
                    Log.d(TAG, "name not found!");
                }
            }
        });
    }
    /*public Location getUserLocation(String userID){
       DocumentReference documentReference= db.collection("users").document(userID);
       documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               DocumentSnapshot doc= task.getResult();
               if(doc.exists()){
                   Location usrlocation=new Location();
                   usrlocation.setLatitude( doc.getGeoPoint("location").getLatitude());

                   Log.d("usrlocation",usrLocation.toString());
               }
               else{}
           }
       })
    }*/

}
