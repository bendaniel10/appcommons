package com.bentech.android.appcommons.utils;

import android.util.Log;

import junit.framework.TestCase;

/**
 * Created by bendaniel on 29/07/2016.
 */
public class CurrencyUtilsTest extends TestCase {

    private static final String TAG = CurrencyUtilsTest.class.getSimpleName();

    public void testParseAmount() throws Exception {
        assertTrue(CurrencyUtils.parseAmount("1,000,000.00") == 1000000D);
        assertTrue(CurrencyUtils.parseAmount("10,0,0,00,0.00") == 1000000D);
        assertTrue(CurrencyUtils.parseAmount("1,000,00,0.00") == 1000000D);
    }

}