package com.clark.qmshootc.modules.login.components;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.amiba.frame.androidframe.base.BaseActivity;
import com.amiba.frame.androidframe.ui.sysbar.SystemBarTintUtil;
import com.clark.qmshootc.R;
import com.clark.qmshootc.common.utils.app.FullScreenUtils;

public class LoginActivity extends BaseActivity {

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullScreenUtils.FullScreen(this);
    }
}
