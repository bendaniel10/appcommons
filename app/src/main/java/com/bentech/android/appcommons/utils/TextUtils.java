package com.bentech.android.appcommons.utils;

import android.app.Application;
import android.widget.TextView;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.R;

/**
 * Created by Daniel on 20/11/2015.
 */
public class TextUtils {

    public static String getUXedString(String string) {
        Application application = AppCommons.getApplication();
        if (string == null || string.isEmpty()) {
            return application.getString(R.string.label_not_available);
        }

        return string;
    }

    public static String getUXedDate(String date) {
        Application application = AppCommons.getApplication();
        if (date == null || date.isEmpty()) {
            return application.getString(R.string.label_not_available);
        }

        return DateUtils.formatToDate(date);
    }

    public static String getUXedDouble(Double theDouble) {
        Application application = AppCommons.getApplication();
        if (theDouble == null) {
            return application.getString(R.string.label_not_available);
        }

        return String.valueOf(theDouble);
    }

    public static void setUxText(TextView text, String textString) {
        Application application = AppCommons.getApplication();
        if (android.text.TextUtils.isEmpty(textString) || textString.equalsIgnoreCase("null")) {
            text.setText(application.getString(R.string.label_not_available_abbr));
        } else {
            text.setText(textString);
        }
    }

    public static void setUxAmountText(TextView text, String textString, String currency) {
        Application application = AppCommons.getApplication();
        if (android.text.TextUtils.isEmpty(textString) || textString.equalsIgnoreCase("null")) {
            text.setText(CurrencyUtils.formatToCurrencyWithSymbol(0D, currency));
        } else {
            text.setText(CurrencyUtils.formatToCurrencyWithSymbol(Double.valueOf(textString), currency));
        }
    }

    public static String getUXedAmount(Double amount, String currency) {
        Application application = AppCommons.getApplication();
        if (amount == null) {
            return application.getString(R.string.label_not_available);
        }

        return CurrencyUtils.formatToCurrencyWithSymbol(amount, currency);
    }
}
