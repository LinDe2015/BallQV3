package com.tysci.ballq.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tysci.ballq.R;
import com.tysci.ballq.modles.BallQNoteEntity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
public class BallQCircleHotNoteAdapter extends RecyclerView.Adapter<BallQCircleHotNoteAdapter.BallQCircleHotNoteViewHolder>{
    private List<BallQNoteEntity> ballQNoteEntities;

    public BallQCircleHotNoteAdapter(List<BallQNoteEntity>list){
        this.ballQNoteEntities=list;
    }

    @Override
    public BallQCircleHotNoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_ballq_hot_note_item,parent,false);
        return new BallQCircleHotNoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BallQCircleHotNoteViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ballQNoteEntities.size();
    }

    public static final class BallQCircleHotNoteViewHolder extends RecyclerView.ViewHolder{

        public BallQCircleHotNoteViewHolder(View itemView) {
            super(itemView);
        }
    }
}
