package com.tysci.ballq.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2016/4/25.
 */
abstract public class BaseActivity extends AppCompatActivity{
    protected final String Tag=this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStacksManager.getActivityStacksManager().addActivity(this);
        if(isBindEventBus()){
            EventBus.getDefault().register(this);
        }
        setContentView();
        initViews();
    }

    abstract protected void setContentView();
   abstract protected boolean isBindEventBus();
    abstract protected void initViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(isBindEventBus()){
            EventBus.getDefault().unregister(this);
        }
        ActivityStacksManager.getActivityStacksManager().finishActivity(this);
    }
}
