package com.clark.qmshootc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.clark.qmshootc.common.views.hscrollview.ArtistViewAdapter;
import com.clark.qmshootc.common.views.hscrollview.MyHorizontalScrollView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestHScrollViewActivity extends BaseActivity {

    private MyHorizontalScrollView mHorizontalScrollView;
    private ArtistViewAdapter mAdapter;
    private ImageView mImg;
    private List<Integer> mDatas = new ArrayList<>(Arrays.asList(
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01));

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test_hscroll;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mImg =  findViewById(R.id.id_content);

        mHorizontalScrollView =  findViewById(R.id.id_horizontalScrollView);
        mAdapter = new ArtistViewAdapter(this, mDatas);
        //添加滚动回调
        mHorizontalScrollView
                .setCurrentImageChangeListener(new MyHorizontalScrollView.CurrentImageChangeListener()
                {
                    @Override
                    public void onCurrentImgChanged(int position,
                                                    View viewIndicator)
                    {
                        mImg.setImageResource(mDatas.get(position));
                        viewIndicator.setBackgroundColor(Color
                                .parseColor("#AA024DA4"));
                    }
                });
        //添加点击回调
        mHorizontalScrollView.setOnItemClickListener(new MyHorizontalScrollView.OnItemClickListener()
        {

            @Override
            public void onClick(View view, int position)
            {
                mImg.setImageResource(mDatas.get(position));
                view.setBackgroundColor(Color.parseColor("#AA024DA4"));
            }
        });
        //设置适配器
        mHorizontalScrollView.initDatas(mAdapter);
    }
}
