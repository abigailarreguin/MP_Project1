package project1.mobile.cs.fsu.edu.project1;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;
import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class SettingsFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final User login_user = getArguments().getParcelable("login_user");
        // Declare variables
        //final Spinner milesSpin = view.findViewById(R.id.settingsSpinner);
        final TextView userName = view.findViewById(R.id.settingsUser);
        final TextView phoneNum = view.findViewById(R.id.settingsPhone);
        final Switch modeSwitch = view.findViewById(R.id.settingsMode);
        final Button saveBut = view.findViewById(R.id.settingsSaveBut);
        final Button emergencyBut = view.findViewById(R.id.settingsEmergency);

        // Get Settings
        SharedPreferences settings = getActivity().getSharedPreferences("Settings", 0);
        modeSwitch.setChecked(settings.getBoolean("PRIVATE",false));

        emergencyBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper dbHelper = new DBHelper();
                String[] latlngstr = login_user.getLocation().split(",");
                double lat = Double.parseDouble(latlngstr[0]);
                double lng = Double.parseDouble(latlngstr[1]);

                List<Address> addresses;
                String address;
                Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());

                try {
                    addresses = geocoder.getFromLocation(lat, lng, 1);
                    address = addresses.get(0).getAddressLine(0);
                    dbHelper.setEmergency(getContext(), login_user.getName(), address);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        // Save Button || Performs sharedPerferences for milesSpin and modeSwitch
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int miles = milesSpin.getSelectedItemPosition();
                boolean isPrivate = modeSwitch.isChecked();

                SharedPreferences settings = getActivity().getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = settings.edit();

                //editor.putInt("MILES", miles);
                editor.putBoolean("PRIVATE",isPrivate);
                editor.commit();

            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void intentSettingsFragment();
        void notifySingle();
        User getUserDB(User status);
    }

}