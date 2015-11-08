package com.bentech.android.appcommons;

import android.app.Application;
import android.util.Log;

import com.bentech.android.appcommons.config.AppCommonsConfiguration;
import com.bentech.android.appcommons.config.AppCommonsDefaultConfiguration;

/**
 * Created by Daniel on 07/11/2015.
 */
public class AppCommons {
    private static final String TAG = AppCommons.class.getSimpleName();
    private static Application application;
    private static AppCommonsConfiguration appCommonsConfiguration;

    public static void install(Application application, AppCommonsConfiguration appCommonsConfiguration) {
        AppCommons.application = application;
        AppCommons.appCommonsConfiguration = appCommonsConfiguration;
        Log.d(TAG, "AppCommmons Installed");
    }

    public static void install(Application application) {
        AppCommons.application = application;
        AppCommons.appCommonsConfiguration = new AppCommonsDefaultConfiguration();
        Log.d(TAG, "AppCommmons Installed");

    }

    public static Application getApplication() {
        return application;
    }

    public static AppCommonsConfiguration getAppCommonsConfiguration() {
        return appCommonsConfiguration;
    }
}
