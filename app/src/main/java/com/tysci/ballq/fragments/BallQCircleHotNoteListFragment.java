package com.tysci.ballq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;
import com.tysci.ballq.R;
import com.tysci.ballq.app.BaseFragment;
import com.tysci.ballq.networks.HttpClientUtil;
import com.tysci.ballq.networks.HttpUrls;
import com.tysci.ballq.views.widgets.CustomSwipeRefreshLayout;
import com.tysci.ballq.views.widgets.loadmorerecyclerview.AutoLoadMoreRecyclerView;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2016/4/25.
 * 热门圈子列表
 */
public class BallQCircleHotNoteListFragment extends BaseFragment implements CustomSwipeRefreshLayout.OnRefreshListener{
    private CustomSwipeRefreshLayout swipeRefreshLayout;
    private AutoLoadMoreRecyclerView recyclerView;
    private int currentPages=1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recyclerview_list,container,false);
    }

    @Override
    protected void initViews(View view) {
        swipeRefreshLayout=(CustomSwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView=(AutoLoadMoreRecyclerView)view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        swipeRefreshLayout.setRefreshing();
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    /**
     * 加载数据
     * @param pages
     * @param isLoadMore
     */
    private void requestDatas(int pages,final boolean isLoadMore){
        String url= HttpUrls.HOT_CIRCLE_LIST_URL+"?pageNo="+pages+"&pageSize=10";
        KLog.e("Url:" + url);
        HttpClientUtil.getHttpClientUtil().sendGetRequest(TAG, url, 10, new HttpClientUtil.StringResponseCallBack() {
            @Override
            public void onBefore(Request request) {

            }

            @Override
            public void onError(Call call, Exception error) {
                KLog.e("加载失败...");
            }

            @Override
            public void onSuccess(Call call, String response) {
                KLog.e("加载的数据:" + response);
                KLog.json(response);
                if(!TextUtils.isEmpty(response)){
                    JSONObject obj= JSON.parseObject(response);

                }
            }

            @Override
            public void onFinish(Call call) {
                if(!isLoadMore){
                    swipeRefreshLayout.onRefreshComplete();
                }

            }
        });

    }

    @Override
    public void onRefresh() {
        currentPages=1;
        requestDatas(currentPages,false);

    }
}
