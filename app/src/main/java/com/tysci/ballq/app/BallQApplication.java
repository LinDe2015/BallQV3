package com.tysci.ballq.app;

import android.app.Application;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tysci.ballq.R;
import com.tysci.ballq.networks.HttpClientUtil;
import com.tysci.ballq.utils.FontsUtil;
import com.tysci.ballq.utils.WeChatUtils;

/**
 * Created by Administrator on 2016/4/25.
 * App start
 */
public class BallQApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActivityStacksManager.initActivityStacksManager();
        initFontTypefaces();
        initWX();
        /**
         * TODO cache路径未设置
         */
        HttpClientUtil.initHttpClientUtil(this, Environment.getExternalStorageDirectory().getPath());
    }

    /**
     * 初始化微信
     */
    private void initWX() {
        WeChatUtils.api = WXAPIFactory.createWXAPI(this, getResources().getString(R.string.app_id_wx), true);
        WeChatUtils.api.registerApp(getResources().getString(R.string.app_id_wx));
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
