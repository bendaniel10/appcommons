package com.bentech.android.appcommons.validator;

import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.EditTextUtils;

/**
 * Created by Daniel on 9/11/2015.
 */
public class EditTextEmailValidator extends EditTextValidator {

    public EditTextEmailValidator(EditText editText) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextInvalidEmailErrorMessage());
    }

    @Override
    public boolean isValid() {
        if (EditTextUtils.testIsEmpty(editText))
            return false;
        return android.util.Patterns.EMAIL_ADDRESS.matcher(EditTextUtils.getText(editText)).matches();
    }
}
