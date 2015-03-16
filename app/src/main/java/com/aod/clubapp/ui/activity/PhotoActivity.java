package com.aod.clubapp.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.aod.clubapp.R;
import com.aod.clubapp.adapter.PhotoAlbumAdapter;
import com.aod.clubapp.api.model.ApiCallback;
import com.aod.clubapp.api.model.albums.AlbumWithPhoto;
import com.aod.clubapp.api.model.photo.Photo;
import com.aod.clubapp.api.v2.Api;
import com.aod.clubapp.ui.view.RevealBackgroundView;
import com.aod.clubapp.util.DataUtil;
import com.aod.clubapp.util.MyLog;

import java.util.ArrayList;

import butterknife.InjectView;
import retrofit.RestAdapter;
import retrofit.client.Request;
import retrofit.client.Response;

public class PhotoActivity extends BaseActivity   implements RevealBackgroundView.OnStateChangeListener{

    private static final String EXTRA_IMAGE = "extra_image";
    private static final String EXTRA_ID = "extra_id";

    private static final String LOG_TAG = PhotoActivity.class.getSimpleName();
    private static final String ARG_REVEAL_START_LOCATION = "arg_reveal_start_location";

    @InjectView(R.id.vRevealBackground)
    RevealBackgroundView vRevealBackground;
    @InjectView(R.id.rvAlbumPhotos)
    RecyclerView rvAlbumPhotos;
    private PhotoAlbumAdapter photoAlbumAdapter;


    private static AlbumWithPhoto albumWithPhoto = null;
    private long id;
    private String url;
    private int state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        ImageView image = (ImageView) findViewById(R.id.image);
//        ViewCompat.setTransitionName(image, EXTRA_IMAGE);

//        Picasso.with(this).load(getIntent().getStringExtra(EXTRA_IMAGE)).into(image);
//        gridView = (GridView) findViewById(R.id.grid);


        id = getIntent().getLongExtra(EXTRA_ID, 0);
        url = getIntent().getStringExtra(EXTRA_IMAGE);
photoAlbumAdapter = new PhotoAlbumAdapter(this, "1213", new ArrayList<Photo>());
        getAlbumsWithPhoto(id);
        final StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvAlbumPhotos.setLayoutManager(layoutManager);

        rvAlbumPhotos.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    photoAlbumAdapter.setLockedAnimations(true);

            }
        });


//        getAlbumsWithPhoto(id);

//        setupUserProfileGrid();
        setupRevealBackground(savedInstanceState);

    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_photo;
    }


    private void getAlbumsWithPhoto(long id) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Api.URL)
                .build();
        Api api = restAdapter.create(Api.class);
        Request request = null;

        api.getAlbumsById(id, DataUtil.getToken(getApplicationContext()), new ApiCallback<AlbumWithPhoto>(this) {
            @Override
            public void success(AlbumWithPhoto albumWithPhoto, Response response) {
                MyLog.d(LOG_TAG, albumWithPhoto.toString());
                MyLog.d(LOG_TAG, "" + response.getUrl());
                PhotoActivity.albumWithPhoto = albumWithPhoto;
                ArrayList<Photo> photos = new ArrayList<Photo>(PhotoActivity.albumWithPhoto.getPhotos().size());
                for (Photo photo : PhotoActivity.albumWithPhoto.getPhotos()) {
                    if (photo.getImageMediumUrl() != null) {
                        photos.add(photo);
                    }
                }
                PhotoActivity.albumWithPhoto.setPhotos(photos);
                photoAlbumAdapter.update(photos, url);
//                photoAlbumAdapter = new PhotoAlbumAdapter(PhotoActivity.this,url,  photos );


//                if (RevealBackgroundView.STATE_FINISHED == state) {
//                    rvAlbumPhotos.setVisibility(View.VISIBLE);
//                    rvAlbumPhotos.setAdapter(photoAlbumAdapter );
//                }
//
//                 update adapter
//                gridView.setAdapter(new PhotoGridAdapter(PhotoActivity.this, PhotoActivity.albumWithPhoto.getPhotos()));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void launch(Activity activity, View transitionView, String url, long albumId, int[] startingLocation) {
//        ActivityOptionsCompat options =
//                ActivityOptionsCompat.makeSceneTransitionAnimation(
//                        activity, transitionView, EXTRA_IMAGE);

        Intent intent = new Intent(activity, PhotoActivity.class);
        intent.putExtra(EXTRA_IMAGE, url);
        intent.putExtra(EXTRA_ID, albumId);
        intent.putExtra(ARG_REVEAL_START_LOCATION, startingLocation);
//        ActivityCompat.startActivity(activity, intent, options.toBundle());
        activity.startActivity( intent);
    }




    private void setupRevealBackground(Bundle savedInstanceState) {
        vRevealBackground.setOnStateChangeListener(this);
        if (savedInstanceState == null) {
            final int[] startingLocation = getIntent().getIntArrayExtra(ARG_REVEAL_START_LOCATION);
            vRevealBackground.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {
                    vRevealBackground.getViewTreeObserver().removeOnPreDrawListener(this);
                    vRevealBackground.startFromLocation(startingLocation);
                    return true;
                }
            });
        } else {
            vRevealBackground.setToFinishedFrame();
            photoAlbumAdapter.setLockedAnimations(true);
        }
    }

    @Override
    public void onStateChange(int state) {
        this.state = state;
        if (RevealBackgroundView.STATE_FINISHED == state) {
            rvAlbumPhotos.setVisibility(View.VISIBLE);
//            userPhotosAdapter = new UserProfileAdapter(this);
//            photoAlbumAdapter = new PhotoAlbumAdapter(this,url, id );
//            if(photoAlbumAdapter!=null){
                rvAlbumPhotos.setAdapter(photoAlbumAdapter );
//            }
        } else {
            rvAlbumPhotos.setVisibility(View.INVISIBLE);
        }
    }
}
