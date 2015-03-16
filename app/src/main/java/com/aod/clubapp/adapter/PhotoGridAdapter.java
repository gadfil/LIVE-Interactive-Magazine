package com.aod.clubapp.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import com.aod.clubapp.R;
import com.aod.clubapp.api.model.photo.Photo;
import com.aod.clubapp.ui.view.SquaredImageView;
import com.aod.clubapp.util.MyLog;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.picasso.Callback;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by gadfil on 11.01.2015.
 */
public class PhotoGridAdapter extends BaseAdapter {
    private final Context context;
    private ArrayList<Photo> mPhotos;
    private HashSet<Integer> loadedId;

    public PhotoGridAdapter(Context context,ArrayList<Photo> photos) {
        this.context = context;
        mPhotos = photos;
        loadedId = new HashSet<Integer>();
        MyLog.d("la_click", "!new grid adapter");
        for(int i = 0; i<photos.size();i++){
            if(photos.get(i).getPhotoUserTags().length>0){
                MyLog.e("la_click", "p" +i);
                MyLog.d("la_click", photos.get(i).toString());
            }
        }
    }

    @Override
    public int getCount() {
        return mPhotos.size();
    }

    @Override
    public Photo getItem(int position) {
        return mPhotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_photo, null);
            holder = new ViewHolder();
            holder.image = (SquaredImageView) convertView.findViewById(R.id.photo);
            holder.progress = (ProgressBar) convertView.findViewById(R.id.progressBar);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.progress.setTag(Integer.valueOf(position));

        if (!loadedId.contains(Integer.valueOf(position))&&position!=0) {
            holder.progress.setVisibility(View.VISIBLE);
        }

        final String url = getItem(position).getImageThumbUrl();
        MyLog.d("PhotoGrid", "position " + position + " url " + url);
        MyLog.d("PhotoGrid", "position " + position + " photo " + getItem(position));
        MyLog.d("PhotoGrid", "loadedId " + loadedId.toString());
        OkHttpClient client = new OkHttpClient();
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.setCookieHandler(cookieManager);
        OkHttpDownloader downloader = new OkHttpDownloader(client);
// Create the downloader for Picasso to use
        Picasso picasso = new Picasso.Builder(context).downloader(downloader).build();
//        Picasso.with(context)
        picasso.with(context)
                .load(url)
                .fit()

                .tag(context)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {

                        loadedId.add(Integer.valueOf(position));
                        holder.progress.setVisibility(View.GONE);
//                        Log.d("PhotoGrid", " onSuccess position " + position + " url " + url);
//                        Log.d("PhotoGrid", " onSuccess  " + holder.progress.getTag() + " url " + url);
//                        Log.d("PhotoGrid", " ---------------");
                    }

                    @Override
                    public void onError() {
                        Log.e("PhotoGrid", "position " + position + " url " + url);
                    }
                });


        return convertView;
    }

    static class ViewHolder {
        SquaredImageView image;
        ProgressBar progress;
    }
}
