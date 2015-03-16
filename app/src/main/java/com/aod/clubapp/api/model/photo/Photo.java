package com.aod.clubapp.api.model.photo;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

import java.util.Arrays;

public class Photo  implements Parcelable {
    private long id;
    @SerializedName("likes_count")
    private long likesCount;
    boolean liked;
    boolean purchased;
    @SerializedName("image_thumb_url")
    private String imageThumbUrl;
    @SerializedName("image_cover_url")
    private String imageCoverUrl;
    @SerializedName("image_medium_url")
    private String imageMediumUrl;
    @SerializedName("photo_user_tags")
    private PhotoUserTags[]photoUserTags;


    public Photo(Parcel parcel) {
        this.id = parcel.readLong();
        this.likesCount = parcel.readLong();
        if(parcel.readLong()==0){
            this.liked = false;
        }else {
            this.liked = true;
        }
        if(parcel.readLong()==0){
            this.purchased = false;
        }else {
            this.purchased = true;
        }
        this.imageThumbUrl = parcel.readString();
        this.imageCoverUrl = parcel.readString();
        this.imageMediumUrl = parcel.readString();
      parcel.readTypedArray(photoUserTags, PhotoUserTags.CREATOR);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", likesCount=" + likesCount +
                ", liked=" + liked +
                ", purchased=" + purchased +
                ", imageThumbUrl='" + imageThumbUrl + '\'' +
                ", imageCoverUrl='" + imageCoverUrl + '\'' +
                ", imageMediumUrl='" + imageMediumUrl + '\'' +
                ", photoUserTags=" + Arrays.toString(photoUserTags) +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(long likesCount) {
        this.likesCount = likesCount;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public void setPurchased(boolean purchased) {
        this.purchased = purchased;
    }

    public String getImageThumbUrl() {
        return imageThumbUrl;
    }

    public void setImageThumbUrl(String imageThumbUrl) {
        this.imageThumbUrl = imageThumbUrl;
    }

    public String getImageCoverUrl() {
        return imageCoverUrl;
    }

    public void setImageCoverUrl(String imageCoverUrl) {
        this.imageCoverUrl = imageCoverUrl;
    }

    public String getImageMediumUrl() {
        return imageMediumUrl;
    }

    public void setImageMediumUrl(String imageMediumUrl) {
        this.imageMediumUrl = imageMediumUrl;
    }

    public PhotoUserTags[] getPhotoUserTags() {
        return photoUserTags;
    }

    public void setPhotoUserTags(PhotoUserTags[] photoUserTags) {
        this.photoUserTags = photoUserTags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(likesCount);
        if(liked){
            dest.writeLong(1);
        }else {
            dest.writeLong(0);
        }
        if(purchased){
            dest.writeLong(1);
        }else {
            dest.writeLong(0);
        }
        dest.writeString(imageThumbUrl);
        dest.writeString(imageCoverUrl);
        dest.writeString(imageMediumUrl);
        dest.writeTypedArray(photoUserTags, 0);

    }
}
