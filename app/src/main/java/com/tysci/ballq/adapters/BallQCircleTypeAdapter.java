package com.tysci.ballq.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tysci.ballq.R;

/**
 * Created by Administrator on 2016/4/27.
 * 圈子类型项
 */
public class BallQCircleTypeAdapter extends RecyclerView.Adapter<BallQCircleTypeAdapter.BallQCircleTypeViewHolder>{


    @Override
    public BallQCircleTypeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ballq_circle_type_item,parent,false);
        return new BallQCircleTypeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BallQCircleTypeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static final class BallQCircleTypeViewHolder extends RecyclerView.ViewHolder{

        public BallQCircleTypeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
