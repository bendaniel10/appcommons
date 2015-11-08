package com.bentech.android.appcommons.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

/**
 * Created by Daniel on 9/2/2015.
 */
public class CurrencyUtils {

    public static String formatToCurrencyWithSymbol(Double amount, String currency) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) nf).getDecimalFormatSymbols();
        decimalFormatSymbols.setCurrencySymbol("");
        ((DecimalFormat) nf).setDecimalFormatSymbols(decimalFormatSymbols);
        if(currency == null || currency.isEmpty()) {
            return nf.format(amount);
        }
        return String.format("%s %s", currency, nf.format(amount));
    }
}
