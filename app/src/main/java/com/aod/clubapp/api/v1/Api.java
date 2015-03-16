package com.aod.clubapp.api.v1;

import com.aod.clubapp.api.model.auth.SignRequest;
import com.aod.clubapp.api.model.auth.SignResponse;
import com.aod.clubapp.api.model.auth.SignUpRequest;
import com.aod.clubapp.api.model.auth.SignUpResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by gadfil on 18.02.2015.
 */
public interface Api {
    public static final String URL = "http://fixapp-clubs.herokuapp.com/api/v1";
    static final String AUTH_SIGNIN = "/auth/signin";
    static final String AUTH_SIGNUP = "/auth/signup";
    static final String AUTH_FORGET_PASSWORD = "/auth/forget_password";

    static final String QUERY_AUTH_TOKEN = "auth_token";

    @POST(AUTH_SIGNIN)
    void sign(@Body SignRequest request, Callback<SignResponse> callback);
    @POST(AUTH_SIGNUP)
    void signup(@Body SignUpRequest request, Callback<SignUpResponse> callback);

}
