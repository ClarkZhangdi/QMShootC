package com.clark.qmshootc.modules.guide.Views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.guide.Views.presenters.IFragmentPresenter;
import com.clark.qmshootc.modules.guide.Views.presenters.impls.FragmentPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 理解,我们实现 IFragmentPresenter接口的内部接口的意思就是我们不需要完整实现IFragmentPresenter的所有功能
 * 只需要按照规则实现他的部分功能即可,我们内部给IFragmentPresenter的实现类FragmentPresenter创建了对象,也就是说
 * 这时候只需要关注FragmentPresenter即可,而我们想在activity中和IFragmentPresenter交互,只需要在IFragmentPresenter
 * 定义对应的交互方法即可
 */
public class FragmentView extends Fragment implements IFragmentPresenter.View {

    Unbinder unbinder;
    @BindView(R.id.id_btn_skip)
    Button idBtnSkip;
    @BindView(R.id.id_btn_login)
    Button idBtnLogin;
    @BindView(R.id.id_btn_register)
    Button idBtnRegister;
    @BindView(R.id.id_ll_login)
    LinearLayout idLlLogin;

    private View rootView;
    private Bundle stateBundle;
    private IFragmentPresenter fragmentPresenter;

    @OnClick({R.id.id_btn_skip, R.id.id_btn_login, R.id.id_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.id_btn_skip:
                fragmentPresenter.onClick(R.id.id_btn_skip);
                break;
            case R.id.id_btn_login:
                fragmentPresenter.onClick(R.id.id_btn_login);
                break;
            case R.id.id_btn_register:
                fragmentPresenter.onClick(R.id.id_btn_register);
                break;
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stateBundle = getArguments();
        fragmentPresenter = new FragmentPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.layout_setup_guide_fragment, null);
        unbinder = ButterKnife.bind(this, rootView);
        initView(rootView);
        return rootView;
    }

    /**
     * 初始化Fragment的View
     *
     * @param view
     * @return
     */
    void initView(View view) {

        int page = stateBundle.getInt("pager_num");
        switch (page) {
            case 1: {
                view.setBackgroundResource(R.drawable.setup_guide_1);
                break;
            }
            case 2: {
                view.setBackgroundResource(R.drawable.setup_guide_2);
                break;
            }

            case 3: {
                view.setBackgroundResource(R.drawable.setup_guide_7);
                break;
            }

            case 4: {
                view.setBackgroundResource(R.drawable.setup_guide_8);
                if (idBtnSkip != null)
                    idBtnSkip.setVisibility(View.VISIBLE);
                if (idLlLogin != null)
                    idLlLogin.setVisibility(View.VISIBLE);
                break;
            }

        }
    }


    public static FragmentView newInstance(Bundle args) {
        FragmentView fragment = new FragmentView();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void ClickSkip() {

    }

    @Override
    public void ClickLogin() {

    }

    @Override
    public void ClickRegister() {

    }


}