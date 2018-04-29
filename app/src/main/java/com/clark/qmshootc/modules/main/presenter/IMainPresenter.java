package com.clark.qmshootc.modules.main.presenter;

import android.support.v7.widget.RecyclerView;

public interface IMainPresenter {
    interface View {
        void setRecyclerViewData();
        RecyclerView getRecyclerView();

    }
}
