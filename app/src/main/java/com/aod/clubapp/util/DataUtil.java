package com.aod.clubapp.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by gadfil on 27.12.2014.
 */
public class DataUtil {

    private static final String NETWORK_DATA = "network_data";
    private static final String TOKEN = "token";
    private static final String ID = "id";
    private static final String LOGIN = "login";

    public static long getId(Context context) {
        return context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE).getLong(ID,-1);
    }

    public static String getLogin(Context context) {
        return context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE).getString(LOGIN, null);
    }

    public static String getToken(Context context){
        return context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE).getString(TOKEN, null);
    }



    public static void setToken(Context context, String token){
        SharedPreferences sharedPref = context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOKEN, token);
        editor.commit();
    }

    public static void setId(Context context, long id){
        SharedPreferences sharedPref = context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putLong(ID, id);
        editor.commit();
    }

    public static void setLogin(Context context, String login){
        SharedPreferences sharedPref = context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(LOGIN, login);
        editor.commit();
    }

//    public static void addLike(Context context, long photoId){
//        SharedPreferences sharedPref = context.getSharedPreferences(NETWORK_DATA, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.put(LOGIN, login);
//        editor.commit();
//    }

}
