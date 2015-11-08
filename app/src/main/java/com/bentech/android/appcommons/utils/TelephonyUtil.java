package com.bentech.android.appcommons.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.TelephonyManager;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * Created by Daniel on 8/10/2015.
 */
public final class TelephonyUtil {

    //http://stackoverflow.com/questions/2785485/is-there-a-unique-android-device-id/2853253#2853253
    public static String getDeviceId(Context activity) {
        final TelephonyManager tm = (TelephonyManager) activity.getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(activity.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return removeNullBytes(deviceUuid.toString().getBytes(Charset.forName("UTF-8")));
    }

    public static boolean hasCamera(PackageManager packageManager) {
        return packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    private static String removeNullBytes(byte[] data) {
        String dataOut = "";
        for (int i = 0; i < data.length; i++) {
            if (data[i] != 0x00) {
                dataOut += (char) data[i];
            }
        }
        return dataOut;
    }

}
