package com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.clark.qmshootc.R;
import com.clark.qmshootc.common.views.hscrollview.ArtistViewAdapter;
import com.clark.qmshootc.common.views.hscrollview.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainRecyclerViewArtistHolder extends RecyclerView.ViewHolder {
    private MyHorizontalScrollView myHorizontalScrollView;
    private ArtistViewAdapter mAdapter;
    private List<Integer> mDatas = new ArrayList<>(Arrays.asList(
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01));

    public MainRecyclerViewArtistHolder(View itemView) {
        super(itemView);
        init(itemView);
    }

    private void init(View itemView) {
        myHorizontalScrollView = itemView.findViewById(R.id.id_horizontalScrollView);
        mAdapter = new ArtistViewAdapter(itemView.getContext(), mDatas);
        myHorizontalScrollView.initDatas(mAdapter);
    }
}

