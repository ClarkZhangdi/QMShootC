package com.clark.qmshootc.common.views.hscrollview;

import android.view.View;
import android.view.ViewGroup;

public interface BaseHorizontalAdapter {
    int getCount();
    View getView(int position, View convertView, ViewGroup parent);
}
