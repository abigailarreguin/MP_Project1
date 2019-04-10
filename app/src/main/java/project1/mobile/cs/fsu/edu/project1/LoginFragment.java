package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;


public class LoginFragment extends android.support.v4.app.Fragment {
    public final static String TAG="loginfragment";
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    private FusedLocationProviderClient fusedLocationClient;
    public LoginFragment() {
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_login, null);
        Button register = (Button) v.findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.myFragment, new RegisterFragment());
                fragmentTransaction.commit();
            }
        });


        Button login = (Button) v.findViewById(R.id.loginButton);
        final EditText loginUserEbox= v.findViewById(R.id.username);
        final EditText loginPasswordEbox=v.findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                final String loginUsername=loginUserEbox.getText().toString();
                final String loginPassword=loginPasswordEbox.getText().toString();
                db.getReference().child("users").orderByChild("username").equalTo(loginUsername).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot result : dataSnapshot.getChildren()) {

                                Log.d(TAG, "result_snapshot: " + result.toString());
                                User LoginUser=result.getValue(User.class);
                                String remotepass =LoginUser.getPassword();
                                if (loginPassword.equals(remotepass)) {
									Intent myIntent = new Intent(getActivity(), HomeActivity.class);
									myIntent.putExtra("login_user", LoginUser);
                                 
								 getActivity().startActivity(myIntent);
                                } else {
                                    Toast.makeText(getContext(), "Password Incorrect!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(getContext(), "Username or password incorrect!", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(getContext(), "Unable to connect to database, try again later", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });

        return v;
    }
}
