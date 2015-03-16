package com.aod.clubapp.api.model.auth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gadfil on 13.11.2014.
 */
public class SignUpResponse {
    private long id;
    private String login;
    private String message;
    @SerializedName("auth_token")
    private String authToken;
    private String name;
    private String facebook_token;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacebook_token() {
        return facebook_token;
    }

    public void setFacebook_token(String facebook_token) {
        this.facebook_token = facebook_token;
    }
}
