package com.clark.qmshootc.modules.login.presenter.impl;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.login.components.LoginActivity;
import com.clark.qmshootc.modules.login.presenter.ILoginPresenter;

public class LoginPresenter implements ILoginPresenter {


    private View view;
    private LoginActivity loginActivity;

    public LoginPresenter(ILoginPresenter.View view) {
        this.view = view;
        loginActivity = (LoginActivity) view;
    }

    @Override
    public void onClick(int id) {
        switch (id) {
            case R.id.id_iv_back:
                loginActivity.finish();
            case R.id.id_rl_login:

        }

    }
}
