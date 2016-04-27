package com.tysci.ballq.views.widgets;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tysci.ballq.R;
import com.tysci.ballq.views.widgets.roundview.RoundTextView;

/**
 * Created by Administrator on 2016/4/25.
 */
public class TitleBar extends LinearLayout {
    private ImageView ivBack;
    private ViewGroup layoutBack;
    private TextView tvTitle;
    private ViewGroup layoutNext;
    private ImageView ivNext;
    private TextView tvNext;
    private RoundTextView tvMessageTip;

    public TitleBar(Context context) {
        super(context);
        initViews(null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs){
        LayoutInflater.from(this.getContext()).inflate(R.layout.layout_title_bar_content, this, true);
        this.setBackgroundResource(R.color.colorPrimary);
        ivBack=(ImageView)this.findViewById(R.id.iv_titlebar_left);
        layoutBack=(ViewGroup)this.findViewById(R.id.layout_titlebar_back);
        tvTitle=(TextView)this.findViewById(R.id.tv_titlebar_title);
        layoutNext=(RelativeLayout)this.findViewById(R.id.layout_titlebar_next);
        ivNext=(ImageView)this.findViewById(R.id.iv_titlebar_next);
        tvNext=(TextView)this.findViewById(R.id.tv_titlebar_next);
        tvMessageTip=(RoundTextView)this.findViewById(R.id.tv_titlebar_left_msg_tip);

        if(attrs!=null){
            TypedArray ta = this.getContext().obtainStyledAttributes(attrs, R.styleable.TitleBar);
            Drawable iconBack=ta.getDrawable(R.styleable.TitleBar_title_bar_back_icon);
            if(iconBack!=null){
                ivBack.setImageDrawable(iconBack);
            }
            String title=ta.getString(R.styleable.TitleBar_title_bar_title);
            if(!TextUtils.isEmpty(title)){
                tvTitle.setText(title);
            }

            Drawable iconNext=ta.getDrawable(R.styleable.TitleBar_title_bar_next_icon);
            if(iconNext!=null){
                ivNext.setImageDrawable(iconNext);
            }
            ta.recycle();
        }
    }

    public void setPaddingTop(int paddingTop){
        this.setPadding(this.getPaddingLeft(),paddingTop,this.getPaddingRight(),this.getPaddingBottom());
    }

    public void setTitle(String title){
        this.tvTitle.setText(title);
    }

    public void setTitle(int res){
        this.tvTitle.setText(res);
    }

    public void setBackIcon(int res){
        this.ivBack.setImageResource(res);
    }

    public void setBackIcon(Drawable drawable){
        this.ivBack.setImageDrawable(drawable);
    }

    public void setNextIcon(int res){
        this.ivNext.setImageResource(res);
    }

    public void setNextIcon(Drawable drawable){
        this.ivNext.setImageDrawable(drawable);
    }

    public void setOnClickBackListener(OnClickListener listener){
        layoutBack.setOnClickListener(listener);
        // ivBack.setOnClickListener(listener);
    }

    public void setOnClickNextListener(OnClickListener listener){
        layoutNext.setOnClickListener(listener);
        //ivNext.setOnClickListener(listener);
    }

    public void setOnClickTitleListener(OnClickListener listener){
        tvTitle.setOnClickListener(listener);
    }

    public void setTitleRightIcon(Drawable drawable){
        tvTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    public void setTitleBarNextVisibility(int visibility){
        layoutNext.setVisibility(visibility);

    }

    public void setTitleBarNextText(String text){
        tvNext.setVisibility(View.VISIBLE);
        tvNext.setText(text);
    }

    public void setTitleBarNextText(int res){
        tvNext.setVisibility(View.VISIBLE);
        tvNext.setText(res);
    }

//    public void setTitleBarRefresh(){
//        refreshAnimation= AnimationUtils.loadAnimation(this.getContext(), R.anim.anim_titlebar_refresh);
//        LinearInterpolator lin = new LinearInterpolator();
//        refreshAnimation.setInterpolator(lin);
//    }
//
//    public void startTitleBarRefresh(){
//        if(refreshAnimation!=null) {
//            ivNext.startAnimation(refreshAnimation);
//        }else{
//            setTitleBarRefresh();
//            ivNext.startAnimation(refreshAnimation);
//
//        }
//    }

    public void stopTitleBarRefresh(){
        ivNext.clearAnimation();
    }

    public TextView getTitleView(){
        return tvTitle;
    }

//    public void showMessageTip(boolean isVisibility){
//        if(isVisibility){
//            tvMessageTip.setVisibility(View.VISIBLE);
//            UnreadMsgUtils.show(tvMessageTip,0);
//        }else{
//            tvMessageTip.setVisibility(View.GONE);
//        }
//
//    }
}

