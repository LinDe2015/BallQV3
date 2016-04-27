package com.tysci.ballq.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.socks.library.KLog;
import com.tysci.ballq.R;
import com.tysci.ballq.adapters.TipListAdapter;
import com.tysci.ballq.app.LazyLoadingFragment;
import com.tysci.ballq.interfaces.IEtype;
import com.tysci.ballq.interfaces.ISort;
import com.tysci.ballq.networks.HttpClientUtil;
import com.tysci.ballq.networks.HttpUrls;
import com.tysci.ballq.views.widgets.CustomSwipeRefreshLayout;
import com.tysci.ballq.views.widgets.loadmorerecyclerview.AutoLoadMoreRecyclerView;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by QianBao on 2016/4/27.
 * 首页爆料列表
 */
public class TipListFragment extends LazyLoadingFragment implements CustomSwipeRefreshLayout.OnRefreshListener, HttpClientUtil.StringResponseCallBack {

    private CustomSwipeRefreshLayout swipeRefreshLayout1;

    private AutoLoadMoreRecyclerView recyclerView1;
    private TipListAdapter adapter1;

    private int currentPage;

    @Override
    protected boolean onFirstVisibleToUser() {
        return false;
    }

    @Override
    protected void onVisibleToUser() {
    }

    @Override
    protected void onInvisibleToUser() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.layout_recyclerview_list, container, false);
    }

    @Override
    protected void initViews(View view) {
        swipeRefreshLayout1 = getView(R.id.swipe_refresh);
        recyclerView1 = getView(R.id.recyclerview);

        swipeRefreshLayout1.setOnRefreshListener(this);

        final LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView1.setLayoutManager(llm);

        adapter1=new TipListAdapter(getContext());
        recyclerView1.setAdapter(adapter1);

        swipeRefreshLayout1.setRefreshing();
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onRefresh() {
        toRefreshDataList(false);
    }

    private void toRefreshDataList(boolean loadMore) {
        currentPage = loadMore ? currentPage + 1 : 1;
        String sb = HttpUrls.HOST_URL_V5 +
                "tips/" +
                "?etype=" +
                IEtype.ALL +
                "&settled=-1" +
                "&sort=" +
                ISort.TIME +
                "&p=" +
                currentPage;
        HttpClientUtil.getHttpClientUtil().sendPostRequest(TAG, sb, null, this);
    }

    @Override
    public void onBefore(Request request) {
    }

    @Override
    public void onError(Call call, Exception error) {
        swipeRefreshLayout1.onRefreshComplete();
    }

    @Override
    public void onSuccess(Call call, String response) {
        swipeRefreshLayout1.onRefreshComplete();
        KLog.json(response);
    }

    @Override
    public void onFinish(Call call) {
        swipeRefreshLayout1.onRefreshComplete();
    }
}
