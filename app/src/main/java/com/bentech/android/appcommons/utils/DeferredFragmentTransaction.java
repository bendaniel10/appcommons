package com.bentech.android.appcommons.utils;

import android.support.v4.app.Fragment;

import com.bentech.android.appcommons.activity.AppCommonsActivity;

/**
 * Created by bendaniel on 10/01/2017.
 */

public abstract class DeferredFragmentTransaction {

    private int contentFrameId;
    private Fragment replacingFragment;
    private Fragment parentFragment;

    public abstract void commit(AppCommonsActivity appCommonsActivity) ;

    public int getContentFrameId() {
        return contentFrameId;
    }

    public void setContentFrameId(int contentFrameId) {
        this.contentFrameId = contentFrameId;
    }

    public Fragment getReplacingFragment() {
        return replacingFragment;
    }

    public void setReplacingFragment(Fragment replacingFragment) {
        this.replacingFragment = replacingFragment;
    }

    public void setParentFragment(Fragment parentFragment) {
        this.parentFragment = parentFragment;
    }

    public Fragment getParentFragment() {
        return parentFragment;
    }
}
