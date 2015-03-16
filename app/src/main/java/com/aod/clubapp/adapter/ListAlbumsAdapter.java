package com.aod.clubapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.aod.clubapp.R;
import com.aod.clubapp.api.model.albums.AlbumItem;
import com.aod.clubapp.ui.activity.PhotoActivity;
import com.aod.clubapp.ui.view.SquaredImageView;
import com.aod.clubapp.util.MyLog;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashSet;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by gadfil on 25.02.2015.
 */
public class ListAlbumsAdapter extends BaseAdapter  implements StickyListHeadersAdapter {
    private ArrayList<AlbumItem> mList;
    private Context context;
    private HashSet<Integer> mLoadedId;

    public ListAlbumsAdapter(ArrayList<AlbumItem> mList, Context mContext) {
        this.mList = mList;
        this.context = mContext;
        mLoadedId = new HashSet<Integer>();
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public AlbumItem getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_albums, null);
            holder = new ViewHolder();
            holder.image = (SquaredImageView) convertView.findViewById(R.id.image);
            holder.progress = (ProgressBar) convertView.findViewById(R.id.progressBar);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        if (!mLoadedId.contains(Integer.valueOf(position))) {
            holder.progress.setVisibility(View.VISIBLE);
        }


        MyLog.d("album", "p " + position);
        MyLog.d("album", "p " + getItem(position));
        String url = getItem(position).getMainPhotoUrl();
        Picasso.with(context)
                .load(url)
                .fit()
                .tag(context)
                .into(holder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        mLoadedId.add(Integer.valueOf(position));
                        holder.progress.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {

                    }
                });


        return convertView;
    }

    public void addList(ArrayList<AlbumItem> mList){
        this.mList.addAll(mList);
        notifyDataSetChanged();
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
//        HeaderViewHolder holder;
        final HeaderViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.header, null);
            holder = new HeaderViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.text1);
            holder.text1 = (TextView) convertView.findViewById(R.id.text2);
            holder.date = (TextView) convertView.findViewById(R.id.date);
            holder.likeCount = (TextView) convertView.findViewById(R.id.like_count);
            holder.like = (ImageView) convertView.findViewById(R.id.img_like);

            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
//        convertView.getLayoutParams().width = (int) context.getResources().getDimension(R.dimen.header_height);

        holder.text.setText(getItem(position).getName());
        holder.text1.setText(getItem(position).getPlaceName());
        holder.date.setText(getItem(position).getOutDate());
        String like=" ";
        if(getItem(position).getLikesCount()!=0){
            like=String.valueOf(getItem(position).getLikesCount());
            holder.like.setVisibility(View.VISIBLE);
        }else {
            holder.like.setVisibility(View.GONE);
        }
        holder.likeCount.setText(like);

        return convertView;
    }

    @Override
    public long getHeaderId(int i) {
        return i;
    }

    static class ViewHolder {
        SquaredImageView image;
        ProgressBar progress;
    }

    static class HeaderViewHolder{
        TextView text;
        TextView text1;
        TextView likeCount;
        TextView date;
        ImageView like;
    }

}
