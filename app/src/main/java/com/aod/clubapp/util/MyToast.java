package com.aod.clubapp.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aod.clubapp.R;
import com.aod.clubapp.api.model.errors.ResponseError;

/**
 * Created by gadfil on 24.02.2015.
 */
public class MyToast {
    public static void show(Context context, String msg){
        View view = LayoutInflater.from(context).inflate(R.layout.toast_error_view, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText( msg);
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    public static void show(Context context, int msg){
        View view = LayoutInflater.from(context).inflate(R.layout.toast_error_view, null);
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText( context.getString(msg));
        Toast toast = new Toast(context);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
