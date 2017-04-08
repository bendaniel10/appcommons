package com.bentech.android.appcommons.config;

import com.bentech.android.appcommons.permission.AndroidPermissionItem;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Daniel on 07/11/2015.
 */
public abstract class AppCommonsConfiguration implements Serializable {

    protected Integer fragmentEnterAnimation;
    protected Integer fragmentExitAnimation;
    protected Integer fragmentPopEnterAnimation;
    protected Integer fragmentPopExitAnimation;
    private Integer snackbarActionTextColor;
    private Integer snackbarTextColor;
    private Integer snackbarTextSize;
    private String preferredStringLocale;
    private Integer picUtilImageSampleSize;
    private Integer spinnerLabelValidColor;
    private Integer spinnerLabelErrorColor;
    private Integer editTextRequiredErrorMessage;
    private int editTextInvalidUsernameErrorMessage;
    private int editTextInvalidEmailErrorMessage;
    private int editTextInvalidNumberRangeErrorMessage;
    private int editTextInvalidPositiveNumberErrorMessage;
    private int editTextTextLengthErrorMessage;
    private int enableRequiredPermissionsMessage;
    private List<AndroidPermissionItem> dangerousPermissions;
    private int editTextDateEarlierThanTodayErrorMessage;
    private int editTextStartDateGreaterThanTodayErrorMessage;
    private int editTextPhoneNumberValidatorErrorMessage;
    private int maximumCurrencyFractionDigits = 2;

    public AppCommonsConfiguration() {
    }

    public int getFragmentEnterAnimation() {
        return fragmentEnterAnimation;
    }

    public void setFragmentEnterAnimation(int fragmentEnterAnimation) {
        this.fragmentEnterAnimation = fragmentEnterAnimation;
    }


    public void setEditTextRequiredErrorMessage(int editTextRequiredErrorMessage) {
        this.editTextRequiredErrorMessage = editTextRequiredErrorMessage;
    }

    public int getEditTextRequiredErrorMessage() {
        return editTextRequiredErrorMessage;
    }

    public void setPicUtilImageSampleSize(int picUtilImageSampleSize) {
        this.picUtilImageSampleSize = picUtilImageSampleSize;
    }

    public int getPicUtilImageSampleSize() {
        return picUtilImageSampleSize;
    }

    public int getFragmentExitAnimation() {
        return fragmentExitAnimation;
    }

    public void setFragmentExitAnimation(int fragmentExitAnimation) {
        this.fragmentExitAnimation = fragmentExitAnimation;
    }

    public int getFragmentPopEnterAnimation() {
        return fragmentPopEnterAnimation;
    }

    public void setFragmentPopEnterAnimation(int fragmentPopEnterAnimation) {
        this.fragmentPopEnterAnimation = fragmentPopEnterAnimation;
    }

    public int getFragmentPopExitAnimation() {
        return fragmentPopExitAnimation;
    }

    public void setFragmentPopExitAnimation(int fragmentPopExitAnimation) {
        this.fragmentPopExitAnimation = fragmentPopExitAnimation;
    }


    public void setSnackbarActionTextColor(int snackbarActionTextColor) {
        this.snackbarActionTextColor = snackbarActionTextColor;
    }

    public int getSnackbarActionTextColor() {
        return snackbarActionTextColor;
    }

    public void setSnackbarTextColor(int snackbarTextColor) {
        this.snackbarTextColor = snackbarTextColor;
    }

    public int getSnackbarTextColor() {
        return snackbarTextColor;
    }

    public void setSnackbarTextSize(int snackbarTextSize) {
        this.snackbarTextSize = snackbarTextSize;
    }

    public int getSnackbarTextSize() {
        return snackbarTextSize;
    }


    public void setPreferredStringLocale(String preferredStringLocale) {
        this.preferredStringLocale = preferredStringLocale;
    }

    public String getPreferredStringLocale() {
        return preferredStringLocale;
    }

    public int getSpinnerLabelValidColor() {
        return spinnerLabelValidColor;
    }

    public void setSpinnerLabelValidColor(int spinnerLabelValidColor) {
        this.spinnerLabelValidColor = spinnerLabelValidColor;
    }

