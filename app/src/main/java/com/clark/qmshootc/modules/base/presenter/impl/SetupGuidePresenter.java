package com.clark.qmshootc.modules.base.presenter.impl;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.clark.qmshootc.modules.guide.Views.FragmentView;
import com.clark.qmshootc.modules.guide.components.SetupGuideActivity;
import com.clark.qmshootc.modules.guide.presenter.ISetupGuidePresenter;

import java.util.ArrayList;
import java.util.List;

public class SetupGuidePresenter implements ISetupGuidePresenter {


    private View view;
    private SetupGuideActivity setupGuideActivity;

    public SetupGuidePresenter(View view) {
        this.view = view;
        this.setupGuideActivity = (SetupGuideActivity) view;
        initViewPager();
    }

    private void initViewPager() {

        List<Fragment> list = new ArrayList<>();

        Bundle bundle1 = new Bundle();
        bundle1.putString("Title", "第一个Fragment");
        bundle1.putInt("pager_num", 1);
        Fragment fg1 = FragmentView.newInstance(bundle1);

        Bundle bundle2 = new Bundle();
        bundle2.putString("Title", "第二个Fragment");
        bundle2.putInt("pager_num", 2);
        Fragment fg2 = FragmentView.newInstance(bundle2);

        Bundle bundle3 = new Bundle();
        bundle3.putString("Title", "第三个Fragment");
        bundle3.putInt("pager_num", 3);
        Fragment fg3 = FragmentView.newInstance(bundle3);

        Bundle bundle4 = new Bundle();
        bundle4.putString("Title", "第四个Fragment");
        bundle4.putInt("pager_num", 4);
        Fragment fg4 = FragmentView.newInstance(bundle4);

        list.add(fg1);
        list.add(fg2);
        list.add(fg3);
        list.add(fg4);

        view.getViewPager().setAdapter(new MyFragmentAdapter(setupGuideActivity.getSupportFragmentManager(),list));

    }

    class MyFragmentAdapter extends FragmentPagerAdapter {

        List<Fragment> list;

        public MyFragmentAdapter(FragmentManager fm, List<Fragment> list) {
            super(fm);
            this.list=list;
        }

        /**
         * 返回需要展示的fragment
         * @param position
         * @return
         */
        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        /**
         * 返回需要展示的fragment的数量
         * @return
         */
        @Override
        public int getCount() {
            return list.size();
        }
    }


}
