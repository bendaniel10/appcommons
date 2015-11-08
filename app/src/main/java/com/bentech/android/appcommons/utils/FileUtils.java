package com.bentech.android.appcommons.utils;

import android.content.Context;
import android.util.Log;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.config.AppCommonsConfiguration;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Daniel on 10/14/2015.
 */
public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    //http://stackoverflow.com/a/8475135
    public static String getStringAssets(String fileName, Context context) {
        String theString = "";

        try {
            InputStream is = context.getApplicationContext().getAssets().open(fileName);

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            theString = new String(buffer);
        } catch (IOException e) {
            Log.d(TAG, "IOException" + e.getMessage());
        }

        return theString;
    }

    //http://stackoverflow.com/a/8475135
    public static String getStringRawResource(String resourceIdentifier, Context context) {
        String theString = "";

        try {
            int resourceId = context.getResources().getIdentifier(getLocaleResourceIdentifier(resourceIdentifier, context), "raw", context.getPackageName());
            if (resourceId == 0) {
                resourceId = context.getResources().getIdentifier(resourceIdentifier, "raw", context.getPackageName());
            }
            InputStream is = context.getResources().openRawResource(resourceId);

            // We guarantee that the available method returns the total
            // size of the asset...  of course, this does mean that a single
            // asset can't be more than 2 gigs.
            int size = is.available();

            // Read the entire asset into a local byte buffer.
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            // Convert the buffer into a string.
            theString = new String(buffer);
        } catch (IOException e) {
            Log.d(TAG, "IOException" + e.getMessage());
        }

        return theString;
    }

    private static String getLocaleResourceIdentifier(String resourceIdentifier, Context context) {
        AppCommonsConfiguration appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();
        return String.format("%s_%s", resourceIdentifier, appCommonsConfiguration.getPreferredStringLocale());
    }
}
