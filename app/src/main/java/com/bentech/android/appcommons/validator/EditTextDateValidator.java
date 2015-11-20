package com.bentech.android.appcommons.validator;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.EditText;

import com.bentech.android.appcommons.R;
import com.bentech.android.appcommons.utils.EditTextUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Created by Daniel on 09/11/2015.
 */
public class EditTextDateValidator extends EditTextValidator {
    private static final String TAG = EditTextValidator.class.getSimpleName();
    //http://stackoverflow.com/a/15504877
    private final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private final Pattern pattern;
    DateFormat dateFormat;

    @SuppressLint("SimpleDateFormat")
    public EditTextDateValidator(EditText editText) {
        super(editText, R.string.invalid_date);
        pattern = Pattern.compile(DATE_REGEX);
        dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, Locale.getDefault());
    }

    @Override
    public boolean isValid() {
        if (EditTextUtils.testIsEmpty(editText))
            return false;
        try {
            dateFormat.parse(EditTextUtils.getText(editText));
            return true;
        } catch (ParseException ex) {
            Log.d(TAG, ex.getMessage());
            return false;
        }
    }
}
