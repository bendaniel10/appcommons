package com.bentech.android.appcommons.validator;

import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.utils.DateUtils;


/**
 * Created by Daniel on 21/01/2016.
 */
public class EditTextStartDateGreaterThanTodayValidator extends EditTextValidator {
    private final long startDateMilliSecs;

    public EditTextStartDateGreaterThanTodayValidator(EditText startDateEditText, long startDateMilliSecs) {
        super(startDateEditText, AppCommons.getAppCommonsConfiguration().getEditTextStartDateGreaterThanTodayErrorMessage());
        this.startDateMilliSecs = startDateMilliSecs;
    }

    @Override
    public boolean isValid() {
        return DateUtils.isDateGreaterThanToday(startDateMilliSecs);
    }
}
