package com.aod.clubapp.api.model.albums;

import com.aod.clubapp.util.CacheImage;
import com.aod.clubapp.api.model.photo.CoverImage;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gadfil on 27.12.2014.
 */
public class AlbumItem  extends CacheImage{
    private long id;
    private String name;
    @SerializedName("place_name")
    private String placeName;
    private boolean interview;
    @SerializedName("likes_count")
    private int likesCount;
    @SerializedName("place_id")
    private int placeId;
    private String date;
    @SerializedName("album_date")
    private String albumDate;
    @SerializedName("main_photo_url")
    private String mainPhotoUrl;
    @SerializedName("photos_count")
    private int photosCount;
    @SerializedName("cover_image")
    private CoverImage coverImage;
    private transient String outDate;
    private transient long albumsDate;


    @Override
    public String toString() {
        return "AlbumItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", placeName='" + placeName + '\'' +
                ", interview=" + interview +
                ", likesCount=" + likesCount +
                ", placeId=" + placeId +
                ", date='" + date + '\'' +
                ", albumDate='" + albumDate + '\'' +
                ", mainPhotoUrl='" + mainPhotoUrl + '\'' +
                ", photosCount=" + photosCount +
                ", coverImage=" + coverImage +
                ", outDate='" + outDate + '\'' +
                ", albumsDate=" + albumsDate +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public boolean isInterview() {
        return interview;
    }

    public void setInterview(boolean interview) {
        this.interview = interview;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getPlaceId() {
        return placeId;
    }

    public void setPlaceId(int placeId) {
        this.placeId = placeId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAlbumDate() {
        return albumDate;
    }

    public void setAlbumDate(String albumDate) {
        this.albumDate = albumDate;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    public int getPhotosCount() {
        return photosCount;
    }

    public void setPhotosCount(int photosCount) {
        this.photosCount = photosCount;
    }

    public CoverImage getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(CoverImage coverImage) {
        this.coverImage = coverImage;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public long getAlbumsDate() {
        return albumsDate;
    }

    public void setAlbumsDate(long albumsDate) {
        this.albumsDate = albumsDate;
    }

    @Override
    public String[] getCacheImage() {
        return new String[]{getMainPhotoUrl()};
    }
}
