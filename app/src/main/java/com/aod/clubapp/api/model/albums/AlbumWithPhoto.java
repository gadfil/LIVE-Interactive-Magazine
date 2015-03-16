package com.aod.clubapp.api.model.albums;

import com.aod.clubapp.api.model.photo.Photo;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;


public class AlbumWithPhoto {
    private long id;
    private String name;
    @SerializedName("place_name")
    private String placeName;
    private String date;
    private boolean interview;
    @SerializedName("likes_count")
    private long likesCount;
    @SerializedName("place_id")
    private String placeId;
    @SerializedName("interview_description")
    private String interviewDescription;
    @SerializedName("main_photo_url")
    private String mainPhotoUrl;
    @SerializedName("photos_count")
    private long photosCount;
    private ArrayList<Photo> photos;


    @Override
    public String toString() {
        return "AlbumWithPhoto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", placeName='" + placeName + '\'' +
                ", date='" + date + '\'' +
                ", interview=" + interview +
                ", likesCount=" + likesCount +
                ", placeId='" + placeId + '\'' +
                ", interviewDescription='" + interviewDescription + '\'' +
                ", mainPhotoUrl='" + mainPhotoUrl + '\'' +
                ", photosCount=" + photosCount +
                ", photos=" + Arrays.toString(photos.toArray()) +
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isInterview() {
        return interview;
    }

    public void setInterview(boolean interview) {
        this.interview = interview;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getInterviewDescription() {
        return interviewDescription;
    }

    public void setInterviewDescription(String interviewDescription) {
        this.interviewDescription = interviewDescription;
    }

    public String getMainPhotoUrl() {
        return mainPhotoUrl;
    }

    public void setMainPhotoUrl(String mainPhotoUrl) {
        this.mainPhotoUrl = mainPhotoUrl;
    }

    public long getPhotosCount() {
        return photosCount;
    }

    public void setPhotosCount(long photosCount) {
        this.photosCount = photosCount;
    }

    public ArrayList<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(ArrayList<Photo> photos) {
        this.photos = photos;
    }
}
