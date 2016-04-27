package com.tysci.ballq.views.multiphotopicker.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.views.multiphotopicker.ui.adapters.PhotoItemAdapter;
import com.tysci.ballq.views.multiphotopicker.ui.adapters.PhotoDirectoryItemAdapter;
import com.tysci.ballq.utils.DimensionUtil;
import com.tysci.ballq.views.multiphotopicker.entity.Photo;
import com.tysci.ballq.views.multiphotopicker.entity.PhotoDirectory;
import com.tysci.ballq.views.multiphotopicker.utils.ImageMediaStoreUtil;
import com.tysci.ballq.R;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HTT on 2016/4/12.
 */
public class PhotoPickerActivity extends BaseActivity implements PhotoDirectoryItemAdapter.OnItemClickListener
,PhotoItemAdapter.OnSelectPhotoListener{
    /**默认可选的最多的图片数量*/
    public static final int DEFAULT_MAX_SELECTED_PHOTOS=9;
    private RecyclerView photoRecyclerView;
    private TextView tvPhotoDirectoryName;
    private TextView tvDirectoryPhotoCounts;
    private List<PhotoDirectory> directoryList;
    private PhotoDirectoryItemAdapter photoDirectoryItemAdapter;
    private PhotoItemAdapter photoItemAdapter=null;

    private int photoMaxSelectCounts=DEFAULT_MAX_SELECTED_PHOTOS;

    private ListPopupWindow directoryListPopupWindow=null;
    private TextView tvTitleBarRight;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_photo_picker);
    }

    @Override
    protected void initViews() {
        titleBar.setTitle("相册");
        addTitleBarRightItem();
        photoRecyclerView= (RecyclerView) this.findViewById(R.id.rv_photos);
        photoRecyclerView.setLayoutManager(new GridLayoutManager(this,3));
        tvPhotoDirectoryName=(TextView)this.findViewById(R.id.tv_photo_directory);
        tvPhotoDirectoryName.setOnClickListener(this);
        tvDirectoryPhotoCounts=(TextView)this.findViewById(R.id.tv_directory_photo_counts);
        initPhotoDirectoryListPopupWindow();
        getPhotoDirectory();
    }

    private void addTitleBarRightItem(){
        tvTitleBarRight=new TextView(this);
        ViewGroup.LayoutParams layoutParams=new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int paddingTop= DimensionUtil.dip2px(this,6);
        int paddingLeft=DimensionUtil.dip2px(this,10);
        tvTitleBarRight.setPadding(paddingLeft,paddingTop,paddingLeft,paddingTop);
        tvTitleBarRight.setTextColor(Color.parseColor("#ffffff"));
        tvTitleBarRight.setTextSize(12);
        tvTitleBarRight.setBackgroundResource(R.drawable.button_title_bar_selector);
        tvTitleBarRight.setText("完成(0/"+photoMaxSelectCounts+")");
        //titleBar.addTitleBarRightItem(tvTitleBarRight,layoutParams);
    }

    private void initPhotoDirectoryListPopupWindow(){
        directoryList=new ArrayList<>();
        photoDirectoryItemAdapter=new PhotoDirectoryItemAdapter(this,directoryList);
        photoDirectoryItemAdapter.setOnItemClickListener(this);
        directoryListPopupWindow=new ListPopupWindow(this);
        directoryListPopupWindow.setWidth(ListPopupWindow.MATCH_PARENT);
        setPhotoDirectoryListPopupWindowHeight(directoryListPopupWindow);
        directoryListPopupWindow.setAnchorView(this.findViewById(R.id.layout_photo_directorys));
        directoryListPopupWindow.setAdapter(photoDirectoryItemAdapter);
        directoryListPopupWindow.setModal(true);
        directoryListPopupWindow.setDropDownGravity(Gravity.BOTTOM);
        //directoryListPopupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
    }

    private void setPhotoDirectoryListPopupWindowHeight(ListPopupWindow popupWindow){
        WindowManager wm = (WindowManager)this.getSystemService(Context.WINDOW_SERVICE);
        int height= (int) (wm.getDefaultDisplay().getHeight()*0.75);
        popupWindow.setHeight(height);
    }

    private void getPhotoDirectory(){
        ImageMediaStoreUtil.getPhotoDirs(this, null, new ImageMediaStoreUtil.PhotosResultCallback() {
            @Override
            public void onResultCallback(List<PhotoDirectory> directories) {
                if (directories != null && directories.size() > 0) {
                    if (directoryList == null) {
                        directoryList = new ArrayList<PhotoDirectory>();
                        directoryList.addAll(directories);
                    } else {
                        if (directoryList.size() > 0) {
                            directoryList.clear();
                        }
                        directoryList.addAll(directories);
                    }
                    KLog.e("图片目录数量:" + directoryList.size());
                    photoDirectoryItemAdapter.notifyDataSetChanged();
                    if (photoItemAdapter == null) {
                        photoItemAdapter = new PhotoItemAdapter(directoryList.get(0),photoMaxSelectCounts);
                        photoItemAdapter.setOnSelectPhotoListener(PhotoPickerActivity.this);
                        photoRecyclerView.setAdapter(photoItemAdapter);
                    }else{
                        photoItemAdapter.setPhotoDirectory(directoryList.get(0));
                    }
                    tvPhotoDirectoryName.setText(directoryList.get(0).getDirectoryName());
                    tvDirectoryPhotoCounts.setText(directoryList.get(0).getPhotos().size()+"张");
                }
            }
        });
    }

    private void showPhotoDirectoryPopupWindow(){
        if(directoryListPopupWindow.isShowing()){
            directoryListPopupWindow.dismiss();
        }else{
            directoryListPopupWindow.show();
        }
    }

    @Override
    public void onViewClick(View v) {
        int id=v.getId();
        switch (id){
            case R.id.tv_photo_directory:
                showPhotoDirectoryPopupWindow();
                break;
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        if(directoryList!=null&&directoryList.size()>0) {
            PhotoDirectory photoDirectory = directoryList.get(position);
            photoItemAdapter.setPhotoDirectory(photoDirectory);
            tvDirectoryPhotoCounts.setText(photoDirectory.getPhotos().size()+"张");
            tvPhotoDirectoryName.setText(photoDirectory.getDirectoryName());
            directoryListPopupWindow.dismiss();
        }
    }

    @Override
    public void onSelectedPhoto(int selelctedPhotos, String photoPath, boolean isSelected) {
        tvTitleBarRight.setText("完成("+selelctedPhotos+"/"+photoMaxSelectCounts+")");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==0x0001){
            List<String> selectedPhotos=data.getStringArrayListExtra("selected_photos");
            if(selectedPhotos!=null){
                photoItemAdapter.setSelectedPhotos(selectedPhotos);
            }
            if(resultCode==RESULT_OK){

            }
        }
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }
}
