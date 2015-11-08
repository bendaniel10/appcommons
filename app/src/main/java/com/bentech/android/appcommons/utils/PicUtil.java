package com.bentech.android.appcommons.utils;

/**
 * Created by Daniel on 9/7/2015.
 */
//http://www.java2s.com/Code/Android/2D-Graphics/SaveBitmaptoandloadfromExternalStorage.htm

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.Log;

import com.bentech.android.appcommons.AppCommons;
import com.bentech.android.appcommons.config.AppCommonsConfiguration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PicUtil {
    private static final String TAG = PicUtil.class.getSimpleName();

    public static File getSavePath() {
        File path;
        if (hasSDCard()) { // SD card
            path = new File(String.format("%s/%s/", getSDCardPath(), AppCommons.getApplication().getApplicationInfo().labelRes));
            path.mkdir();
        } else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    public static Bitmap loadFromFile(String filename) {
        //http://stackoverflow.com/a/11047327 - Weird image rotation.
        try {
            File f = new File(filename);

            if (!f.exists()) {
                return null;
            }
            ExifInterface exif = new ExifInterface(f.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            int angle = 0;

            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
                angle = 90;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
                angle = 180;
            } else if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
                angle = 270;
            }

            Matrix mat = new Matrix();
            mat.postRotate(angle);

            // The new size we want to scale to
            AppCommonsConfiguration appCommonsConfiguration = AppCommons.getAppCommonsConfiguration();
            final int REQUIRED_SIZE = appCommonsConfiguration.getPicUtilImageSampleSize();

            Bitmap bmp = decodeSampledBitmapFromFileInputStream(f, REQUIRED_SIZE, REQUIRED_SIZE);

            return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
    }

    //http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
    public static Bitmap decodeSampledBitmapFromFileInputStream(File file,
                                                                int reqWidth, int reqHeight) throws FileNotFoundException {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(file), null, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(new FileInputStream(file), null, options);
    }

    //http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    public static void saveToFile(String filename, Bitmap bmp) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            bmp.compress(CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    Log.d(TAG, e.getMessage());
                }
            }
        }
    }

    private static boolean hasSDCard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    private static String getSDCardPath() {
        File path = Environment.getExternalStorageDirectory();
        return path.getAbsolutePath();
    }

}