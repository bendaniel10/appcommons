package com.bentech.android.appcommons.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.activity.AppCommonsActivity;

import androidx.annotation.RequiresPermission;

import static android.Manifest.permission.ACCESS_NETWORK_STATE;

/**
 * Created by Daniel on 8/12/2015.
 */
public class InternetUtils {

    @RequiresPermission(ACCESS_NETWORK_STATE)
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static abstract class RunWithInternet {
        private Context context;
        public RunWithInternet(Context context) {
            this.context = context;
        }

        @SuppressLint("MissingPermission")
        public boolean execute() {
            if (isNetworkAvailable(context)) {
                internetOperation();
                return true;
            } else {
                ((AppCommonsActivity) context).showLongToast(R.string.label_connect_to_the_internet);
                return false;
            }
        }

        @SuppressLint("MissingPermission")
        public boolean executeFailSilent() {
            if (isNetworkAvailable(context)) {
                internetOperation();
                return true;
            } else {
                return false;
            }
        }


        protected abstract void internetOperation();

    }
}
