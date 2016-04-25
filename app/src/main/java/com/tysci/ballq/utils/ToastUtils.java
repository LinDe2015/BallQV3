package com.tysci.ballq.utils;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.tysci.ballq.R;
import com.tysci.ballq.app.BallQApplication;

/**
 * Created by LinDe on 2016-01-27.
 *
 * @see Toast
 */
@SuppressWarnings("unused")
public class ToastUtils {
    private static final long SHOW_SAME_DELAY = 3000L;

    private static String lastShowString;
    private static long lastShowTimeMillis;

    private ToastUtils() {
    }

    public static void show(Context c, String s) {
        show(c, s, false);
    }

    public static void show(Context c, String s, boolean showLong) {
        final TextView tv = (TextView) ((BallQApplication) c).createView(R.layout.toast_tv);
        tv.setText(s);
        final Toast t = new Toast(c);
        t.setView(tv);
        t.setDuration(showLong ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
    }
}
