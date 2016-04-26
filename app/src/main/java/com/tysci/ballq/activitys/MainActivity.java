package com.tysci.ballq.activitys;

import android.view.View;

import com.tysci.ballq.R;
import com.tysci.ballq.app.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onViewClick(View view) {

    }

    @Override
    protected void initViews() throws Exception {

    }
}
