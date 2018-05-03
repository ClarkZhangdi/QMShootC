package com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.gridview.adapter.MainGridViewAdapter;

public class MainRecyclerGridViewHolder extends RecyclerView.ViewHolder {


    public MainRecyclerGridViewHolder(View itemView) {
        super(itemView);
        GridView gridView = itemView.findViewById(R.id.id_gv_like);
        gridView.setAdapter(new MainGridViewAdapter(itemView.getContext()));
    }
}
