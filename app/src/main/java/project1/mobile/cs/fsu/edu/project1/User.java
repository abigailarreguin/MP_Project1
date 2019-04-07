package project1.mobile.cs.fsu.edu.project1;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

    public class User implements Parcelable {
//literally copied Brandons "Friends" class so i can implement functions
        /*******************************************************************
         * This is a "Parcelable" class that creates User
         *
         * - Currently there are 2 constructor
         * --- Everyone is a User
         * --- Everyone is not a User (like a normal social network)
         *
         * - This class is used with UserArray to build up the UserListView
         *
         ********************************************************************/

        ///// Class Variables
        private int id;
        private String username;
        private String phone;
        private String name;
        private String location;
        private Boolean isLocationPublic;

        // ** Everyone is a User, maybe we will use this later
        private Boolean isPendingUser;
        private Boolean isUser;

        /////  Constructor without pending Users
        public User(int id, String username, String phone, String name, String location, Boolean isLocationPublic) {
            this.id = id;
            this.username = username;
            this.phone = phone;
            this.name = name;
            this.location = location;
            this.isLocationPublic = isLocationPublic;
        }

        /////  Constructor with pending Users
        public User(int id, String username, String phone, String name, String location, Boolean isLocationPublic, Boolean isPendingUser, Boolean isUser) {
            this.id = id;
            this.username = username;
            this.phone = phone;
            this.name = name;
            this.location = location;
            this.isLocationPublic = isLocationPublic;
            this.isPendingUser = isPendingUser;
            this.isUser = isUser;
        }

        ///// Parceable Methods
        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.username);
            dest.writeString(this.phone);
            dest.writeString(this.name);
            dest.writeString(this.location);
            dest.writeValue(this.isLocationPublic);
            dest.writeValue(this.isPendingUser);
            dest.writeValue(this.isUser);
        }

        protected User(Parcel in) {
            this.id = in.readInt();
            this.username = in.readString();
            this.phone = in.readString();
            this.name = in.readString();
            this.location = in.readString();
            this.isLocationPublic = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.isPendingUser = (Boolean) in.readValue(Boolean.class.getClassLoader());
            this.isUser = (Boolean) in.readValue(Boolean.class.getClassLoader());
        }

        public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
            @Override
            public User createFromParcel(Parcel source) {
                return new User(source);
            }

            @Override
            public User[] newArray(int size) {
                return new User[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Boolean getUser() {
            return isUser;
        }

        public void setUser(Boolean User) {
            isUser = User;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public Boolean getLocationPublic() {
            return isLocationPublic;
        }

        public void setLocationPublic(Boolean locationPublic) {
            isLocationPublic = locationPublic;
        }

        public Boolean getPendingUser() {
            return isPendingUser;
        }

        public void setPendingUser(Boolean pendingUser) {
            isPendingUser = pendingUser;
        }
    }
