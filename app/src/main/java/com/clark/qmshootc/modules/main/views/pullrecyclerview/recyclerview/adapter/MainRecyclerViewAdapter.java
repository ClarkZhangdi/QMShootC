package com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder.MainRecyclerGridViewHolder;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder.MainRecyclerViewArtistHolder;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder.MainRecyclerViewBannerHolder;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder.MainRecyclerViewLikeHolder;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.recyclerview.viewholder.MainRecyclerViewPlaceHolder;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /********************模拟测试数据开始**********************************/
    private int itemCount = 4;
    /********************模拟测试数据结束**********************************/

    private final int BANNER_TYPE = 0;
    private final int ARTIST_TYPE = 1;
    private final int PLACE_TYPE = 2;
    private final int LIKE_TYPE = 3;
    private LayoutInflater mLayoutInflater;
    private Context context;

    public MainRecyclerViewAdapter(Context context) {
        this.context = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder mViewHolder;
        switch (viewType) {
            case BANNER_TYPE:
                mViewHolder = new MainRecyclerViewBannerHolder(
                        mLayoutInflater.inflate(R.layout.layout_banner_home, parent, false), context);
                ((MainRecyclerViewBannerHolder) mViewHolder).startBanner();

                return mViewHolder;
            case ARTIST_TYPE:
                mViewHolder = new MainRecyclerViewArtistHolder(
                        mLayoutInflater.inflate(R.layout.layout_artist_home, parent, false));
                return mViewHolder;
            case PLACE_TYPE:
                mViewHolder = new MainRecyclerViewPlaceHolder(
                        mLayoutInflater.inflate(R.layout.layout_place_home, parent, false));
                return mViewHolder;
            case LIKE_TYPE:
                mViewHolder = new MainRecyclerGridViewHolder(
                        mLayoutInflater.inflate(R.layout.layout_like_home, parent, false));
                return mViewHolder;
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        轮播图
        if (holder instanceof MainRecyclerViewBannerHolder) {
        }
//        艺人推荐
        else if (holder instanceof MainRecyclerViewArtistHolder) {

        }
//        场地推荐
        else if (holder instanceof MainRecyclerViewPlaceHolder) {

        }
//        猜你喜欢
        else if (holder instanceof MainRecyclerViewLikeHolder) {

        }
    }

    @Override
    public int getItemCount() {
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_TYPE;
            case 1:
                return ARTIST_TYPE;
            case 2:
                return PLACE_TYPE;
            default:
                return LIKE_TYPE;


        }
    }
}
