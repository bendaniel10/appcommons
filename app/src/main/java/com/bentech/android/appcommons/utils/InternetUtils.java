package com.bentech.android.appcommons.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.activity.AppCommonsActivity;

/**
 * Created by Daniel on 8/12/2015.
 */
public class InternetUtils {

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

        public boolean execute() {
            if (isNetworkAvailable(context)) {
                internetOperation();
                return true;
            } else {
                ((AppCommonsActivity) context).showLongToast(R.string.label_connect_to_the_internet);
                return false;
            }
        }

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
