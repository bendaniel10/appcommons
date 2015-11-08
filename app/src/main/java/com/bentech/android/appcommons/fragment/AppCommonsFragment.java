package com.bentech.android.appcommons.fragment;

import android.support.v4.app.Fragment;

import com.bentech.android.appcommons.activity.AppCommonsActivity;

/**
 * Created by Daniel on 08/11/2015.
 */
public class AppCommonsFragment extends Fragment {

    private AppCommonsActivity appCommonsActivity;

    public AppCommonsFragment() {
        this.appCommonsActivity = ((AppCommonsActivity) getActivity());
    }
}
