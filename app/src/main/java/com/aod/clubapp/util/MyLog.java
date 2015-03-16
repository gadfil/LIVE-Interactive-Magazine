package com.aod.clubapp.util;

import android.util.Log;

/**
 * Created by gadfil on 18.02.2015.
 */
public class MyLog {
    private static final boolean DEBUG_FLAG = true;
    public static void e(String tag, String out){
        if(DEBUG_FLAG){
            Log.e(tag, out);
        }
    }

    public static void d(String tag, String out){
        if(DEBUG_FLAG){
            Log.d(tag, out);
        }
    }
}
