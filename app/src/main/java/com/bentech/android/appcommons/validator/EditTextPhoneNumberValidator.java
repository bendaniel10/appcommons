package com.bentech.android.appcommons.validator;

import android.view.View;
import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.EditTextUtils;

/**
 * Created by bendaniel on 07/09/2016.
 */

public class EditTextPhoneNumberValidator extends EditTextValidator {
    public EditTextPhoneNumberValidator(EditText editText) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextPhoneNumberValidatorErrorMessage());
    }

    @Override
    public boolean isValid() {
        return editText.getVisibility() == View.GONE || android.util.Patterns.PHONE.matcher(EditTextUtils.getText(editText)).matches();
    }
}
