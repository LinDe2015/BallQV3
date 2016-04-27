package com.tysci.ballq.views.widgets;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

/**
 * Created by LinDe on 2016-03-25 0025.
 *
 * @see SwipeRefreshLayout
 */
public class CustomSwipeRefreshLayout extends SwipeRefreshLayout implements SwipeRefreshLayout.OnRefreshListener
{
    private OnRefreshListener onRefreshListener;

    public CustomSwipeRefreshLayout(Context context)
    {
        this(context, null);
    }

    public CustomSwipeRefreshLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        //noinspection deprecation
        setOnRefreshListener(this);
    }

    @Override
    @Deprecated
    public void setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener listener)
    {
        super.setOnRefreshListener(this);
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener)
    {
        this.onRefreshListener = onRefreshListener;
    }

    @Deprecated
    @Override
    public void onRefresh()
    {
        setEnabled(false);
        if (onRefreshListener != null)
        {
            onRefreshListener.onRefresh();
        }
    }

    @Deprecated
    @Override
    public void setRefreshing(boolean refreshing)
    {
        if(refreshing)
        {
            //noinspection deprecation
            onRefresh();
        }
        setEnabled(!refreshing);
        super.setRefreshing(refreshing);
    }

    public void setRefreshing()
    {
        this.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //noinspection deprecation
                setRefreshing(true);
            }
        }, 250);
    }

    public void onRefreshComplete()
    {
        this.postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //noinspection deprecation
                setRefreshing(false);
            }
        }, 250);
    }

    public interface OnRefreshListener
    {
        void onRefresh();
    }
}
