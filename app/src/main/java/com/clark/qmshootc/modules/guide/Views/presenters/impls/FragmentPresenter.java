package com.clark.qmshootc.modules.guide.Views.presenters.impls;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.Fragment;

import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.guide.Views.presenters.IFragmentPresenter;
import com.clark.qmshootc.modules.login.components.LoginActivity;


/**
 * create by SuperClark 2018/4/26
 */
public class FragmentPresenter implements IFragmentPresenter {
    private View dataView;
    private Fragment fragment;

    public FragmentPresenter(IFragmentPresenter.View dataView) {
        this.dataView = dataView;
        fragment = (Fragment) dataView;
    }


    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.id_btn_skip:
//                showFindActivity();
//                ToastUtil.show("id_btn_skip");

                break;
            case R.id.id_btn_login:
//                showLoginActivity();
//                ToastUtil.show("id_btn_login");
                fragment.startActivity(new Intent(fragment.getActivity(), LoginActivity.class));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                    fragment.startActivity(new Intent(fragment.getActivity(), RegisterActivity.class), ActivityOptions.makeSceneTransitionAnimation(fragment.getActivity()).toBundle());
                }
                break;
            case R.id.id_btn_register:
//                showLoginActivity();
//                ToastUtil.show("id_btn_register");
                fragment.startActivity(new Intent(fragment.getActivity(), LoginActivity.class));

                break;
        }
    }
}
