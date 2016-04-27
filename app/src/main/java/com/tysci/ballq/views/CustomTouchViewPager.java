package com.tysci.ballq.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.tysci.ballq.app.LazyLoadingFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LinDe on 2016-01-25.
 *
 * @see ViewPager
 */
public class CustomTouchViewPager extends ViewPager {
    /**
     * 允许左右滑动
     */
    private boolean isCanScrollHorizontal;

    private boolean isSetAdapterTrue;
    private List<LazyLoadingFragment> fragments;

    public CustomTouchViewPager(Context context) {
        this(context, null);
    }

    public CustomTouchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        isSetAdapterTrue = false;
        isCanScrollHorizontal = true;
//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onGlobalLayout() {
//                Log.e("TAG", String.valueOf(getWidth()));
//                Log.e("TAG", String.valueOf(getHeight()));
//                getViewTreeObserver().removeOnGlobalLayoutListener(this);
//            }
//        });
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return isCanScrollHorizontal && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return isCanScrollHorizontal && super.onInterceptTouchEvent(event);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    /**
     * @param canScrollHorizontal 允许左右滑动
     */
    public final void setCanScrollHorizontal(boolean canScrollHorizontal) {
        this.isCanScrollHorizontal = canScrollHorizontal;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (!isSetAdapterTrue)
            throw new RuntimeException("use setAdapter(FragmentManager,List<LazyLoadingFragment>)");
        super.setAdapter(adapter);
        isSetAdapterTrue = false;
    }

    public void setAdapter(FragmentManager manager, final ArrayList<LazyLoadingFragment> list) {
        isSetAdapterTrue = true;
        setAdapter(new FragmentStatePagerAdapter(manager) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        fragments = list;
    }

    /**
     * clear
     */
    public final void clear() {
        if (fragments != null) {
            fragments.clear();
            fragments = null;
        }
    }
}
