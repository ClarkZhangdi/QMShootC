package com.clark.qmshootc.modules.guide.views.presenters.impls;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.clark.qmshootc.R;
import com.clark.qmshootc.TestKJActivity;
import com.clark.qmshootc.modules.guide.views.presenters.IFragmentPresenter;
import com.clark.qmshootc.modules.login.components.LoginActivity;


/**
 * create by SuperClark 2018/4/26
 */
public class FragmentPresenter implements IFragmentPresenter {
    private View dataView;
    private Fragment fragment;

    public FragmentPresenter(View dataView) {
        this.dataView = dataView;
        fragment = (Fragment) dataView;
    }


    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.id_btn_skip:

                break;
            case R.id.id_btn_login:
                fragment.startActivity(new Intent(fragment.getActivity(), LoginActivity.class));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                }
                break;
            case R.id.id_btn_register:
                fragment.startActivity(new Intent(fragment.getActivity(), TestKJActivity.class));

                break;
        }
    }
}
