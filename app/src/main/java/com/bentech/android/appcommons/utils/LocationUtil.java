package com.bentech.android.appcommons.utils;

import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.provider.Settings;

import java.util.List;

/**
 * Created by Daniel on 8/11/2015.
 */
public final class LocationUtil {
    public static boolean hasGPSDevice(Context context) {
        final LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (mgr == null) {
            return false;
        }
        final List<String> providers = mgr.getAllProviders();
        if (providers == null) {
            return false;
        }
        return providers.contains(LocationManager.GPS_PROVIDER);
    }

    public static boolean isGPSEnabled(Context context) {
        return hasGPSDevice(context) && ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE))
                .isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    public static void showEnableGPSSetting(Context context) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        context.startActivity(intent);
    }

}
