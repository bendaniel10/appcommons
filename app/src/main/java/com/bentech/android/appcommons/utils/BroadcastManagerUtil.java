package com.bentech.android.appcommons.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by Daniel on 9/4/2015.
 */
public class BroadcastManagerUtil {

    public static void sendLocalBroadcast(Context context, Intent intent) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).sendBroadcast(intent);
    }

    public static void registerReceiver(Context context, IntentFilter intentFilter, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);

    }

    public static void unregisterReceiver(Context context, BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(context.getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }
}
