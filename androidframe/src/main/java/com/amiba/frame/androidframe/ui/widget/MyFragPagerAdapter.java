package com.amiba.frame.androidframe.ui.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by wudl on 16/5/18.
 */
public class MyFragPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    private static final int PAGER_COUNT = 10;

    private Context mContext;
    private List<Fragment> fragmentList;

    public MyFragPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MyFragPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    public List<Fragment> getFragmentList() {
        return fragmentList;
    }

    public void setFragmentList(List<Fragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList!= null && fragmentList.size()>position ? fragmentList.get(position):null;
    }

    @Override
    public int getCount() {
        return fragmentList!= null ? fragmentList.size():0;
    }

}
