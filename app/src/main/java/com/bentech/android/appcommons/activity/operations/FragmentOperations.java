package com.bentech.android.appcommons.activity.operations;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Daniel on 07/11/2015.
 */
public interface FragmentOperations {
    void clearBackStack();

    void popBackStack(FragmentManager fragmentManager);

    void switchFragmentsAddToBackStack(int contentFrameId, android.support.v4.app.Fragment replacingFragment);

    void switchFragments(int contentFrameId, android.support.v4.app.Fragment replacingFragment);

    void switchChildFragmentsAddToBackStack(int contentFrameId, Fragment parentFragment, Fragment replacingFragment);

    void switchChildFragments(int contentFrameId, Fragment parentFragment, Fragment replacingFragment);

    void showFragmentDialog(DialogFragment dialogFragment);

    void dismissFragmentDialog(DialogFragment dialogFragment);
}
