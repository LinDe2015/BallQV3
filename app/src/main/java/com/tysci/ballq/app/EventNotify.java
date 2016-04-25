package com.tysci.ballq.app;

/**
 * Created by QianBao on 2016/4/25.
 * the class that notify
 */
public class EventNotify {
    public final String json;
    public final Class<?>[] notifyClasses;

    public EventNotify(String json, Class<?>[] notifyClasses) {
        this.json = json;
        this.notifyClasses = notifyClasses;
    }
}
