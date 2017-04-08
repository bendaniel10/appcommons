package com.bentech.android.appcommons.utils;

import android.util.Log;

import com.bentech.android.appcommons.AppCommons;

import java.math.RoundingMode;
import java.text.ChoiceFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by Daniel on 9/2/2015.
 */
public class CurrencyUtils {

    private static final String TAG = CurrencyUtils.class.getSimpleName();

    public static String formatToCurrencyWithSymbol(Double amount, String currency) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        nf.setMaximumFractionDigits(AppCommons.getAppCommonsConfiguration().getMaximumCurrencyFractionDigits());
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        if (currency == null || currency.isEmpty()) {
            return nf.format(amount);
        }
        return String.format("%s %s", currency, nf.format(amount));
    }

    public static double parseAmount(String amountString) {
        NumberFormat nf = DecimalFormat.getInstance(Locale.getDefault());

        char groupingSeperator = ((DecimalFormat) nf).getDecimalFormatSymbols().getGroupingSeparator();
        amountString = amountString.replaceAll(String.valueOf(groupingSeperator), "");
        try {
            return nf.parse(amountString).doubleValue();
        } catch (ParseException e) {
            Log.d(TAG, "Exception: " + e.toString());
        }

        Log.d(TAG, "Returning 0, it seems something happened.");
        return 0D;
    }
}
