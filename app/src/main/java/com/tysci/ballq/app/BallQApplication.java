package com.tysci.ballq.app;

import android.app.Application;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.pgyersdk.crash.PgyCrashManager;
import com.tysci.ballq.utils.FontsUtil;

/**
 * Created by Administrator on 2016/4/25.
 */
public class BallQApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFontTypefaces();
        PgyCrashManager.register(this);
    }

    /**
     * 更改系统默认字体
     */
    private void initFontTypefaces() {
        FontsUtil.setDefaultFont(this, "MONOSPACE", "cczyt.TTF");
        FontsUtil.setDefaultFont(this, "SERIF", "FZLTHJW.TTF");
        FontsUtil.setDefaultFont(this, "SANS_SERIF", "fzltkh_gbk.TTF");
    }

    public View createView(@LayoutRes int layoutResID) {
        return LayoutInflater.from(this).inflate(layoutResID, null);
    }
}