package com.tysci.ballq.utils;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;

/**
 * Created by LinDe on 2016-03-12 0012.
 * Resources Utils
 */
@SuppressWarnings("deprecation")
public class ResUtils {
    private ResUtils() {
    }

    public static int getColor(Resources res, @ColorRes int color) {
        return res.getColor(color);
    }

    public static int getColor(String colorParams) {
        try {
            return Color.parseColor(colorParams);
        } catch (Exception e) {
            return 0;
        }
    }

    public static int getInteger(Resources res, @IntegerRes int integerResID) {
        return res.getInteger(integerResID);
    }

    public static String getString(Resources res, @StringRes int string) {
        return res.getString(string);
    }

    public static String[] getArray(Resources res, @ArrayRes int array) {
        return res.getStringArray(array);
    }
}
