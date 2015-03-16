package com.aod.clubapp.api.model.albums;

import com.google.gson.JsonObject;

import java.util.Arrays;

public class AlbumsResponse {

    private JsonObject albums[];

    public JsonObject[] getAlbums() {
        return albums;
    }

    public void setAlbums(JsonObject[] albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "AlbumsResponse{" +
                "albums=" + Arrays.toString(albums) +
                '}';
    }
}
