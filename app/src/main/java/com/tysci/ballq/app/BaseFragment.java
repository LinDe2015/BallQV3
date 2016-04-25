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
 */
abstract public class BaseFragment extends Fragment{
    protected final String Tag=this.getClass().getSimpleName();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=createView(inflater,container,savedInstanceState);
        if(isBindEventBus()){
            EventBus.getDefault().register(this);
        }
        initViews(view);
        return view;
    }

    abstract protected boolean isBindEventBus();

    abstract protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    abstract protected void initViews(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(isBindEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }
}
