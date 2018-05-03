package com.clark.qmshootc.modules.main.views.viewpager;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 只是为了让MainViewPager默认不拦截处理任何触摸事件
 */
public class MainViewPager extends ViewPager {
    public MainViewPager(@NonNull Context context) {
        super(context);
    }

    public MainViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
