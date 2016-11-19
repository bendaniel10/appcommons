package com.bentech.android.appcommons.validator;

import android.widget.EditText;

/**
 * Created by Daniel on 9/11/2015.
 */
public abstract class EditTextValidator {

    protected EditText editText;
    protected int errorMessageId;
    protected String errorMessage;//error message for this instance.

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

    protected int getErrorMessageId() {
        return errorMessageId;
    }

    protected void setErrorMessageId(int errorMessageId) {
        this.errorMessageId = errorMessageId;
    }

    public String buildErrorMessage() {
        if (editText == null || editText.getContext() == null) {
            return "";
        }
        return errorMessage == null ? editText.getContext().getString(errorMessageId) : errorMessage;
    }

    public abstract boolean isValid();

    public EditTextValidator setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
        return this;
    }
}
