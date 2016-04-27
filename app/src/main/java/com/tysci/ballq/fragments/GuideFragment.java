package com.tysci.ballq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tysci.ballq.R;
import com.tysci.ballq.app.LazyLoadingFragment;

/**
 * Created by QianBao on 2016/4/27.
 * guide fragment
 */
public class GuideFragment extends LazyLoadingFragment {
    public static final int PAGE_1 = 0x1;
    public static final int PAGE_2 = 0x2;
    public static final int PAGE_3 = 0x3;
    public static final int PAGE_4 = 0x4;

    private ImageView imageView1;
    private int page;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle b = getArguments();
        page = b.getInt(GuideFragment.class.getName());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        imageView1 = new ImageView(getContext());
        imageView1.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView1.setScaleType(ImageView.ScaleType.FIT_XY);
        return imageView1;
    }

    @Override
    protected void initViews(View view) {
        switch (page) {
            case PAGE_1:
                Glide.with(this).load(R.mipmap.guide_1).into(imageView1);
                break;
            case PAGE_2:
                Glide.with(this).load(R.mipmap.guide_2).into(imageView1);
                break;
            case PAGE_3:
                Glide.with(this).load(R.mipmap.guide_3).into(imageView1);
                break;
            case PAGE_4:
                Glide.with(this).load(R.mipmap.guide_4).into(imageView1);
                break;
        }
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    protected boolean onFirstVisibleToUser() {
        return true;
    }

    @Override
    protected void onVisibleToUser() {
    }

    @Override
    protected void onInvisibleToUser() {
    }
}
