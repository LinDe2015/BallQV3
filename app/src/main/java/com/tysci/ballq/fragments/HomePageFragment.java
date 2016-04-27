package com.tysci.ballq.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tysci.ballq.R;
import com.tysci.ballq.app.BallQApplication;
import com.tysci.ballq.app.BaseFragment;
import com.tysci.ballq.app.LazyLoadingFragment;
import com.tysci.ballq.utils.ResUtils;
import com.tysci.ballq.views.CustomTouchViewPager;

import java.util.ArrayList;

/**
 * Created by QianBao on 2016/4/27.
 * 首页
 */
public class HomePageFragment extends BaseFragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private CustomTouchViewPager viewPager1;
    private LinearLayout llLine;

    private TextView tvTips, tvArticles, tvCircles;

    private int screenWidth_3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_page, container, false);
    }

    @Override
    protected void initViews(View view) {
        viewPager1 = getView(R.id.viewPager1);
        llLine = getView(R.id.llLine);
        tvTips = getView(R.id.tvTips);
        tvArticles = getView(R.id.tvArticles);
        tvCircles = getView(R.id.tvCircles);

        tvTips.setOnClickListener(this);
        tvArticles.setOnClickListener(this);
        tvCircles.setOnClickListener(this);

        screenWidth_3 = ((BallQApplication) getActivity().getApplicationContext()).getMetrics().widthPixels / 3;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) llLine.getLayoutParams();
        lp.width = screenWidth_3;
        llLine.setLayoutParams(lp);

        viewPager1.addOnPageChangeListener(this);
        /**
         * init viewPager setAdapter addListener
         */
        final ArrayList<LazyLoadingFragment> fragments = new ArrayList<>();
        LazyLoadingFragment f;
        Bundle b;

        f = new GuideFragment();
        b = new Bundle();
        b.putInt(GuideFragment.class.getName(), GuideFragment.PAGE_1);
        f.setArguments(b);
        fragments.add(f);

        f = new GuideFragment();
        b = new Bundle();
        b.putInt(GuideFragment.class.getName(), GuideFragment.PAGE_2);
        f.setArguments(b);
        fragments.add(f);

        f = new GuideFragment();
        b = new Bundle();
        b.putInt(GuideFragment.class.getName(), GuideFragment.PAGE_3);
        f.setArguments(b);
        fragments.add(f);

        viewPager1.setAdapter(getActivity().getSupportFragmentManager(), fragments);
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        final RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) llLine.getLayoutParams();
        lp.leftMargin = (int) (screenWidth_3 * position + screenWidth_3 * positionOffset);
        llLine.setLayoutParams(lp);
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvTips:
            case R.id.tvArticles:
            case R.id.tvCircles:
                toRefreshTabSelected(v.getId());
                break;
        }
    }

    private void toRefreshTabSelected(int viewId) {
        final Resources res = getResources();
        switch (viewId) {
            case R.id.tvTips:
                viewPager1.setCurrentItem(0, false);
                tvTips.setTextColor(ResUtils.getColor(res, R.color.gold));
                tvArticles.setTextColor(ResUtils.getColor(res, R.color.black));
                tvCircles.setTextColor(ResUtils.getColor(res, R.color.black));
                break;
            case R.id.tvArticles:
                viewPager1.setCurrentItem(1, false);
                tvTips.setTextColor(ResUtils.getColor(res, R.color.black));
                tvArticles.setTextColor(ResUtils.getColor(res, R.color.gold));
                tvCircles.setTextColor(ResUtils.getColor(res, R.color.black));
                break;
            case R.id.tvCircles:
                viewPager1.setCurrentItem(2, false);
                tvTips.setTextColor(ResUtils.getColor(res, R.color.black));
                tvArticles.setTextColor(ResUtils.getColor(res, R.color.black));
                tvCircles.setTextColor(ResUtils.getColor(res, R.color.gold));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewPager1.clear();
    }
}
