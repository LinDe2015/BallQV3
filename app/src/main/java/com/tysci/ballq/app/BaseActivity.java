package com.tysci.ballq.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.pgyersdk.crash.PgyCrashManager;
import com.tysci.ballq.networks.HttpClientUtil;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by HTT on 2016/4/25.
 * All activity base this
 */
abstract public class BaseActivity extends AppCompatActivity implements IEvent {
    protected final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PgyCrashManager.register(this);
        ActivityStacksManager.getActivityStacksManager().addActivity(this);
        if (isNeedBindEventBus()) {
            EventBus.getDefault().register(this);
        }
        setContentView();
        try {
            initViews();
        } catch (Exception e) {
            e.printStackTrace();
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    abstract protected void setContentView();

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onEventMainThread(EventNotify notify) {
        for (Class<?> aClass : notify.notifyClasses) {
            if (aClass == this.getClass()) {
                onNotifyJson(notify.json);
            }
        }
    }

    @Override
    public void onNotifyJson(String json) {
    }

    abstract protected void initViews() throws Exception;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpClientUtil.getHttpClientUtil().cancelTag(TAG);
        if (isNeedBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        ActivityStacksManager.getActivityStacksManager().finishActivity(this);
    }
}
