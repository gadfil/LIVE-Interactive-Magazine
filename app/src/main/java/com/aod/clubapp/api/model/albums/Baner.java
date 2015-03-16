package com.aod.clubapp.api.model.albums;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gadfil on 12.01.2015.
 */
public class Baner {
    @SerializedName("image_url")
    private String imageUrl;
    private String action;
    private String url;
    private int position;
    @SerializedName("place_id")
    private int placeId;
    @SerializedName("album_id")
    private int albumId;


    @Override
    public String toString() {
        return "Baner{" +
                "imageUrl='" + imageUrl + '\'' +
                ", action='" + action + '\'' +
                ", url='" + url + '\'' +
                ", position=" + position +
                ", placeId=" + placeId +
                ", albumId=" + albumId +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }
}
