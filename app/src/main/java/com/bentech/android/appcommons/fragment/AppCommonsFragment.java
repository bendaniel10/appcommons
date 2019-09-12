package com.bentech.android.appcommons.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bentech.android.appcommons.activity.AppCommonsActivity;

/**
 * Created by Daniel on 08/11/2015.
 */
public class AppCommonsFragment extends Fragment {

    protected AppCommonsActivity appCommonsActivity;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.appCommonsActivity = ((AppCommonsActivity) getActivity());
    }
}
