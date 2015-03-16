package com.aod.clubapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.aod.clubapp.R;
import com.aod.clubapp.ui.AuthNavigator;
import com.aod.clubapp.ui.fragment.SignFragment;
import com.aod.clubapp.util.DataUtil;


public class AuthActivity extends ActionBarActivity implements SignFragment.SignCallbacks {
    AuthNavigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(DataUtil.getToken(this)!=null){
            ListAlbumActivity.start(this);
            finish();
        }
        setContentView(R.layout.activity_auth);
          navigator = new AuthNavigator(this);
//        SignFragment fragment = SignFragment.newInstance();
//        Log.d("log", fragment.getTagNavigate());
//        getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
//                .replace(R.id.auth_container, fragment, fragment.getTagNavigate())
//                .commit();
    }


    @Override
    public void overridePendingTransition(int enterAnim, int exitAnim) {
        super.overridePendingTransition(R.anim.activity_slide_left_in, R.anim.activity_slide_left_out);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public static void start(Context context){
        context.startActivity(new Intent(context, AuthActivity.class));
    }

    @Override
    public void forgetPassword() {
        navigator.forgetPassword();
    }

    @Override
    public void userAgreement() {
        navigator.userAgreement();
    }

    @Override
    public void signUp(String email) {
        navigator.signUp(email);
    }

    @Override
    public void sign() {
        navigator.sign();
    }


//    @Override
//    public void onBackPressed() {
//        navigator.back();
//    }
}
