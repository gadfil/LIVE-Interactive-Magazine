package com.aod.clubapp.api.model.photo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gadfil on 01.12.2014.
 */
public class PhotoUserTags  implements Parcelable {
    private long id;
    private double x;
    @SerializedName("user_id")
    private String userId;
    @SerializedName("user_login")
    private String userLogin;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }


    public PhotoUserTags(Parcel in) {
        this.id = in.readLong();
        this.x = in.readDouble();
        this.userId = in.readString();
        this.userLogin = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeDouble(x);
        dest.writeString(userId);
        dest.writeString(userLogin);
    }


    transient public static final Creator<PhotoUserTags> CREATOR = new Creator<PhotoUserTags>() {
        public PhotoUserTags createFromParcel(Parcel in) {
            return new PhotoUserTags(in);
        }

        public PhotoUserTags[] newArray(int size) {
            return new PhotoUserTags[size];
        }
    };

    @Override
    public String toString() {
        return "PhotoUserTags{" +
                "id=" + id +
                ", x=" + x +
                ", userId='" + userId + '\'' +
                ", userLogin='" + userLogin + '\'' +
                '}';
    }
}
