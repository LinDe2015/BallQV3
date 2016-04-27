package com.tysci.ballq.app;

import android.app.Application;
import android.os.Environment;
import android.support.annotation.LayoutRes;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tysci.ballq.R;
import com.tysci.ballq.networks.HttpClientUtil;
import com.tysci.ballq.utils.FontsUtil;
import com.tysci.ballq.utils.WeChatUtils;

import cn.jpush.android.api.JPushInterface;

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
        // 极光推送
        initJPush();
        // 蒲公英
        PgyCrashManager.register(this);
        initWX();
        /**
         * TODO cache路径未设置
         */
        HttpClientUtil.initHttpClientUtil(this, Environment.getExternalStorageDirectory().getPath());
    }

    /**
     * 初始化极光推送
     */
    private void initJPush() {
        JPushInterface.setDebugMode(true);// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);
//        LogUtils.e("JPush RegistrationID", JPushInterface.getRegistrationID(this));
    }

    /**
     * @return 极光推送ID
     */
    public String getJPushRegId() {
        return JPushInterface.getRegistrationID(this);
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

    public DisplayMetrics getMetrics() {
        final DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        return dm;
    }
}
