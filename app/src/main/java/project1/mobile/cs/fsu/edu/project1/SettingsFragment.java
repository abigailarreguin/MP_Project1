package project1.mobile.cs.fsu.edu.project1;

import android.content.Context;
import android.content.SharedPreferences;
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

        // Declare variables
        final Spinner milesSpin = view.findViewById(R.id.settingsSpinner);
        final TextView userName = view.findViewById(R.id.settingsUser);
        final TextView phoneNum = view.findViewById(R.id.settingsPhone);
        final Switch modeSwitch = view.findViewById(R.id.settingsMode);
        final Button saveBut = view.findViewById(R.id.settingsSaveBut);

        // Get Settings
        SharedPreferences settings = getActivity().getSharedPreferences("Settings", 0);
        milesSpin.setSelection(settings.getInt("MILES",0));
        modeSwitch.setChecked(settings.getBoolean("PRIVATE",false));

        // Save Button || Performs sharedPerferences for milesSpin and modeSwitch
        saveBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int miles = milesSpin.getSelectedItemPosition();
                boolean isPrivate = modeSwitch.isChecked();

                SharedPreferences settings = getActivity().getSharedPreferences("Settings", 0);
                SharedPreferences.Editor editor = settings.edit();

                editor.putInt("MILES", miles);
                editor.putBoolean("PRIVATE",isPrivate);
                editor.commit();

                Toast.makeText(getActivity(), "Settings Applied In Next Restart", Toast.LENGTH_SHORT).show();
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
    }

}
