package com.tysci.ballq.app;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by LinDe on 2016/4/26.
 * used with ViewPager
 */
public abstract class LazyLoadingFragment extends BaseFragment {
    private boolean isCanLoading;
    private boolean isVisibleToUser;
    private boolean isFirstVisibleToUser;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        isCanLoading = false;
        isVisibleToUser = false;
        isFirstVisibleToUser = true;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        onVisibleChange(isFirstVisibleToUser);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isCanLoading = true;
        onVisibleChange(true);
    }

    private void onVisibleChange(boolean isFirst) {
        if (!isCanLoading) return;
        if (isVisibleToUser) {
            if (isFirst) {
                if (!isFirstVisibleToUser) return;
                if (onFirstVisibleToUser()) onVisibleToUser();
                isFirstVisibleToUser = false;
            } else {
                onVisibleToUser();
            }
        } else onInvisibleToUser();
    }

    protected abstract boolean onFirstVisibleToUser();

    protected abstract void onVisibleToUser();

    protected abstract void onInvisibleToUser();
}
