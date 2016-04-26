package com.tysci.ballq.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/4/25.
 * All fragment base this
 */
abstract public class BaseFragment extends Fragment implements IEvent {
    protected final String TAG = this.getClass().getSimpleName();
    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = createView(inflater, container, savedInstanceState);
        if (isNeedBindEventBus()) {
            EventBus.getDefault().register(this);
        }
        initViews(view);
        return view;
    }

    abstract protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    abstract protected void initViews(View view);

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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mRootView != null) {
            if (mRootView instanceof ViewGroup) {
                ((ViewGroup) mRootView).removeAllViews();
            }
        }
        mRootView = null;
        if (isNeedBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }
}
