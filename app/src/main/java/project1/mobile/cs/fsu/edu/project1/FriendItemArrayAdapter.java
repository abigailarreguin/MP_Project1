package project1.mobile.cs.fsu.edu.project1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class FriendItemArrayAdapter extends ArrayAdapter<FriendItem> {

        // Class Variables
        private List<FriendItem> mFriendLists;
        private Context mContext;

        // Constructor
        public FriendItemArrayAdapter(@NonNull Context context, int resource)
        {
            super(context,resource);
            mContext = context;
            mFriendLists = new ArrayList<>();
        }

        private static class FriendHolder{
            //***** Declare the textviews here
            TextView textView_Title;
            TextView textView_friendAdd;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {

            final FriendItem item = getItem(position);
            FriendHolder viewHolder;

            if (convertView == null)
            {
                viewHolder = new FriendHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(R.layout.friendrows, parent, false);


                // Insert the textviews ID's here from R.layout.friendsRow
                viewHolder.textView_Title = (TextView) convertView.findViewById(R.id.rowTitle);
                viewHolder.textView_friendAdd = (TextView) convertView.findViewById(R.id.rowFriendAdd);


                // End of comment

                convertView.setTag(viewHolder);
            } else{
                viewHolder = (FriendHolder) convertView.getTag();
            }

            // Set the required text for the TextView declared above
            viewHolder.textView_Title.setText("friend testing 1");
            viewHolder.textView_friendAdd.setText("++++");



            // End of comment


            return convertView;
        }
    }