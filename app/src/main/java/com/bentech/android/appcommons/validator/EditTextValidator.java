package com.bentech.android.appcommons.validator;

import android.widget.EditText;

/**
 * Created by Daniel on 9/11/2015.
 */
public abstract class EditTextValidator {

    protected EditText editText;
    protected int errorMessageId;

    public EditTextValidator(EditText editText, int errorMessageId) {
        this.editText = editText;
        this.errorMessageId = errorMessageId;
    }

    public EditText getEditText() {
        return editText;
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }

    public int getErrorMessageId() {
        return errorMessageId;
    }

    public void setErrorMessageId(int errorMessageId) {
        this.errorMessageId = errorMessageId;
    }

    public String buildErrorMessage() {
        if (editText == null || editText.getContext() == null) {
            return "";
        }
        return editText.getContext().getString(errorMessageId);
    }

    public abstract boolean isValid();
}
