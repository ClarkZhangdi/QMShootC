package com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.clark.qmshootc.R;
import com.clark.qmshootc.common.views.hscrollview.ArtistViewAdapter;
import com.clark.qmshootc.common.views.hscrollview.MyHorizontalScrollView;
import com.clark.qmshootc.common.views.hscrollview.PlaceViewAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainRecyclerViewPlaceHolder extends RecyclerView.ViewHolder {
    private MyHorizontalScrollView myHorizontalScrollView;
    private PlaceViewAdapter mAdapter;
    private List<Integer> mDatas = new ArrayList<>(Arrays.asList(
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01));

    public MainRecyclerViewPlaceHolder(View itemView) {
        super(itemView);
        init(itemView);
    }

    private void init(View itemView) {
        myHorizontalScrollView = itemView.findViewById(R.id.id_horizontalScrollView);
        mAdapter = new PlaceViewAdapter(itemView.getContext(), mDatas);
        //设置适配器
        myHorizontalScrollView.initDatas(mAdapter);
    }
}
