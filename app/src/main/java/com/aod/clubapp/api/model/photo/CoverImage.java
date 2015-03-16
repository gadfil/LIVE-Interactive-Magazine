package com.aod.clubapp.api.model.photo;

import com.google.gson.annotations.SerializedName;

public class CoverImage {
    @SerializedName("image_thumb_url")
    private String imageThumbUrl;
    @SerializedName("image_cover_url")
    private String imageCoverUrl;
    @SerializedName("image_medium_url")
    private String imageMediumUrl;

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
}
