package com.bentech.android.appcommons.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;

/**
 * Created by Daniel on 7/30/2015.
 */
public class DrawableUtils {

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    public static void setBackgroundDrawable(View view, Drawable drawable) {
        int sdk = android.os.Build.VERSION.SDK_INT;
        if (sdk < 17) {
            view.setBackgroundDrawable(drawable);
        } else {
            view.setBackground(drawable);
        }
    }

    public static void setBackgroundDrawable(View view, int drawableId) {
        setBackgroundDrawable(view, getDrawable(view.getContext(), drawableId));
    }


    public static Drawable getDrawable(Context context, int drawableId) {
        return ContextCompat.getDrawable(context, drawableId);
    }

    public static Drawable getDrawableFromBase64String(Context context, String base64String) {
        if (base64String == null) {
            return null;
        }
        try {
            byte[] decodedString = Base64.decode(base64String, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            return new BitmapDrawable(context.getResources(), decodedByte);
        } catch (Exception ex) {
            return null;
        }
    }

    //http://stackoverflow.com/a/9224180
    public static String getStringfromBitmapDrawable(Bitmap bitmap) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream .toByteArray();

        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    public static Drawable tintMyDrawable(Drawable drawable, @ColorInt int color) {
        drawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(drawable, color);
        DrawableCompat.setTintMode(drawable, PorterDuff.Mode.SRC_IN);
        return drawable;
    }

}
