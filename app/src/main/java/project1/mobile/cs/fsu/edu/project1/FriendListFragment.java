package project1.mobile.cs.fsu.edu.project1;

import android.content.Context;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FriendListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class FriendListFragment extends Fragment {

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

        // Adapter && Listview Variables
        ListView listView = (ListView)view.findViewById(R.id.list);
        mAdapter = new FriendItemArrayAdapter(getActivity(), R.layout.friendrows);


        //********* Need a function to parse through the table and place into this adapter

        mAdapter.add(new FriendItem(555,"test","test","test","test",false));
        listView.setAdapter(mAdapter);

        //********* Need a function to parse through the table and place into this adapter


        // Listview Listener
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int positionNum = adapterView.getPositionForView(view);
                FriendItem curItem = mAdapter.getItem(positionNum);
                Toast.makeText(getActivity(), curItem.getUsername(), Toast.LENGTH_SHORT).show();

                // Take curItem and will need to bundle and intent to map activity
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
