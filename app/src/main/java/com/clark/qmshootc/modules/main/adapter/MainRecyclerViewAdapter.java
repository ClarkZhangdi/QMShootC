package com.clark.qmshootc.modules.main.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.clark.qmshootc.R;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 定义主界面的RecyclerView的适配器
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<String> picList;
    private List<Map<String, Object>> channelList;
    private List<Integer> girlList;
    private List<String> normalList;
    private final int BANNER_VIEW_TYPE = 1 << 0;//Banner+GridView
    private final int ARTIST_VIEW_TYPE = 1 << 1;//艺人推荐
    private final int PLACE_VIEW_TYPE = 1 << 2;//场地推荐
    private final int LIKE_VIEW_TYPE = 1 << 3;//猜你喜欢

    public MainRecyclerViewAdapter(Context context,
                                   List<String> picList,
                                   List<Map<String, Object>> channelList,
                                   List<Integer> girlList,
                                   List<String> normalList) {
        this.context = context;
        this.picList = picList;
        this.channelList = channelList;
        this.girlList = girlList;
        this.normalList = normalList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case BANNER_VIEW_TYPE:
                android.view.View viewInflate =
                        LayoutInflater.from(parent.getContext()).
                                inflate(R.layout.layout_banner_home, parent, false);
                return new BannerViewHolder(viewInflate);
            case ARTIST_VIEW_TYPE:
                return new ArtistsRecommendedViewHolder(parent);
            case PLACE_VIEW_TYPE:
                return new PlaceRecommendedViewHolder(parent);
            case LIKE_VIEW_TYPE:
                return new GuessLikeViewHolder(parent);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof BannerViewHolder) {
            setBanner(holder);

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return BANNER_VIEW_TYPE;
            case 1:
                return ARTIST_VIEW_TYPE;
            case 2:
                return PLACE_VIEW_TYPE;
            case 3:
                return LIKE_VIEW_TYPE;
            default:
                return -1;
        }
    }

    @OnClick({R.id.id_vp_banner, R.id.id_ll_point_container})
    public void onViewClicked(android.view.View view) {
        switch (view.getId()) {
            case R.id.id_vp_banner:
                break;
            case R.id.id_ll_point_container:
                break;
        }
    }


    /**
     * 设置Banner 的 ViewHolder
     *
     * @param holder
     */
    private void setBanner(RecyclerView.ViewHolder holder) {

    }

    /**
     * 定义轮播图ViewHolder
     */
    class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.id_vp_banner)
        ViewPager idVpBanner;
        @BindView(R.id.id_ll_point_container)
        LinearLayout idLlPointContainer;

        public BannerViewHolder(android.view.View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }

    /**
     * 定义艺人推荐ViewHolder
     */
    class ArtistsRecommendedViewHolder extends RecyclerView.ViewHolder {

        public ArtistsRecommendedViewHolder(android.view.View itemView) {
            super(itemView);
        }
    }

    /**
     * 定义场地推荐ViewHolder
     */
    class PlaceRecommendedViewHolder extends RecyclerView.ViewHolder {

        public PlaceRecommendedViewHolder(android.view.View itemView) {
            super(itemView);
        }
    }

    /**
     * 定义猜你喜欢ViewHolder
     */

    class GuessLikeViewHolder extends RecyclerView.ViewHolder {

        public GuessLikeViewHolder(android.view.View itemView) {
            super(itemView);
        }
    }
}