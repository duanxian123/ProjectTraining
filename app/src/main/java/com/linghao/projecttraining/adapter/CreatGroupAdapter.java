package com.linghao.projecttraining.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.linghao.projecttraining.R;

import java.util.List;

/**
 * Created by linghao on 2017/9/8.
 * QQ：782695971
 * 作用：
 */

public class CreatGroupAdapter extends RecyclerView.Adapter{
    public CreatGroupAdapter() {

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_creat_group,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;

            viewHolder.textView.setText("测试"+position);


    }

    @Override
    public int getItemCount() {
        return 50;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.test);
        }
    }
}
