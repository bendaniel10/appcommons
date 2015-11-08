package com.bentech.android.appcommons.activity.operations;

import android.support.design.widget.Snackbar;
import android.view.View;

/**
 * Created by Daniel on 07/11/2015.
 */
public interface FeedbackOperations {

    Snackbar showShortSnackBar(View view, int messageId, int actionLabel, View.OnClickListener onClickListener);

    Snackbar showShortSnackBar(View view, int messageId);

    Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel);

    Snackbar showIndefiniteSnackBar(View view, int messageId);

    Snackbar showIndefiniteSnackBar(View view, int messageId, View.OnClickListener onClickListener);

    Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel, View.OnClickListener onClickListener);

    Snackbar showLongSnackBar(View view, int messageId);

    void showLongToast(int messageId);
}
