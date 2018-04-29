package com.clark.qmshootc.modules.guide.views.presenters;

/**
 * create by SuperClark 2018/4/26
 */
public interface IFragmentPresenter   {
    interface View  {
        void ClickSkip();
        void ClickLogin();
        void ClickRegister();

    }
    void onClick(int viewId);
}
