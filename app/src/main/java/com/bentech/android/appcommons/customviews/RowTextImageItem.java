package com.bentech.android.appcommons.customviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bentech.android.appcommons.R;

/**
 * Created by Daniel on 20/11/2015.
 */
public class RowTextImageItem extends RelativeLayout {

    public RowTextImageItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_row_text_image_item, this, true);
    }

    public RowTextImageItem(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.custom_view_row_text_image_item, this, true);
    }

    public void setImage(Drawable image) {
        ((ImageView) findViewById(R.id.image)).setImageDrawable(image);
    }

    public void setText(CharSequence text) {
        ((TextView) findViewById(R.id.text)).setText(text);
    }
}
