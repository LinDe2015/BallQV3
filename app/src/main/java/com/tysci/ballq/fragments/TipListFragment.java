package com.tysci.ballq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tysci.ballq.app.LazyLoadingFragment;

/**
 * Created by QianBao on 2016/4/27.
 */
public class TipListFragment extends LazyLoadingFragment {
    @Override
    protected boolean onFirstVisibleToUser() {
        return false;
    }

    @Override
    protected void onVisibleToUser() {
    }

    @Override
    protected void onInvisibleToUser() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return null;
    }

    @Override
    protected void initViews(View view) {
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }
}
