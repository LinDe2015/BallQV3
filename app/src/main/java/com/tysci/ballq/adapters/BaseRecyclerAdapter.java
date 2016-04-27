package com.tysci.ballq.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by QianBao on 2016/4/27.
 * wrap adapter
 *
 * @see android.support.v7.widget.RecyclerView.Adapter
 */
public abstract class BaseRecyclerAdapter<Bean, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    protected final Context context;
    protected final ArrayList<Bean> dataList;

    public BaseRecyclerAdapter(Context context) {
        this(context, null);
    }

    public BaseRecyclerAdapter(Context context, ArrayList<Bean> dataList) {
        this.context = context;
        this.dataList = dataList == null ? new ArrayList<Bean>() : dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public final void refreshDataList(ArrayList<Bean> dataList) {
        refreshDataList(dataList, false);
    }

    public final void refreshDataList(ArrayList<Bean> dataList, boolean append) {
        if (!append)
            dataList.clear();
        if (dataList != null)
            this.dataList.addAll(dataList);
        notifyDataSetChanged();
    }
}
