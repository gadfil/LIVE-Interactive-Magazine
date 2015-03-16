package com.aod.clubapp.util;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.aod.clubapp.api.model.albums.AlbumItem;

import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by gadfil on 13.03.2015.
 */
public class CacheUtil {
    public static final String ALBUM = "album";
    public void cache(Context context, ArrayList< CacheImage> list, String dir, final int startCache){
        new AsyncTask< ArrayList<AlbumItem>,Void,  Void>() {

            @Override
            protected Void doInBackground( ArrayList<AlbumItem>... params) {
                ArrayList<AlbumItem> list = params[0];
               for(int i = startCache; i < list.size(); i ++){
                   for(String url:list.get(i).getCacheImage()){

                   }
               }
                return null;
            }
        };
    }

    public void downloadFromUrl(String DownloadUrl, String fileName, String dirName) {

        try {
            File root = android.os.Environment.getExternalStorageDirectory();

            File dir = new File (root.getAbsolutePath() + "/"+dirName);
            if(dir.exists()==false) {
                dir.mkdirs();
            }

            URL url = new URL(DownloadUrl); //you can write here any link
            File file = new File(dir, fileName);

            long startTime = System.currentTimeMillis();
            MyLog.d("DownloadManager", "download begining");
            MyLog.d("DownloadManager", "download url:" + url);
            MyLog.d("DownloadManager", "downloaded file name:" + fileName);

           /* Open a connection to that URL. */
            URLConnection ucon = url.openConnection();

           /*
            * Define InputStreams to read from the URLConnection.
            */
            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

           /*
            * Read bytes to the Buffer until there is nothing more to read(-1).
            */
            ByteArrayBuffer baf = new ByteArrayBuffer(5500);
            int current = 0;
            while ((current = bis.read()) != -1) {
                baf.append((byte) current);
            }


           /* Convert the Bytes read to a String. */
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baf.toByteArray());
            fos.flush();
            fos.close();
            MyLog.d("DownloadManager", "download ready in" + ((System.currentTimeMillis() - startTime) / 1000) + " sec");

        } catch (IOException e) {
            Log.d("DownloadManager", "Error: " + e);
        }

    }
}
