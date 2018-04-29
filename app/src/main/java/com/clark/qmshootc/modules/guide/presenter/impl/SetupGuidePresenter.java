package com.clark.qmshootc.modules.guide.presenter.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.clark.qmshootc.modules.guide.components.SetupGuideActivity;
import com.clark.qmshootc.modules.guide.presenter.ISetupGuidePresenter;
import com.clark.qmshootc.modules.guide.views.FragmentView;

import java.util.ArrayList;
import java.util.List;

public class SetupGuidePresenter implements ISetupGuidePresenter {


    private View view;
    private SetupGuideActivity setupGuideActivity;

    public SetupGuidePresenter(ISetupGuidePresenter.View view) {
        this.view = view;
        this.setupGuideActivity = (SetupGuideActivity) view;
        initViewPager();
    }

    private void initViewPager() {

        List<Fragment> list = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            Bundle bundle1 = new Bundle();
            bundle1.putString("Title", "第 " + i + " 个Fragment");
            bundle1.putInt("pager_num", i);
            Fragment fg1 = FragmentView.newInstance(bundle1);
            list.add(fg1);
        }

        view.getViewPager().setAdapter(new MyFragmentAdapter(setupGuideActivity.getSupportFragmentManager(), list));

    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list = list;
        }

        /**
         * 返回需要展示的fragment
         *
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        /**
         * 返回需要展示的fragment的数量
         *
         * @return
         */
        @Override
        public int getCount() {
            return list.size();
        }
    }


}
