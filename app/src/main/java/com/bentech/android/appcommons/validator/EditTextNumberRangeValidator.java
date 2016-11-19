package com.bentech.android.appcommons.validator;

import android.view.View;
import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.EditTextUtils;
import com.google.common.primitives.Doubles;

/**
 * Created by Daniel on 05/11/2015.
 */
public class EditTextNumberRangeValidator extends EditTextValidator {
    private final Range range;

    public EditTextNumberRangeValidator(EditText editText, Range range) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextInvalidNumberRangeErrorMessage());
        this.range = range;
    }


    @Override
    public boolean isValid() {
        String text = EditTextUtils.getText(editText);
        if (EditTextUtils.testIsEmpty(editText) || text.isEmpty() || (Doubles.tryParse(EditTextUtils.getText(editText)) == null))
            return false;

        Double value = Double.parseDouble(text);
        return editText.getVisibility() != View.VISIBLE || (value >= range.getLow() && value <= range.getHigh());
    }


    @Override
    public String buildErrorMessage() {
        if (errorMessage != null) {
            return  errorMessage;
        }
        return editText == null ? "" : range.getHigh() == range.getLow() ? String.format("%s %s", editText.getContext().getString(errorMessageId), range.getHigh()) : String.format("%s %s", editText.getContext().getString(errorMessageId), range.toString());
    }

}
