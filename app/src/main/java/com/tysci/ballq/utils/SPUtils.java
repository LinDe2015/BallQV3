package com.tysci.ballq.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.tysci.ballq.R;

import java.util.Set;

/**
 * Created by LinDe on 2016-03-11.
 * SPUtils
 */
public class SPUtils {

    public static SharedPreferences getSP(final Context c) {
        return c.getSharedPreferences(ResUtils.getString(c.getResources(), R.string.sp_utils_xml_name), Context.MODE_PRIVATE);
    }

    public static String read(final Context c, String key, String defaultValue) {
        final SharedPreferences sp = getSP(c);
        return sp.getString(key, defaultValue);
    }

    public static void write(final Context c, String key, String value) {
        final SharedPreferences sp = getSP(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static int read(final Context c, String key, int defaultValue) {
        final SharedPreferences sp = getSP(c);
        return sp.getInt(key, defaultValue);
    }

    public static void write(final Context c, String key, int value) {
        final SharedPreferences sp = getSP(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static boolean read(final Context c, String key, boolean defaultValue) {
        final SharedPreferences sp = getSP(c);
        return sp.getBoolean(key, defaultValue);
    }

    public static void write(final Context c, String key, boolean value) {
        final SharedPreferences sp = getSP(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static float read(final Context c, String key, float defaultValue) {
        final SharedPreferences sp = getSP(c);
        return sp.getFloat(key, defaultValue);
    }

    public static void write(final Context c, String key, float value) {
        final SharedPreferences sp = getSP(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public static long read(final Context c, String key, long defaultValue) {
        final SharedPreferences sp = getSP(c);
        return sp.getLong(key, defaultValue);
    }

    public static void write(final Context c, String key, long value) {
        final SharedPreferences sp = getSP(c);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static Set<String> read(final Context c, String key, Set<String> defaultValue) {
        final SharedPreferences sp = getSP(c);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            return sp.getStringSet(key, defaultValue);
        }
        return null;
    }

    public static boolean write(final Context c, String key, Set<String> value) {
        final SharedPreferences sp = getSP(c);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putStringSet(key, value);
            editor.apply();
            return true;
        }
        return false;
    }
}
