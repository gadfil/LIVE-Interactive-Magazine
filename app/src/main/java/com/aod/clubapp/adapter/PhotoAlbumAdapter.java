package com.aod.clubapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.andexert.library.RippleView;
import com.aod.clubapp.R;
import com.aod.clubapp.api.model.ApiCallback;
import com.aod.clubapp.api.model.albums.AlbumWithPhoto;
import com.aod.clubapp.api.model.photo.Photo;
import com.aod.clubapp.api.v2.Api;
import com.aod.clubapp.ui.view.SquaredImageView;
import com.aod.clubapp.util.DataUtil;
import com.aod.clubapp.util.MyLog;
import com.aod.clubapp.util.UtilDimens;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import retrofit.RestAdapter;
import retrofit.client.Request;
import retrofit.client.Response;

/**
 * Created by gadfil on 11.03.2015.
 */
public class PhotoAlbumAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public static final int TYPE_PROFILE_HEADER = 0;
    public static final int TYPE_PHOTO = 1;

    private static final int USER_OPTIONS_ANIMATION_DELAY = 300;
    private static final int MAX_PHOTO_ANIMATION_DELAY = 600;

    private static final int MIN_ITEMS_COUNT = 1;
    private static final Interpolator INTERPOLATOR = new DecelerateInterpolator();
    private static final String LOG_TAG = PhotoAlbumAdapter.class.getSimpleName();

    private boolean lockedAnimations = false;
    private long profileHeaderAnimationStartTime = 0;
    private int lastAnimatedItem = 0;

    private final Context context;
    private String mainPhoto;
    private ArrayList<Photo> photos;
    private final int cellSize;
    private final int headerSize;
    public PhotoAlbumAdapter(Context context,String mainPhoto,  ArrayList<Photo> photos) {
        this.context = context;
        this.mainPhoto = mainPhoto;
        this.photos = photos;
        this.cellSize = UtilDimens.getScreenWidth(context) / 2;
        this.headerSize = UtilDimens.getScreenWidth(context);
//        getAlbumsWithPhoto(id);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (TYPE_PROFILE_HEADER == viewType) {
            final View view = LayoutInflater.from(context).inflate(R.layout.view_photo_almum_header, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
            return new ProfileHeaderViewHolder(view);

        } else if (TYPE_PHOTO == viewType) {
            final View view = LayoutInflater.from(context).inflate(R.layout.item_photo_l, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();
            layoutParams.setFullSpan(false);
            view.setLayoutParams(layoutParams);
            return new PhotoViewHolder(view);
        }

        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (TYPE_PROFILE_HEADER == viewType) {
            bindProfileHeader((ProfileHeaderViewHolder) holder);
        } else if (TYPE_PHOTO == viewType) {
            bindPhoto((PhotoViewHolder) holder, position);
        }
    }

    private void bindProfileHeader(final ProfileHeaderViewHolder holder) {
        Picasso.with(context)
                .load(mainPhoto)
//                .placeholder(R.drawable.img_circle_placeholder)
                .resize(headerSize, headerSize)
                .centerCrop()
//                .transform(new CircleTransformation())
                .into(holder.ivUserProfilePhoto);
        holder.vUserProfileRoot.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                holder.vUserProfileRoot.getViewTreeObserver().removeOnPreDrawListener(this);
                animateUserProfileHeader(holder);
                return false;
            }
        });
    }

    private void bindPhoto(final PhotoViewHolder holder, int position) {
        Picasso.with(context)
                .load(photos.get(position - MIN_ITEMS_COUNT).getImageThumbUrl())
                .resize(cellSize, cellSize)
                .centerCrop()
                .into(holder.ivPhoto, new Callback() {
                    @Override
                    public void onSuccess() {
                        animatePhoto(holder);
                    }

                    @Override
                    public void onError() {

                    }
                });
        if (lastAnimatedItem < position) lastAnimatedItem = position;
    }

    private void animateUserProfileHeader(ProfileHeaderViewHolder viewHolder) {
        if (!lockedAnimations) {
            profileHeaderAnimationStartTime = System.currentTimeMillis();

            viewHolder.vUserProfileRoot.setTranslationY(-viewHolder.vUserProfileRoot.getHeight());
            viewHolder.ivUserProfilePhoto.setTranslationY(-viewHolder.ivUserProfilePhoto.getHeight());

            viewHolder.vUserProfileRoot.animate().translationY(0).setDuration(300).setInterpolator(INTERPOLATOR);
            viewHolder.ivUserProfilePhoto.animate().translationY(0).setDuration(300).setStartDelay(100).setInterpolator(INTERPOLATOR);
        }
    }

    private void animatePhoto(PhotoViewHolder viewHolder) {
        if (!lockedAnimations) {
            if (lastAnimatedItem == viewHolder.getPosition()) {
                setLockedAnimations(true);
            }

            long animationDelay = profileHeaderAnimationStartTime + MAX_PHOTO_ANIMATION_DELAY - System.currentTimeMillis();
            if (profileHeaderAnimationStartTime == 0) {
                animationDelay = viewHolder.getPosition() * 30 + MAX_PHOTO_ANIMATION_DELAY;
            } else if (animationDelay < 0) {
                animationDelay = viewHolder.getPosition() * 30;
            } else {
                animationDelay += viewHolder.getPosition() * 30;
            }

            viewHolder.flRoot.setScaleY(0);
            viewHolder.flRoot.setScaleX(0);
            viewHolder.flRoot.animate()
                    .scaleY(1)
                    .scaleX(1)
                    .setDuration(200)
                    .setInterpolator(INTERPOLATOR)
                    .setStartDelay(animationDelay)
                    .start();
        }
    }


    @Override
    public int getItemCount() {
        if(photos!=null){
            return  MIN_ITEMS_COUNT + photos.size();
        }else{
            return MIN_ITEMS_COUNT;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_PROFILE_HEADER;
        } else {
            return TYPE_PHOTO;
        }
    }

    public void update(ArrayList<Photo> photos, String url){
        this.photos = photos;
        this.mainPhoto = url;
        notifyDataSetChanged();
    }

    static class ProfileHeaderViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.header_photo)
        SquaredImageView ivUserProfilePhoto;
        @InjectView(R.id.rippleView)
        RippleView vUserProfileRoot;
        public ProfileHeaderViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    static class PhotoViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.rippleViewPhotoL)
        RippleView flRoot;
        @InjectView(R.id.photoL)
        SquaredImageView ivPhoto;

        public PhotoViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }

    private void getAlbumsWithPhoto(long id) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Api.URL)
                .build();
        Api api = restAdapter.create(Api.class);
        Request request = null;

        api.getAlbumsById(id, DataUtil.getToken(context), new ApiCallback<AlbumWithPhoto>(context) {
            @Override
            public void success(AlbumWithPhoto albumWithPhoto, Response response) {
                MyLog.d(LOG_TAG, albumWithPhoto.toString());
                MyLog.d(LOG_TAG, "" + response.getUrl());
                photos = new ArrayList<Photo>(albumWithPhoto.getPhotos().size());
                for (Photo photo : albumWithPhoto.getPhotos()) {
                    if (photo.getImageMediumUrl() != null) {
                        photos.add(photo);
                    }

                }

//                notifyDataSetChanged();

//                PhotoActivity.albumWithPhoto.setPhotos(photos);
//
//                 update adapter
//                gridView.setAdapter(new PhotoGridAdapter(PhotoActivity.this, PhotoActivity.albumWithPhoto.getPhotos()));

            }
        });
    }

    public void setLockedAnimations(boolean lockedAnimations) {
        this.lockedAnimations = lockedAnimations;
    }
}
