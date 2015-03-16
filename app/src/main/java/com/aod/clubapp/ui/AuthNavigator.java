package com.aod.clubapp.ui;

import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.aod.clubapp.R;
import com.aod.clubapp.ui.Navigator;
import com.aod.clubapp.ui.activity.ListAlbumActivity;
import com.aod.clubapp.ui.activity.UserAgreementActivity;
import com.aod.clubapp.ui.fragment.BaseFragment;
import com.aod.clubapp.ui.fragment.SignFragment;
import com.aod.clubapp.ui.fragment.SignUpFragment;
import com.aod.clubapp.util.MyLog;

import java.util.Stack;

/**
 * Created by gadfil on 16.02.2015.
 */
public class AuthNavigator implements Navigator, SignFragment.SignCallbacks{
    FragmentActivity activity;
    Stack<String> stack;

    public AuthNavigator(FragmentActivity activity) {
        this.activity = activity;
        BaseFragment fragment = SignFragment.newInstance();
        stack = new Stack<>();


        activity.getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .setCustomAnimations(R.anim.slide_in_right,0 )
                .replace(R.id.auth_container, fragment, fragment.getTagNavigate())

                .commit();
        stack.push( fragment.getTagNavigate());
    }

    @Override
    public void navigateTo() {

    }

    @Override
    public void back() {

    }


    @Override
    public void userAgreement() {
        UserAgreementActivity.start(activity);
        activity.overridePendingTransition(R.anim.activity_slide_left_in, R.anim.activity_slide_left_out);
    }

    @Override
    public void signUp(String email) {
        BaseFragment fragment = SignUpFragment.newInstance(email);
        activity.getSupportFragmentManager().beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .setCustomAnimations(R.anim.slide_in_right,0 )
        .addToBackStack(null)
                .replace(R.id.auth_container, fragment, fragment.getTagNavigate())
                .commit();
        stack.push( fragment.getTagNavigate());
    }

    @Override
    public void sign() {
        ListAlbumActivity.start(activity);
        activity.overridePendingTransition(R.anim.activity_slide_left_in, R.anim.activity_slide_left_out);
    }

    @Override
    public void forgetPassword() {

    }
}
