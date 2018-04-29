package com.clark.qmshootc.modules.login.components;

import android.os.Bundle;
import android.view.View;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.clark.qmshootc.R;
import com.clark.qmshootc.TestKJActivity;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;
import com.clark.qmshootc.modules.login.presenter.ILoginPresenter;
import com.clark.qmshootc.modules.login.presenter.impl.LoginPresenter;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginPresenter.View {

    private ILoginPresenter loginPresenter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreenUtils.FullScreen(this);
        loginPresenter = new LoginPresenter(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        FullScreenUtils.FullScreen(this);
    }


    @OnClick({R.id.id_iv_back, R.id.id_rl_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_iv_back:
                loginPresenter.onClick(R.id.id_iv_back);
                break;
            case R.id.id_rl_login:
                loginPresenter.onClick(R.id.id_rl_login);
                startActivity(TestKJActivity.class);
                break;
        }
    }
}
