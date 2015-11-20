package com.bentech.android.appcommons.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentech.android.appcommons.R;

public class SingleTextRowItem extends RelativeLayout {

    public SingleTextRowItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_single_text_row, this, true);
    }

    public SingleTextRowItem(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_single_text_row, this, true);
    }

    public void setText(CharSequence text) {
        ((TextView) findViewById(R.id.title)).setText(text);
    }
}