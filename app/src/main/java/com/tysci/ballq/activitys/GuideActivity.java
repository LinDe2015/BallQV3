package com.tysci.ballq.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tysci.ballq.R;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.app.LazyLoadingFragment;
import com.tysci.ballq.fragments.GuideFragment;
import com.tysci.ballq.views.CustomTouchViewPager;

import java.util.ArrayList;

/**
 * Created by QianBao on 2016/4/26.
 * guide of BallQ
 */
public class GuideActivity extends BaseActivity implements ViewPager.OnPageChangeListener {
    public static final String SP_GUIDE = GuideActivity.class.getName() + "/SP_GUIDE";

    private CustomTouchViewPager viewPager1;
    private TextView textView1;
    private ImageView imageView1, imageView2, imageView3, imageView4;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_guide);
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.textView1:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
        }
    }

    @Override
    protected void initViews() throws Exception {
        viewPager1 = getView(R.id.viewPager1);

        textView1 = getView(R.id.textView1);

        imageView1 = getView(R.id.imageView1);
        imageView2 = getView(R.id.imageView2);
        imageView3 = getView(R.id.imageView3);
        imageView4 = getView(R.id.imageView4);

        textView1.setVisibility(View.GONE);
        textView1.setOnClickListener(this);

        imageView1.setImageResource(R.drawable.guide_red);

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

        f = new GuideFragment();
        b = new Bundle();
        b.putInt(GuideFragment.class.getName(), GuideFragment.PAGE_4);
        f.setArguments(b);
        fragments.add(f);

        viewPager1.setAdapter(getSupportFragmentManager(), fragments);

        viewPager1.addOnPageChangeListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewPager1.clear();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        textView1.setVisibility(position == 3 ? View.VISIBLE : View.GONE);
        imageView1.setImageResource(R.drawable.guide_gray);
        imageView2.setImageResource(R.drawable.guide_gray);
        imageView3.setImageResource(R.drawable.guide_gray);
        imageView4.setImageResource(R.drawable.guide_gray);
        switch (position) {
            case 0:
                imageView1.setImageResource(R.drawable.guide_red);
                break;
            case 1:
                imageView2.setImageResource(R.drawable.guide_red);
                break;
            case 2:
                imageView3.setImageResource(R.drawable.guide_red);
                break;
            case 3:
                imageView4.setImageResource(R.drawable.guide_red);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
