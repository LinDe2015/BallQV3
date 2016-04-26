package com.tysci.ballq.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isNeedBindEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Nullable
    @Override
    public abstract View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    abstract protected void initViews(View view);

    @Override
    public abstract boolean isNeedBindEventBus();

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
        initViews(view);
    }

    protected final <T extends View> T getView(@IdRes int viewId) {
        return getView(mRootView, viewId);
    }

    protected final <T extends View> T getView(View parent, @IdRes int viewId) {
        //noinspection unchecked
        return (T) parent.findViewById(viewId);
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
