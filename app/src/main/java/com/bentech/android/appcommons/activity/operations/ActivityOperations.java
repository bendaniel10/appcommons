package com.bentech.android.appcommons.activity.operations;

import android.content.Context;
import android.content.Intent;

import com.bentech.android.appcommons.AppCommonsContext;

/**
 * Created by Daniel on 07/11/2015.
 */
public interface ActivityOperations {

    public void startActivity(Intent intent);

    AppCommonsContext getAppCommonsContext();

    void setAppCommonsContext(AppCommonsContext appCommonsContext);

    void restartApp(Context context, Class starupClass);

    void hideKeyBoard();

    void hideToolBar();

    void showToolBar();

    void setActionBarTitle(int messageId);

    void setActionBarTitle(String message);
}
