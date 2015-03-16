package com.aod.clubapp.ui.fragment;

import android.support.v4.app.Fragment;

/**
 * Created by gadfil on 16.02.2015.
 */
abstract public  class BaseFragment  extends Fragment{
    public String getTagNavigate(){
        return getClass().getCanonicalName();
    }
//    public static abstract  int[]animatesId;
}