    public int getSpinnerLabelErrorColor() {
        return spinnerLabelErrorColor;
    }

    public void setSpinnerLabelErrorColor(int spinnerLabelErrorColor) {
        this.spinnerLabelErrorColor = spinnerLabelErrorColor;
    }

    public int getEditTextInvalidUsernameErrorMessage() {
        return editTextInvalidUsernameErrorMessage;
    }

    public void setEditTextInvalidUsernameErrorMessage(int editTextInvalidUsernameErrorMessage) {
        this.editTextInvalidUsernameErrorMessage = editTextInvalidUsernameErrorMessage;
    }

    public int getEditTextInvalidEmailErrorMessage() {
        return editTextInvalidEmailErrorMessage;
    }

    public void setEditTextInvalidEmailErrorMessage(int editTextInvalidEmailErrorMessage) {
        this.editTextInvalidEmailErrorMessage = editTextInvalidEmailErrorMessage;
    }

    public int getEditTextInvalidNumberRangeErrorMessage() {
        return editTextInvalidNumberRangeErrorMessage;
    }

    public void setEditTextInvalidNumberRangeErrorMessage(int editTextInvalidNumberRangeErrorMessage) {
        this.editTextInvalidNumberRangeErrorMessage = editTextInvalidNumberRangeErrorMessage;
    }

    public int getEditTextInvalidPositiveNumberErrorMessage() {
        return editTextInvalidPositiveNumberErrorMessage;
    }

    public void setEditTextInvalidPositiveNumberErrorMessage(int editTextInvalidPositiveNumberErrorMessage) {
        this.editTextInvalidPositiveNumberErrorMessage = editTextInvalidPositiveNumberErrorMessage;
    }

    public int getEditTextTextLengthErrorMessage() {
        return editTextTextLengthErrorMessage;
    }

    public void setEditTextTextLengthErrorMessage(int editTextTextLengthErrorMessage) {
        this.editTextTextLengthErrorMessage = editTextTextLengthErrorMessage;
    }

    public int getEnableRequiredPermissionsMessage() {
        return enableRequiredPermissionsMessage;
    }

    public void setEnableRequiredPermissionsMessage(int enableRequiredPermissionsMessage) {
        this.enableRequiredPermissionsMessage = enableRequiredPermissionsMessage;
    }

    public List<AndroidPermissionItem> getDangerousPermissions() {
        return dangerousPermissions;
    }

    public void setDangerousPermissions(List<AndroidPermissionItem> dangerousPermissions) {
        this.dangerousPermissions = dangerousPermissions;
    }

    public int getEditTextDateEarlierThanTodayErrorMessage() {
        return editTextDateEarlierThanTodayErrorMessage;
    }

    public void setEditTextDateEarlierThanTodayErrorMessage(int editTextDateLaterThanErrorMessage) {
        this.editTextDateEarlierThanTodayErrorMessage = editTextDateLaterThanErrorMessage;
    }

    public int getEditTextStartDateGreaterThanTodayErrorMessage() {
        return editTextStartDateGreaterThanTodayErrorMessage;
    }

    public void setEditTextStartDateGreaterThanTodayErrorMessage(int editTextStartDateGreaterThanTodayErrorMessage) {
        this.editTextStartDateGreaterThanTodayErrorMessage = editTextStartDateGreaterThanTodayErrorMessage;
    }

    public int getEditTextPhoneNumberValidatorErrorMessage() {
        return editTextPhoneNumberValidatorErrorMessage;
    }

    public void setEditTextPhoneNumberValidatorErrorMessage(int editTextPhoneNumberValidatorErrorMessage) {
        this.editTextPhoneNumberValidatorErrorMessage = editTextPhoneNumberValidatorErrorMessage;
    }

    public int getMaximumCurrencyFractionDigits() {
        return maximumCurrencyFractionDigits;
    }

    public void setMaximumCurrencyFractionDigits(int maximumCurrencyFractionDigits) {
        this.maximumCurrencyFractionDigits = maximumCurrencyFractionDigits;
    }
}
