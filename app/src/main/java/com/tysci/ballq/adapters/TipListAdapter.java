package com.tysci.ballq.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by QianBao on 2016/4/27.
 *
 * @see com.tysci.ballq.fragments.TipListFragment
 */
public class TipListAdapter extends BaseRecyclerAdapter<String, TipListAdapter.TipListHolder> {
    public TipListAdapter(Context context) {
        super(context);
    }

    @Override
    public TipListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TipListHolder holder, int position) {
    }

    class TipListHolder extends RecyclerView.ViewHolder {
        public TipListHolder(View itemView) {
            super(itemView);
        }
    }
}
