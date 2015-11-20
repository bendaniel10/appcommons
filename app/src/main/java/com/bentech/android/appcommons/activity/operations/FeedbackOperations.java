package com.bentech.android.appcommons.activity.operations;

import android.support.design.widget.Snackbar;
import android.view.View;

import com.bentech.android.appcommons.constants.alert.AlertLevel;

/**
 * Created by Daniel on 07/11/2015.
 */
public interface FeedbackOperations {

    Snackbar showShortSnackBar(View view, int messageId, int actionLabel, View.OnClickListener onClickListener, AlertLevel alertLevel);

    Snackbar showShortSnackBar(View view, int messageId, AlertLevel alertLevel);

    Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel, AlertLevel alertLevel);

    Snackbar showIndefiniteSnackBar(View view, int messageId, AlertLevel alertLevel);

    Snackbar showIndefiniteSnackBar(View view, int messageId, View.OnClickListener onClickListener, AlertLevel alertLevel);

    Snackbar showIndefiniteSnackBar(View view, int messageId, int actionLabel, View.OnClickListener onClickListener, AlertLevel alertLevel);

    Snackbar showLongSnackBar(View view, int messageId, AlertLevel alertLevel);

    void showLongToast(int messageId);

    void showShortToast(int messageId);
}
