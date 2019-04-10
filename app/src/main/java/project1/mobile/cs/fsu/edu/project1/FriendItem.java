package project1.mobile.cs.fsu.edu.project1;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class FriendItem implements Parcelable {

    /*******************************************************************
     * This is a "Parcelable" class that creates FriendItem
     *
     * - Currently there are 2 constructor
     * --- Everyone is a friend
     * --- Everyone is not a friend (like a normal social network)
     *
     * - This class is used with FriendItemArray to build up the FriendListView
     *
     ********************************************************************/

    ///// Class Variables
    private int id;
    private String username;
    private String phone;
    private String name;
    private String location;
    private Boolean isLocationPublic;

    // ** Everyone is a friend, maybe we will use this later
    private Boolean isPendingFriend;
    private Boolean isFriend;

    /////  Constructor without pending friends
    public FriendItem(int id, String username, String phone, String name, String location, Boolean isLocationPublic) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.name = name;
        this.location = location;
        this.isLocationPublic = isLocationPublic;
    }

    /////  Constructor with pending friends
    public FriendItem(int id, String username, String phone, String name, String location, Boolean isLocationPublic, Boolean isPendingFriend, Boolean isFriend) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.name = name;
        this.location = location;
        this.isLocationPublic = isLocationPublic;
        this.isPendingFriend = isPendingFriend;
        this.isFriend = isFriend;
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
        dest.writeValue(this.isPendingFriend);
        dest.writeValue(this.isFriend);
    }

    protected FriendItem(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.phone = in.readString();
        this.name = in.readString();
        this.location = in.readString();
        this.isLocationPublic = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isPendingFriend = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.isFriend = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FriendItem> CREATOR = new Parcelable.Creator<FriendItem>() {
        @Override
        public FriendItem createFromParcel(Parcel source) {
            return new FriendItem(source);
        }

        @Override
        public FriendItem[] newArray(int size) {
            return new FriendItem[size];
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

    public Boolean getFriend() {
        return isFriend;
    }

    public void setFriend(Boolean friend) {
        isFriend = friend;
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

    public Boolean getPendingFriend() {
        return isPendingFriend;
    }

    public void setPendingFriend(Boolean pendingFriend) {
        isPendingFriend = pendingFriend;
    }
}
