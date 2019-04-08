package project1.mobile.cs.fsu.edu.project1;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class LoginFragment extends android.support.v4.app.Fragment {

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
        login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {

                // TO DO: Make sure username and password are in database
                // If not, show error
                Intent myIntent = new Intent(getActivity(), HomeActivity.class);
                getActivity().startActivity(myIntent);
            }
        });

        return v;
    }
}
