package com.bentech.android.appcommons.preference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.bentech.android.appcommons.AppCommons;
import com.google.gson.Gson;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by Daniel on 08/11/2015.
 */
public abstract class Preference implements Serializable {

    protected transient SharedPreferences sharedPreference;
    protected transient Context context;
    private transient String preferenceKey;

    public Preference() {
        this.context = AppCommons.getApplication();
        this.preferenceKey = getClass().getSimpleName();
        this.sharedPreference = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
    }

    private Preference inflatePreference() {
        Gson gson = new Gson();
        String json = sharedPreference.getString(preferenceKey, "");
        Log.d("Retrieved JSON: " + getClass().getSimpleName(), json);
        return gson.fromJson(json, this.getClass());
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public void savePreference() {
        //http://stackoverflow.com/a/29670311 ?
        SharedPreferences.Editor editor = sharedPreference.edit();
        Gson gson = new Gson();
        String json = gson.toJson(this);
        Log.d("Saving JSON: " + getClass().getSimpleName(), json);

        editor.putString(preferenceKey, json);
        editor.commit();
    }

    @SuppressLint({"CommitPrefEdits", "ApplySharedPref"})
    public void clearPreference() {
        SharedPreferences.Editor editor = sharedPreference.edit();

        Log.d("Clearing JSON: " + getClass().getSimpleName(), "{}");
        editor.putString(preferenceKey, "{}");
        editor.commit();
    }

    @SuppressWarnings({"TryWithIdenticalCatches", "unchecked"})
    public static <T extends Preference> T getPreference(Class<T> preferenceClass) {

        Preference preference = null;
        try {
            preference = preferenceClass.getDeclaredConstructor().newInstance();
            String savedPreferenceKey = preferenceClass.getSimpleName();

            if (preference.inflatePreference() == null) {
                Log.d(Preference.class.getSimpleName(), "Creating new preference");
                preference.savePreference();
            } else {
                preference = preference.inflatePreference();
                //These variables are transient so set them up again, yourself.
                preference.context = AppCommons.getApplication();
                preference.sharedPreference = PreferenceManager.getDefaultSharedPreferences(preference.context.getApplicationContext());
                preference.preferenceKey = savedPreferenceKey;
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return (T) preference;
    }

}
