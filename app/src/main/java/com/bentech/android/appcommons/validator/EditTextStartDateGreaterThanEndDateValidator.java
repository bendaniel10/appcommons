package com.bentech.android.appcommons.validator;

import android.widget.EditText;

import com.bentech.android.appcommons.R;


/**
 * Created by Daniel on 21/01/2016.
 */
public class EditTextStartDateGreaterThanEndDateValidator extends EditTextValidator {
    private final long startDateMilliSecs;
    private final long endDateMilliSecs;

    public EditTextStartDateGreaterThanEndDateValidator(EditText editText, long startDateMilliSecs, long endDateMilliSecs) {
        super(editText, R.string.label_end_date_must_be_greater_than_start_date);
        this.startDateMilliSecs = startDateMilliSecs;
        this.endDateMilliSecs = endDateMilliSecs;
    }

    @Override
    public boolean isValid() {
        return ((endDateMilliSecs) >= (startDateMilliSecs));
    }
}
