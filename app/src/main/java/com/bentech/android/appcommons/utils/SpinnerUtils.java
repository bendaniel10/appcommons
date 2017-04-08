package com.bentech.android.appcommons.utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.config.AppCommonsConfiguration;

/**
 * Created by Daniel on 8/12/2015.
 */
public final class SpinnerUtils {

    public static boolean isEmpty(Spinner spinner) {

        boolean isEmpty = spinner == null || spinner.getSelectedItemPosition() == 0 ||
                spinner.getSelectedItemPosition() == AdapterView.INVALID_POSITION || spinner.getVisibility() != View.VISIBLE;

        if (isEmpty) {

            if (spinner != null && spinner.getVisibility() != View.VISIBLE) {
                return false;
            }
            setError(spinner);
        } else {

            clearError(spinner);

        }

        return isEmpty;
    }

    public static void clearError(Spinner spinner) {

        AppCommonsConfiguration appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();
        if (spinner != null && spinner.getChildAt(0) != null && spinner.getChildAt(0) instanceof TextView) {
            ((TextView) spinner.getChildAt(0)).setTextColor(appCommonsConfiguration.getSpinnerLabelValidColor());
        }
    }

    public static void setError(Spinner spinner) {

        AppCommonsConfiguration appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();

        if (spinner != null && spinner.getChildAt(0) != null && spinner.getChildAt(0) instanceof TextView) {
            ((TextView) spinner.getChildAt(0)).setTextColor(appCommonsConfiguration.getSpinnerLabelErrorColor());
        }

    }

    public static boolean isEmpty(Spinner... spinners) {
        boolean isEmpty = false;
        for (Spinner spinner : spinners) {
            if (isEmpty(spinner)) {
                isEmpty = true;
            }
        }

        return isEmpty;
    }

    public static String getSelectedString(Spinner spinner) {
        return String.valueOf(getSelectedObject(spinner));
    }

    public static Object getSelectedObject(Spinner spinner) {
        return spinner.getSelectedItem();
    }

    public static void reset(Spinner spinner) {
        if (spinner != null) {
            spinner.setSelection(0);
        }
    }

    public static void resetToPosition(Spinner spinner, int position) {
        if (spinner != null) {
            spinner.setSelection(position);
        }
    }
}
