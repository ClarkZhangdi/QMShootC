package com.clark.qmshootc.modules.main.views.viewpager.fragment.persenter;

import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;

public interface IMainPagerPresenter {
    interface View {
        void setRecyclerViewData();

        RecyclerView getRecyclerView();

        void setTitleBackGroundColor(@ColorInt int color, int dy);
    }
}
