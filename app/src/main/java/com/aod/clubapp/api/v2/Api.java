package com.aod.clubapp.api.v2;

import com.aod.clubapp.api.model.ApiCallback;
import com.aod.clubapp.api.model.albums.AlbumWithPhoto;
import com.aod.clubapp.api.model.albums.AlbumsResponse;
import com.aod.clubapp.api.model.auth.SignRequest;
import com.aod.clubapp.api.model.auth.SignResponse;
import com.aod.clubapp.api.model.auth.SignUpRequest;
import com.aod.clubapp.api.model.auth.SignUpResponse;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by gadfil on 18.02.2015.
 */
public interface Api {
    public static final String URL = "http://fixapp-clubs.herokuapp.com/api/v2";
    static final String AUTH_SIGNIN = "/auth/signin";
    static final String AUTH_SIGNUP = "/auth/signup";
    static final String AUTH_FORGET_PASSWORD = "/auth/forget_password";
    static final String ALBUMS = "/albums";
    static final String ALBUMS_BY_ID = ALBUMS+"/{id}/";
    static final String QUERY_PAGE = "page";

    static final String QUERY_AUTH_TOKEN = "auth_token";

    @POST(AUTH_SIGNIN)
    void sign(@Body SignRequest request, Callback<SignResponse> callback);
    @POST(AUTH_SIGNUP)
    void signup(@Body SignUpRequest request, Callback<SignUpResponse> callback);

    @GET(ALBUMS)
    void getAlbums(@Query(QUERY_AUTH_TOKEN) String token, Callback<AlbumsResponse> callback);

    @GET(ALBUMS)
    void getAlbumsByPage(@Query(QUERY_AUTH_TOKEN) String token,@Query(QUERY_PAGE) int page, Callback<AlbumsResponse> callback);

    @GET(ALBUMS_BY_ID)
    void getAlbumsById(@Path("id") long id, @Query(QUERY_AUTH_TOKEN) String token,Callback<AlbumWithPhoto> callback);


}
