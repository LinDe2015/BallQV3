package com.tysci.ballq.activitys;

import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tysci.ballq.R;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.utils.FileUtils;
import com.tysci.ballq.utils.SPUtils;

/**
 * Created by QianBao on 2016/4/26.
 * welcome to use BallQ application
 */
public class WelcomeActivity extends BaseActivity {
    private ImageView imageView1;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_welcome);
    }

    @Override
    protected void initViews() throws Exception {
        imageView1 = getView(R.id.imageView1);
        Glide.with(this).load(R.mipmap.ic_launcher).into(imageView1);
        if (!SPUtils.read(this, GuideActivity.SP_GUIDE, false)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, GuideActivity.class));
                    finish();
                }
            }, 3000);
            return;
        }
        final String s = FileUtils.readCache(this, "com.tysci.ballq.Splash");
        if (TextUtils.isEmpty(s)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                    finish();
                }
            }, 3000);
        }
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onViewClick(View view) {
    }
}
