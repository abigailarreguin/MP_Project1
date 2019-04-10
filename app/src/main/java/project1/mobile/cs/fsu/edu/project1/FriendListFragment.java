package project1.mobile.cs.fsu.edu.project1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FriendListFragment extends Fragment {
    ListView listView;
    private OnFragmentInteractionListener mListener;
    private FriendItemArrayAdapter mAdapter;


    public FriendListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friend_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle = getArguments();
        ArrayList<User> users = bundle.getParcelableArrayList("users");
        final User login_user = bundle.getParcelable("login_user");
        // Adapter && Listview Variables
        listView = view.findViewById(R.id.list);
        //
        getUsers();


        //********* Need a function to parse through the table and place into this adapter

        //mAdapter.add(new FriendItem(555,"test","test","test","test",false));


        //********* Need a function to parse through the table and place into this adapter


        // Listview Listener
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int positionNum = adapterView.getPositionForView(view);
                User curItem = mAdapter.getItem(positionNum);
                if (curItem != null) {
                    //Toast.makeText(getActivity(), curItem.getUsername(), Toast.LENGTH_SHORT).show();
                    Intent mapIntent=new Intent(getActivity(),MapsActivity.class);
                    mapIntent.putExtra("mapUser",curItem);
                    mapIntent.putExtra("login_user", login_user);
                    getActivity().startActivity(mapIntent);

                }

                // Take curItem and will need to bundle and intent to map activity
            }
        });
    }

    public void getUsers(){
        FirebaseDatabase.getInstance().getReference().child("users").addListenerForSingleValueEvent(new ValueEventListener() {
            ArrayList <User>  users = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){

                    for(DataSnapshot res : dataSnapshot.getChildren()){

                        users.add(res.getValue(User.class));
                    }
                }
                mAdapter=new FriendItemArrayAdapter(getActivity(),users);
                listView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
        void intentFriendListFragment();
    }
}
