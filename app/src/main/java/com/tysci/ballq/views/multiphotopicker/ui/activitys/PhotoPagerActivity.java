package com.tysci.ballq.views.multiphotopicker.ui.activitys;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import java.util.ArrayList;
import java.util.List;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.views.multiphotopicker.ui.adapters.PhotoPagerItemAdapter;
import com.tysci.ballq.utils.DimensionUtil;
import com.tysci.ballq.views.multiphotopicker.entity.Photo;
import com.tysci.ballq.R;

/**
 * Created by HTT on 2016/4/16.
 */
public class PhotoPagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener{
    private ViewPager viewPager;
    private TextView tvTitleBarRight;
    private ImageView ivPhotoSelectedMark;

    private int photoMaxSelectCounts=PhotoPickerActivity.DEFAULT_MAX_SELECTED_PHOTOS;

    private int currentPosition=0;
    private List<com.tysci.ballq.views.multiphotopicker.entity.Photo> photos=null;
    private List<String>selectedPhotos=null;
    private PhotoPagerItemAdapter photoPagerItemAdapter=null;

    @Override
    protected void setContentView() {
        setContentView(com.tysci.ballq.R.layout.activity_photo_pager);
    }

    @Override
    protected void initViews() {
        viewPager=(ViewPager)this.findViewById(com.tysci.ballq.R.id.photo_view_pager);
        viewPager.addOnPageChangeListener(this);
        ivPhotoSelectedMark=(ImageView)this.findViewById(R.id.iv_photo_select_mark);
        ivPhotoSelectedMark.setOnClickListener(this);
        getDatas();
        addTitleBarRightItem();
        titleBar.setTitle(currentPosition+"/"+photos.size());

        photoPagerItemAdapter=new PhotoPagerItemAdapter(this,photos);
        viewPager.setAdapter(photoPagerItemAdapter);
        viewPager.setCurrentItem(currentPosition);
    }

    @Override
    public void onViewClick(View view) {

    }

    private void addTitleBarRightItem(){
        tvTitleBarRight=new TextView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int paddingTop= com.tysci.ballq.utils.DimensionUtil.dip2px(this,6);

        int paddingLeft=DimensionUtil.dip2px(this,10);
        tvTitleBarRight.setPadding(paddingLeft,paddingTop,paddingLeft,paddingTop);
        tvTitleBarRight.setTextColor(Color.parseColor("#ffffff"));
        tvTitleBarRight.setTextSize(12);
        tvTitleBarRight.setBackgroundResource(R.drawable.button_title_bar_selector);
        tvTitleBarRight.setText("完成("+selectedPhotos.size()+"/"+photoMaxSelectCounts+")");
        //titleBar.addTitleBarRightItem(tvTitleBarRight,layoutParams);
    }

    private void getDatas(){
        Intent intent=this.getIntent();
        currentPosition=intent.getIntExtra("position",0);
        photos=intent.getParcelableArrayListExtra("photos");
        selectedPhotos=intent.getStringArrayListExtra("selected_photos");
        photoMaxSelectCounts=intent.getIntExtra("max_select_photos",PhotoPickerActivity.DEFAULT_MAX_SELECTED_PHOTOS);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_photo_select_mark:
                onSelectPhoto();
                break;
        }
    }

    @Override
    protected void back() {
        Intent intent=new Intent();
        intent.putStringArrayListExtra("selected_photos", (ArrayList<String>) selectedPhotos);
        setResult(RESULT_FIRST_USER,intent);
        this.finish();
    }

    @Override
    public void onBackPressed() {
        back();
    }

    private void onSelectPhoto(){
        com.tysci.ballq.views.multiphotopicker.entity.Photo photo=photos.get(currentPosition);
        boolean isSelected=isSelectedPhoto(photo);
        if(isSelected){
            selectedPhotos.remove(photo.getPath());
            ivPhotoSelectedMark.setImageResource(R.mipmap.icon_photo_unselected);
        }else{
            if(selectedPhotos.size()<photoMaxSelectCounts) {
                selectedPhotos.add(photo.getPath());
                ivPhotoSelectedMark.setImageResource(R.mipmap.icon_photo_selected);
            }else{
                //ToastMessageUtil.toastMessage(this,"您最多可选择"+photoMaxSelectCounts+"张图片");
            }
        }
        tvTitleBarRight.setText("完成("+selectedPhotos.size()+"/"+photoMaxSelectCounts+")");
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        currentPosition=position;
        titleBar.setTitle((currentPosition+1)+"/"+photos.size());
        Photo photo=photos.get(position);
        if(isSelectedPhoto(photo)){
            ivPhotoSelectedMark.setImageResource(R.mipmap.icon_photo_selected);
        }else{
            ivPhotoSelectedMark.setImageResource(R.mipmap.icon_photo_unselected);
        }
    }

    /**
     * 判断当前图片是否已经选择
     * @param photo
     * @return
     */
    private boolean isSelectedPhoto(Photo photo){
        int size=selectedPhotos.size();
        for(int i=0;i<size;i++){
            if(selectedPhotos.get(i).equals(photo.getPath())){
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }
}
