package com.aod.clubapp.api.model;

import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.aod.clubapp.R;
import com.aod.clubapp.api.model.errors.ResponseError;
import com.aod.clubapp.ui.activity.AuthActivity;
import com.aod.clubapp.util.DataUtil;
import com.aod.clubapp.util.MyLog;
import com.aod.clubapp.util.MyToast;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by gadfil on 18.02.2015.
 */
abstract public class ApiCallback<T> implements Callback<T> {
    private Context context;

    protected ApiCallback(Context context) {
        this.context = context;
    }

    @Override
    public void failure(RetrofitError retrofitError){
        if(retrofitError.isNetworkError()){
            Toast.makeText(context, R.string.network_error, Toast.LENGTH_LONG).show();

        }else {
            MyLog.e("api", "" + retrofitError);
//        Log.e("api", retrofitError.getBodyAs(ResponseError.class).toString());
//            Toast.makeText(mContext, retrofitError.getBodyAs(ResponseError.class).toString(), Toast.LENGTH_LONG).show();
            switch (retrofitError.getResponse().getStatus()) {
                case 400:
                    MyToast.show(context, retrofitError.getBodyAs(ResponseError.class).toString());
                    break;
                case 401:
                    DataUtil.setToken(context, null);
                    AuthActivity.start(context);
                    break;
            }
        }
    }
}
