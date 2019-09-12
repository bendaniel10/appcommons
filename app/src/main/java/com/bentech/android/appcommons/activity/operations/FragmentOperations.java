package com.bentech.android.appcommons.activity.operations;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

/**
 * Created by Daniel on 07/11/2015.
 */
public interface FragmentOperations {
    void clearBackStack();

    void popBackStack(FragmentManager fragmentManager);

    void switchFragmentsAddToBackStack(int contentFrameId, Fragment replacingFragment);

    void switchFragments(int contentFrameId, Fragment replacingFragment);

    void switchChildFragmentsAddToBackStack(int contentFrameId, Fragment parentFragment, Fragment replacingFragment);

    void switchChildFragments(int contentFrameId, Fragment parentFragment, Fragment replacingFragment);

    void showFragmentDialog(DialogFragment dialogFragment);

    void dismissFragmentDialog(DialogFragment dialogFragment);
}
