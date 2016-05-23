package com.bentech.android.appcommons.validator;

import android.view.View;
import android.widget.EditText;

import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.utils.EditTextUtils;


/**
 * Created by Daniel on 9/11/2015.
 */
public class EditTextRangeValidator extends EditTextValidator {
    private final Range range;

    public EditTextRangeValidator(EditText editText, Range range) {
        super(editText, R.string.label_invalid_input_range);
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
        return editText == null ? "" : range.getHigh() == range.getLow() ? String.format("%s %s", editText.getContext().getString(errorMessageId), (int) range.getHigh()) : String.format("%s %s", editText.getContext().getString(errorMessageId), range.toString());
    }
}
