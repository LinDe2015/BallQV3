package com.tysci.ballq.activitys;

import android.view.View;

import com.tysci.ballq.R;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.views.widgets.slidingmenu.SlidingMenu;

public class MainActivity extends BaseActivity {
    private SlidingMenu slidingMenu=null;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() throws Exception {
        titleBar.setBackIcon(R.mipmap.icon_main_left_menu);
        titleBar.setNextIcon(R.mipmap.icon_main_right_user_menu);
        initSlidingMenu();
    }

    private void initSlidingMenu(){
        slidingMenu=new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCH_MODE_FULLSCREEN);
        slidingMenu.setShadowWidth(200);
//        menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffset(200);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.5f);
        slidingMenu.setMenu(R.layout.layout_main_left_menu);
        slidingMenu.setSecondaryMenu(R.layout.layout_main_right_menu);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onViewClick(View view) {

    }

}
