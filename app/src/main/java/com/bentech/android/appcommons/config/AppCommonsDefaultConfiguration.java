package com.bentech.android.appcommons.config;

import android.graphics.Color;

import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.permission.AndroidPermissionItem;

import java.util.ArrayList;

/**
 * Created by Daniel on 07/11/2015.
 */
public class AppCommonsDefaultConfiguration extends AppCommonsConfiguration {

    public AppCommonsDefaultConfiguration() {
        setFragmentEnterAnimation(android.R.anim.fade_in);
        setFragmentExitAnimation(R.anim.no_animation);
        setFragmentPopEnterAnimation(android.R.anim.fade_in);
        setFragmentPopExitAnimation(R.anim.no_animation);
        setSnackbarTextSize(14);
        setSnackbarTextColor(Color.WHITE);
        setSnackbarActionTextColor(Color.YELLOW);
        setPreferredStringLocale("en");
        setPicUtilImageSampleSize(256);
        setEditTextRequiredErrorMessage(R.string.label_required);
        setSpinnerLabelErrorColor(Color.RED);
        setSpinnerLabelValidColor(Color.BLACK);
        setEditTextInvalidEmailErrorMessage(R.string.label_invalid_email);
        setEditTextInvalidUsernameErrorMessage(R.string.label_invalid_username);
        setEditTextInvalidNumberRangeErrorMessage(R.string.label_invalid_number_range);
        setEditTextTextLengthErrorMessage(R.string.label_invalid_text_length);
        setEditTextStartDateGreaterThanTodayErrorMessage(R.string.label_start_date_greater_than_today_error);
        setEditTextDateEarlierThanTodayErrorMessage(R.string.date_must_not_be_earlier_than_today);
        setEditTextPhoneNumberValidatorErrorMessage(R.string.invalid_phone_number);
        setEditTextInvalidPositiveNumberErrorMessage(R.string.label_invalid_positive_number);
        setEnableRequiredPermissionsMessage(R.string.label_enable_permissions_hint);
        setDangerousPermissions(new ArrayList<AndroidPermissionItem>());
    }

}
