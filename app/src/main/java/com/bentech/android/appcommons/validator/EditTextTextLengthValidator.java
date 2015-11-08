package com.bentech.android.appcommons.validator;

import android.view.View;
import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.EditTextUtils;

/**
 * Created by Daniel on 9/11/2015.
 */
public class EditTextTextLengthValidator extends EditTextValidator {
    private final Range range;

    public EditTextTextLengthValidator(EditText editText, Range range) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextTextLengthErrorMessage());
        this.range = range;
    }

    @Override
    public boolean isValid() {
        if (EditTextUtils.testIsEmpty(editText))
            return false;
        String text = EditTextUtils.getText(editText);
        return editText.getVisibility() != View.VISIBLE || (text.length() >= range.getLow() && text.length() <= range.getHigh());
    }

    @Override
    public String buildErrorMessage() {
        return editText == null ? "" : range.getHigh() == range.getLow() ? String.format("%s %s", editText.getContext().getString(errorMessageId), range.getHigh()) : String.format("%s %s", editText.getContext().getString(errorMessageId), range.toString());
    }
}
