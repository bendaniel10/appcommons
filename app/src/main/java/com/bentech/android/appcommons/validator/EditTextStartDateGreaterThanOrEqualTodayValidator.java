package com.bentech.android.appcommons.validator;

import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.DateUtils;

/**
 * Created by bendaniel on 05/09/2016.
 */

public class EditTextStartDateGreaterThanOrEqualTodayValidator extends EditTextValidator {
    private final long editTextDate;

    public EditTextStartDateGreaterThanOrEqualTodayValidator(EditText editText, long editTextDate) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextDateEarlierThanTodayErrorMessage());
        this.editTextDate = editTextDate;
    }

    @Override
    public boolean isValid() {
        return DateUtils.isDateGreaterThanOrEqualToday(editTextDate);
    }

}
