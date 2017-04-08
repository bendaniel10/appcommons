package com.bentech.android.appcommons.utils;

/**
 * Created by Daniel on 9/7/2015.
 */
//http://www.java2s.com/Code/Android/2D-Graphics/SaveBitmaptoandloadfromExternalStorage.htm

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;

import com.bentech.android.appcommons.AppCommons;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PicUtil {

    private static final String TAG = PicUtil.class.getSimpleName();
    public static int HEIGHT_MAINTAIN_ASPECT_RATIO = -1;

    public static File getSavePath() {
        File path;
        if (hasSDCard()) { // SD card
            path = new File(String.format("%s/%s/", getSDCardPath(), getApplicationName(AppCommons.getApplication())));
            path.mkdir();
        } else {
            path = Environment.getDataDirectory();
        }
        return path;
    }

    public static String getApplicationName(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        int stringId = applicationInfo.labelRes;
        return stringId == 0 ? applicationInfo.nonLocalizedLabel.toString() : context.getString(stringId);
    }

    public static String getCacheFilename() {
        File f = getSavePath();
        return f.getAbsolutePath() + "/cache.png";
    }

    @Deprecated
    /**
     * This method is deprecated, use loadFromFile(String, int, int)
     */
    public static Bitmap loadFromFile(String filename) {
        try {
            File f = new File(filename);
            if (!f.exists()) {
                return null;
            }
            return BitmapFactory.decodeFile(filename);
        } catch (Exception e) {
            return null;
        }
    }

    public static Bitmap loadFromCacheFile(int width, int height) {
        return loadFromFile(getCacheFilename(), width, height);
    }

    public static void saveToCacheFile(Bitmap bmp) {
        saveToFile(getCacheFilename(), bmp);
    }

    public static void saveToFile(String filename, Bitmap bmp) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            bmp.compress(CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }

    public static void saveToFile(String filename, Bitmap bmp, int quality, CompressFormat format) {
        try {
            FileOutputStream out = new FileOutputStream(filename);
            bmp.compress(format, quality, out);
            out.flush();
            out.close();
        } catch (Exception e) {
        }
    }

    public static boolean hasSDCard() { // SD????????
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    public static String getSDCardPath() {
        File path = Environment.getExternalStorageDirectory();
        return path.getAbsolutePath();
    }


    /**
     * Load image as bitmap from file
     *
     * @param filename the path to the file
     * @param width    the width to load the file as
     * @param height   The height to load the file as. PictUtil.HEIGHT_MAINTAIN_ASPECT_RATIO can be used here
     * @return the Bitmap from file
     */
    public static Bitmap loadFromFile(String filename, int width, int height) {
        //http://stackoverflow.com/a/11047327 - Weird image rotation.
        try {
            int i = 0;
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


            Bitmap bmp = decodeSampledBitmapFromFileInputStream(f, width, height);

            return Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight(), mat, true);
        } catch (Exception e) {
            Log.d(TAG, String.valueOf(e.getMessage()));
            return null;
        }
    }

    //http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
    private static Bitmap decodeSampledBitmapFromFileInputStream(File file,
                                                                 int reqWidth, int reqHeight) throws FileNotFoundException {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(new FileInputStream(file), null, options);

        if (reqHeight == HEIGHT_MAINTAIN_ASPECT_RATIO) {
            float aspectRatio = options.outWidth / (float) options.outHeight;
            reqHeight = Math.round(reqWidth / aspectRatio);
            Log.d(TAG, String.format("Maintaining aspect ratio: Size is: W %s, h %s", reqWidth, reqHeight));
        }

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(new FileInputStream(file), null, options);
    }

    //http://developer.android.com/training/displaying-bitmaps/load-bitmap.html
    private static int calculateInSampleSize(
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

    /**
     * http://stackoverflow.com/a/17410076
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static int convertDpToPixel(float dp, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    /**
     * http://stackoverflow.com/a/17410076
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px      A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static int convertPixelsToDp(float px, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    //http://stackoverflow.com/a/4989543
    public static byte[] convertBitmapToByteArray(Bitmap bitmap, CompressFormat compressFormat) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, 100, stream);
        return stream.toByteArray();
    }

    public static byte[] BitmapToGrayScale(Bitmap bmp) {
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        byte[] grayScaleRaw = new byte[width * height];
        int i = 0;
        for (int y = 0; y < height; ++y) {
            for (int x = 0; x < width; ++x) {
                // get one pixel color
                int pixel = bmp.getPixel(x, y);
                // retrieve color of all channel
                int R = Color.red(pixel);
                int G = Color.green(pixel);
                int B = Color.blue(pixel);
                // take conversion up to one single value
                grayScaleRaw[i] = (byte) (0.299 * R + 0.587 * G + 0.114 * B);
                i++;
            }
        }
        return grayScaleRaw;

    }

    //http://stackoverflow.com/a/4989543
    public static byte[] convertBitmapToByteArray(Bitmap bitmap, CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(compressFormat, quality, stream);
        return stream.toByteArray();
    }
}