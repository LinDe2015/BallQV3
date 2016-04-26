package com.tysci.ballq.app;

import android.os.Handler;
import android.os.Looper;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by QianBao on 2016/4/26.
 * notify event by EventBus
 *
 * @see EventBus
 */
public class EventPost {
    private EventPost() {
    }

    public static void postMainThread(String s, Class<?>... classes) {
        postMainThread(0, s, classes);
    }

    public static void postMainThread(long delay, String s, Class<?>... classes) {
        final EventNotify en = new EventNotify(s, classes);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(en);
            }
        }, delay);
    }
}
