package com.tysci.ballq.activitys;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.tysci.ballq.R;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.fragments.BallQInfoListFragment;
import com.tysci.ballq.views.widgets.MainMenuItem;
import com.tysci.ballq.views.widgets.slidingmenu.SlidingMenu;

public class MainActivity extends BaseActivity {
    private SlidingMenu slidingMenu=null;
    private View mainLeftMenu;
    private View mainRightMenu;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initViews() throws Exception {
        titleBar.setBackIcon(R.mipmap.icon_main_left_menu);
        titleBar.setNextIcon(R.mipmap.icon_main_right_user_menu);
        initSlidingMenu();
        addMenusItemOnClickListener();

        getSupportFragmentManager().beginTransaction().replace(R.id.layout_container,new BallQInfoListFragment()).commitAllowingStateLoss();
    }

    private void initSlidingMenu() {
        slidingMenu = new SlidingMenu(this);
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);
        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCH_MODE_FULLSCREEN);
        slidingMenu.setShadowWidth(200);
//        menu.setShadowDrawable(R.drawable.shadow);

        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffset(200);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.5f);
        mainLeftMenu= LayoutInflater.from(this).inflate(R.layout.layout_main_left_menu,null);
        mainRightMenu=LayoutInflater.from(this).inflate(R.layout.layout_main_right_menu,null);
        slidingMenu.setMenu(mainLeftMenu);
        slidingMenu.setSecondaryMenu(mainRightMenu);
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    }

    /**
     * 为左右菜单项添加点击事件
     */
    private void addMenusItemOnClickListener(){
        LinearLayout layoutLeftMenus= (LinearLayout) mainLeftMenu.findViewById(R.id.layout_left_menus);
        int size=layoutLeftMenus.getChildCount();
        for(int i=0;i<size;i++){
            View view=layoutLeftMenus.getChildAt(i);
            if(view instanceof MainMenuItem){
                view.setOnClickListener(this);
            }
        }

        LinearLayout layoutRightMenus=(LinearLayout)mainRightMenu.findViewById(R.id.layout_right_menus);
        size=layoutRightMenus.getChildCount();
        for(int i=0;i<size;i++){
            View view=layoutRightMenus.getChildAt(i);
            if(view instanceof MainMenuItem){
                view.setOnClickListener(this);
            }
        }
    }

    private void onMenuItemClick(View view){
        LinearLayout layoutLeftMenus= (LinearLayout) mainLeftMenu.findViewById(R.id.layout_left_menus);
        int size=layoutLeftMenus.getChildCount();
        for(int i=0;i<size;i++){
            View v=layoutLeftMenus.getChildAt(i);
            if(v instanceof MainMenuItem){
                    ((MainMenuItem)v).setSelectedState(v==view);
            }
        }

        LinearLayout layoutRightMenus=(LinearLayout)mainRightMenu.findViewById(R.id.layout_right_menus);
        size=layoutRightMenus.getChildCount();
        for(int i=0;i<size;i++){
            View v=layoutRightMenus.getChildAt(i);
            if(v instanceof MainMenuItem){
                ((MainMenuItem)v).setSelectedState(v == view);
            }
        }
    }

    @Override
    protected void back() {
        slidingMenu.toggle();
    }

    @Override
    protected void next() {
        slidingMenu.showSecondaryMenu();
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onViewClick(View view) {
        onMenuItemClick(view);

    }
}
