package com.tysci.ballq.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.pgyersdk.crash.PgyCrashManager;
import com.tysci.ballq.R;
import com.tysci.ballq.networks.HttpClientUtil;
import com.tysci.ballq.views.widgets.TitleBar;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by HTT on 2016/4/25.
 * All activity base this
 */
abstract public class BaseActivity extends AppCompatActivity implements IEvent,View.OnClickListener {
    protected final String TAG = this.getClass().getSimpleName();
    protected TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PgyCrashManager.register(this);
        ActivityStacksManager.getActivityStacksManager().addActivity(this);
        if (isNeedBindEventBus()) {
            EventBus.getDefault().register(this);
        }
        setContentView();
        titleBar=(TitleBar)this.findViewById(R.id.title_bar);
        if(titleBar!=null){
            titleBar.setOnClickBackListener(this);
            titleBar.setOnClickNextListener(this);
        }
        try {
            initViews();
        } catch (Exception e) {
            e.printStackTrace();
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    abstract protected void setContentView();

    @Override
    abstract public boolean isNeedBindEventBus();

    abstract public void onViewClick(View view);

    @Override
    public void onEventMainThread(EventNotify notify) {
        for (Class<?> aClass : notify.notifyClasses) {
            if (aClass == this.getClass()) {
                onNotifyJson(notify.json);
            }
        }
    }

    @Override
    public void onNotifyJson(String json) {
    }

    abstract protected void initViews() throws Exception;

    protected void setTitle(String title){
        if(titleBar!=null){
            titleBar.setTitle(title);
        }
    }

    protected void back(){
        this.finish();
    }

    protected void next(){

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.layout_titlebar_back:
                back();
                break;
            case R.id.layout_titlebar_next:
                next();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        HttpClientUtil.getHttpClientUtil().cancelTag(TAG);
        if (isNeedBindEventBus()) {
            EventBus.getDefault().unregister(this);
        }
        ActivityStacksManager.getActivityStacksManager().finishActivity(this);
    }
}
