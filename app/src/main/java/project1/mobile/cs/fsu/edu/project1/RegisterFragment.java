package project1.mobile.cs.fsu.edu.project1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;


public class RegisterFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private User regUser;
    DBHelper dbHelper=new DBHelper();
    private FirebaseDatabase db=FirebaseDatabase.getInstance();
    public RegisterFragment() {
    }


    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_register, null);

        Button register = (Button) v.findViewById(R.id.userRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                regUser = ((StartActivity)getActivity()).getCurrentContentValues();
                if (regUser != null)  {
                    db.getReference("userCount").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            int userID=Integer.valueOf(dataSnapshot.getValue().toString());//get userid
                            regUser.setId(userID);
                            dbHelper.putUser(userID,regUser);//actual put to db
                            db.getReference("userCount").setValue(userID+1);//increment users
                            Intent myIntent = new Intent(getActivity(), HomeActivity.class);
                            myIntent.putExtra("login_user",regUser);
                            getActivity().startActivity(myIntent);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(getContext(), "Error connecting to database, try again later", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
    }
}
