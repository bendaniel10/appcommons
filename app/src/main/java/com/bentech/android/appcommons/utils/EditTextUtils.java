package com.bentech.android.appcommons.utils;

import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.config.AppCommonsConfiguration;
import com.bentech.android.appcommons.validator.EditTextValidator;


/**
 * Created by Daniel on 7/29/2015.
 */
public final class EditTextUtils {
    private String TAG = EditTextUtils.class.getSimpleName();

    public static boolean isEmpty(EditText editText) {
        AppCommonsConfiguration appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();

        boolean isEmpty = testIsEmpty(editText);

        if (isEmpty) {
            if (editText.getVisibility() != View.VISIBLE) {
                return false;
            }
            setError(editText, appCommonsConfiguration.getEditTextRequiredErrorMessage());
        } else {
            clearError(editText);
        }
        return isEmpty;
    }

    public static boolean isInValid(EditTextValidator editTextValidator) {
        boolean isValid = editTextValidator.isValid();
        String errorMessage;
        if (!isValid) {
            setError(editTextValidator.getEditText(), editTextValidator.buildErrorMessage());
            errorMessage = editTextValidator.buildErrorMessage();
            Log.d("APPCOMMONS", errorMessage);
        } else {
            clearError(editTextValidator.getEditText());
        }
        return !isValid;
    }

    public static void setError(EditText editText, String errorString) {
        if (editText.getParent() != null && editText.getParent() instanceof TextInputLayout) {
            ((TextInputLayout) editText.getParent()).setError(editText.getHint() == null ? errorString : editText.getHint());
            ((TextInputLayout) editText.getParent()).setErrorEnabled(true);
        } else if (editText.getParent() != null
                && editText.getParent().getParent() != null && editText.getParent().getParent() instanceof TextInputLayout) {
            ((TextInputLayout) editText.getParent().getParent()).setError(editText.getHint() == null ? errorString : editText.getHint());
            ((TextInputLayout) editText.getParent().getParent()).setErrorEnabled(true);
        } else {
            editText.setError(editText.getHint() == null ? errorString : editText.getHint());
        }
    }

    public static void setError(EditText editText, int errorStringId) {
        if (editText != null) {
            setError(editText, editText.getResources().getString(errorStringId));
        }
    }

    public static void clearError(EditText editText) {
        if (editText.getParent() != null && editText.getParent() instanceof TextInputLayout) {
            ((TextInputLayout) editText.getParent()).setErrorEnabled(false);
            ((TextInputLayout) editText.getParent()).setError(null);
        }else if (editText.getParent() != null
                && editText.getParent().getParent() != null && editText.getParent().getParent() instanceof TextInputLayout) {
            ((TextInputLayout) editText.getParent().getParent()).setErrorEnabled(false);
            ((TextInputLayout) editText.getParent().getParent()).setError(null);
        }  else {
            editText.setError(null);
        }
    }

    public static boolean isEmpty(EditText... editTexts) {
        boolean isEmpty = false;
        for (EditText editText : editTexts) {
            if (isEmpty(editText)) {
                isEmpty = true;
            }
        }

        return isEmpty;
    }

    public static boolean isInValid(EditTextValidator... editTextValidators) {
        boolean isInValid = false;
        for (EditTextValidator editTextValidator : editTextValidators) {
            if (isInValid(editTextValidator)) {
                isInValid = true;
            }
        }
        return isInValid;
    }

    public static String getText(EditText editText) {
        if (editText == null) {
            return "";
        } else {
            return editText.getText().toString().trim();
        }
    }

    public static void reset(EditText editText) {
        if (editText != null) {
            editText.setText("");
        }
    }

    public static double getAmount(EditText editText) {
        if (editText == null) {
            return 0D;
        } else {
            return CurrencyUtils.parseAmount(getText(editText));
        }
    }

    public static void handleViewPassword(EditText passwordField, MotionEvent event) {
        if (passwordField == null || event == null)
            return;

        int selectedPosition = passwordField.getSelectionEnd();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            passwordField.setTransformationMethod(null);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            passwordField.setTransformationMethod(new PasswordTransformationMethod());
        }

        passwordField.setSelection(selectedPosition, selectedPosition);
    }

    public static boolean testIsEmpty(EditText editText) {
        return editText == null || (TextUtils.isEmpty(getText(editText)) && editText.getVisibility() == View.VISIBLE);
    }
}
