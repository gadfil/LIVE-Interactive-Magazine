package com.aod.clubapp.api.model.auth;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class SignResponse implements Parcelable {
    private long id;
    private String login;
    private String message;
    @SerializedName("auth_token")
    private String authToken;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    @Override
    public String toString() {
        return "SignResponse{" +
                "  \nid=" + id +
                ", \nlogin='" + login + '\'' +
                ", \nmessage='" + message + '\'' +
                ", \nauthToken='" + authToken + '\'' +
                '}';
    }


    public SignResponse() {
    }

    public SignResponse(Parcel in) {
        this.id = in.readLong();
        this.login = in.readString();
        this.message = in.readString();
        this.authToken = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeLong(id);
        out.writeString(login);
        out.writeString(message);
        out.writeString(authToken);
    }
}
