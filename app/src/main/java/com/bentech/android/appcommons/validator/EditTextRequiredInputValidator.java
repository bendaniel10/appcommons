package com.bentech.android.appcommons.validator;

import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.EditTextUtils;

/**
 * Created by Daniel on 9/11/2015.
 */
public class EditTextRequiredInputValidator extends EditTextValidator {


    public EditTextRequiredInputValidator(EditText editText) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextRequiredErrorMessage());
    }

    @Override
    public boolean isValid() {
        return !EditTextUtils.testIsEmpty(editText);
    }
}
