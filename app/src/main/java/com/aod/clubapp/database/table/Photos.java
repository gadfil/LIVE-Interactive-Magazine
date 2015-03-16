package com.aod.clubapp.database.table;

import android.provider.BaseColumns;

/**
 * Created by gadfil on 13.03.2015.
 */
public class Photos implements BaseColumns {
    public static final String TABLE_NAME = "photo";
    public static final String ALBUM_ID = "album_id ";
    public static final String LIKES_COUNT = "likes_count";
    public static final String LIKED = "liked";
    public static final String IS_BANNER = "is_banner";
    public static final String BANNER_IMAGE_URL = "banner_image_url";
    public static final String BANNER_URL = "banner_url";
    public static final String PURCHASED = "purchased";
    public static final String IMAGE_THUMB_URL = "image_thumb_url";
    public static final String IMAGE_COVER_URL = "image_cover_url";
    public static final String IMAGE_MEDIUM_URL = "image_medium_url";

}
