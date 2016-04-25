package com.tysci.ballq.app;

/**
 * Created by QianBao on 2016/4/25.
 * Send event to notify all to
 */
public interface IEvent {
    boolean isNeedBindEventBus();

    void onEventMainThread(EventNotify notify);

    void onNotifyJson(String json);
}
