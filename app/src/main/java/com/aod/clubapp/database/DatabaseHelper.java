package com.aod.clubapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aod.clubapp.database.table.Albums;
import com.aod.clubapp.database.table.Photos;

/**
 * Created by gadfil on 13.03.2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "clubs.sqlite3";
    private Context context;

    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not exists " + Albums.TABLE_NAME + " ( " +
                Albums._ID + " INTEGER PRIMARY KEY, " +
                Albums.INTERVIEW + " INTEGER DEFAULT 0, " +
                Albums.LIKES_COUNT + " INTEGER DEFAULT 0, " +
                Photos.IS_BANNER + " INTEGER DEFAULT 0, " +
                Albums.PHOTOS_COUNT + " INTEGER, " +
                Albums.PLACE_ID + " INTEGER, " +
                Albums.ALBUM_DATE + " INTEGER, " +
                Albums.OUT_DATE + " TEXT, " +
                Albums.DATE + " TEXT, " +
                Albums.BANNER_IMAGE_URL + " TEXT, " +
                Albums.BANNER_URL + " TEXT, " +
                Albums.NAME + " TEXT, " +
                Albums.PLACE_NAME + " TEXT, " +
                Albums.MAIN_PHOTO_URL + " TEXT, " +
                Albums.INTERVIEW_DESCRIPTION + " TEXT ); ");

        db.execSQL("CREATE TABLE if not exists " + Photos.TABLE_NAME + " ( " +
                Photos._ID + " INTEGER PRIMARY KEY, " +
                Photos.ALBUM_ID + " INTEGER , " +
                Photos.LIKES_COUNT + " INTEGER DEFAULT 0, " +
                Photos.IS_BANNER + " INTEGER DEFAULT 0, " +
                Photos.LIKED + " INTEGER DEFAULT 0, " +
                Photos.PURCHASED + " INTEGER DEFAULT 0, " +
                Photos.BANNER_IMAGE_URL + " TEXT, " +
                Photos.BANNER_URL + " TEXT, " +
                Photos.IMAGE_THUMB_URL + " TEXT, " +
                Photos.IMAGE_COVER_URL + " TEXT, " +
                Photos.IMAGE_MEDIUM_URL + " TEXT ); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Albums.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Photos.TABLE_NAME);
    }
}
