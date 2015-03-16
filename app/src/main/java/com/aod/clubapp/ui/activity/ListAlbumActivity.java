package com.aod.clubapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.aod.clubapp.R;
import com.aod.clubapp.adapter.ListAlbumsAdapter;
import com.aod.clubapp.api.model.ApiCallback;
import com.aod.clubapp.api.model.albums.AlbumItem;
import com.aod.clubapp.api.model.albums.AlbumsResponse;
import com.aod.clubapp.api.model.errors.ResponseError;
import com.aod.clubapp.api.v2.Api;
import com.aod.clubapp.util.DataUtil;
import com.aod.clubapp.util.DateTimeUtils;
import com.aod.clubapp.util.MyLog;
import com.google.gson.GsonBuilder;
import com.manuelpeinado.fadingactionbar.view.OnScrollChangedCallback;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

public class ListAlbumActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private StickyListHeadersListView listView;
    private ListAlbumsAdapter adapter;
    private ProgressBar progressBar;
    private int page = 0;
    private boolean isRequest = false;

    public static void start(Context context) {
        Intent intent = new Intent(context, ListAlbumActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_list_album);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.inflateMenu(R.menu.menu_list_album);
//        setSupportActionBar(toolbar);

        setActionBarIcon(R.drawable.ic_launcher);

        listView = (StickyListHeadersListView) findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        progressBar =(ProgressBar)findViewById(R.id.progressBar);
        showAlbums(page);


    }

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_list_album;
    }


    private void showAlbums(final int page){

        if(page == 0){
            setAdapter();
            this.page++;
        }else {
            isRequest=true;
            MyLog.d("show", "page " + page + "  count " + adapter.getCount() + " isRequest " + isRequest);
            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(Api.URL)
                    .build();
            Api api = restAdapter.create(Api.class);


            api.getAlbumsByPage(DataUtil.getToken(this), page, new ApiCallback<AlbumsResponse>(this) {
                @Override
                public void success(AlbumsResponse albumsResponse, Response response) {
                    ArrayList<AlbumItem> listS = getAlbumItems(albumsResponse);
                    adapter.addList(listS);
                    ListAlbumActivity.this.page++;
                    isRequest=false;
                }

            });
        }
    }


    private void setAdapter(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Api.URL)
                .build();
        Api api = restAdapter.create(Api.class);
        MyLog.d("api", "+++++++++");
        api.getAlbums(DataUtil.getToken(this), new ApiCallback<AlbumsResponse>(this) {
            @Override
            public void success(AlbumsResponse albumsResponse, Response response) {
                ArrayList<AlbumItem> listS = new ArrayList<AlbumItem>();
                int first = -1;
                int position = 0;
                MyLog.d("api", response.getUrl());
                MyLog.d("api", albumsResponse.toString());

                for (int i = 0; i < albumsResponse.getAlbums().length; i++) {

                    if (albumsResponse.getAlbums()[i].has("banner")) {
                    } else {
                        MyLog.d("api", "" + albumsResponse.getAlbums()[i]);
                        AlbumItem album = new GsonBuilder().create().fromJson(albumsResponse.getAlbums()[i], AlbumItem.class);
                        MyLog.d("api", ""+album);
                        try {
                            Date date1 = DateTimeUtils.ISO8601toDate(album.getAlbumDate());
                            album.setOutDate(DateTimeUtils.dateToDay(date1));
                            album.setAlbumsDate(date1.getTime());

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        listS.add(album);
                        if(album.getAlbumsDate()<=System.currentTimeMillis() && first == -1){
                            first = position;
                        }

                        position++;
                    }
                }

                adapter = new ListAlbumsAdapter(listS, ListAlbumActivity.this);
                listView.setAdapter(adapter);

                listView.setSelection(first);
                progressBar.setVisibility(View.GONE);
//                listView.setOnScrollListener(ListAlpumsFragment.this);
            }});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_album, menu);
        return true;
    }

    private ArrayList<AlbumItem> getAlbumItems(AlbumsResponse albumsResponse) {
        ArrayList<AlbumItem> listS = new ArrayList<AlbumItem>();
        for (int i = 0; i < albumsResponse.getAlbums().length; i++) {
            if (albumsResponse.getAlbums()[i].has("banner")) {
            } else {
                AlbumItem album = new GsonBuilder().create().fromJson(albumsResponse.getAlbums()[i], AlbumItem.class);
                try {
                    Date date1 = DateTimeUtils.ISO8601toDate(album.getAlbumDate());
                    album.setOutDate(DateTimeUtils.dateToDay(date1));
                    album.setAlbumsDate(date1.getTime());

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                listS.add(album);

            }
        }
        return listS;
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

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.activity_slide_right_in, R.anim.activity_slide_right_out);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        MyLog.d("onitemclick", adapter.getItem(position).getMainPhotoUrl());
        int[] startingLocation = new int[2];
        view.getLocationOnScreen(startingLocation);
        startingLocation[0] += view.getWidth() / 7;

        startingLocation[0] =0;
        startingLocation[1] =0;
        PhotoActivity.launch(this, view.findViewById(R.id.image), adapter.getItem(position).getMainPhotoUrl(), adapter.getItem(position).getId(),startingLocation);
    }
}
