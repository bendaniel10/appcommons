package com.bentech.android.appcommons.validator;

import android.widget.EditText;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.utils.EditTextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 9/11/2015.
 */
public class EditTextUsernameValidator extends EditTextValidator {
    private static final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
    private Pattern pattern;

    public EditTextUsernameValidator(EditText editText) {
        super(editText, AppCommons.getAppCommonsConfiguration().getEditTextInvalidUsernameErrorMessage());
        pattern = Pattern.compile(USERNAME_PATTERN);
    }

    @Override
    public boolean isValid() {
        if (EditTextUtils.testIsEmpty(editText))
            return false;
        Matcher matcher = pattern.matcher(EditTextUtils.getText(editText));
        return matcher.matches();
    }
}
