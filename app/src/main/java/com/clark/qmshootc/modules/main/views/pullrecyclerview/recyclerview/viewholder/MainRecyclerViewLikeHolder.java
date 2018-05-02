package com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.clark.qmshootc.R;
import com.clark.qmshootc.common.views.hscrollview.ArtistViewAdapter;
import com.clark.qmshootc.common.views.hscrollview.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainRecyclerViewLikeHolder extends RecyclerView.ViewHolder {
    private MyHorizontalScrollView myHorizontalScrollView;
    private ArtistViewAdapter mAdapter;
    private List<Integer> mDatas = new ArrayList<>(Arrays.asList(
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01));

    public MainRecyclerViewLikeHolder(View itemView) {
        super(itemView);
        init(itemView);
    }

    private void init(View itemView) {
        myHorizontalScrollView = itemView.findViewById(R.id.id_horizontalScrollView);
        mAdapter = new ArtistViewAdapter(itemView.getContext(), mDatas);
        //添加滚动回调
        myHorizontalScrollView
                .setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener() {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator) {
                        viewIndicator.setBackgroundColor(Color
                                .parseColor("#AA024DA4"));
                    }
                });
        //添加点击回调
        myHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener() {

            @Override
            public void onClick(View view, int position) {
                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        myHorizontalScrollView.initDatas(mAdapter);
    }
}
