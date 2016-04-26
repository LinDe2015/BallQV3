package com.tysci.ballq.views.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tysci.ballq.R;
import com.tysci.ballq.views.widgets.roundview.RoundTextView;

/**
 * Created by Administrator on 2016/4/26.
 */
public class MainMenuItem extends LinearLayout{
    private View selectedMark;
    private ImageView ivMenuIcon;
    private TextView tvMenuName;
    private RoundTextView tvMenuMsg;
    private LinearLayout layoutMenuItem;

    private int menuIconRes;
    private String menuName;
    private boolean isSelected;

    public MainMenuItem(Context context) {
        super(context);
        initViews(context,null);
    }

    public MainMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public MainMenuItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context,attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MainMenuItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(context,attrs);
    }

    private void initViews(Context context,AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.layout_main_menu_item,this,true);
        layoutMenuItem= (LinearLayout) this.findViewById(R.id.layout_menu_content);
        selectedMark=this.findViewById(R.id.iv_menu_selected);
        ivMenuIcon= (ImageView) this.findViewById(R.id.iv_menu_icon);
        tvMenuName=(TextView)this.findViewById(R.id.tv_menu_name);
        tvMenuMsg=(RoundTextView)this.findViewById(R.id.tv_menu_messages);

        if(attrs!=null){
            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MainMenuItem);
            menuIconRes=ta.getResourceId(R.styleable.MainMenuItem_menu_icon, -1);
            menuName=ta.getString(R.styleable.MainMenuItem_menu_name);
            isSelected=ta.getBoolean(R.styleable.MainMenuItem_is_selected,false);
            ta.recycle();

            if(menuIconRes>=0){
                ivMenuIcon.setImageResource(menuIconRes);
            }

            if(!TextUtils.isEmpty(menuName)){
                tvMenuName.setText(menuName);
            }
        }
        setSelectedState(isSelected);
    }

    public void setMenuIcon(int res){
        this.menuIconRes=res;
        ivMenuIcon.setImageResource(menuIconRes);
    }

    public void setMenuName(String menuName){
        this.menuName=menuName;
        this.tvMenuName.setText(menuName);
    }

    public void setSelectedState(boolean isSelected){
        if(isSelected){
            layoutMenuItem.setBackgroundResource(R.drawable.main_menu_item_selected_bg);
            selectedMark.setVisibility(VISIBLE);
        }else{
            layoutMenuItem.setBackgroundColor(android.R.color.transparent);
            selectedMark.setVisibility(INVISIBLE);
        }
    }
}
