package com.clark.qmshootc.modules.main.views.pullrecyclerview.gridview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.clark.qmshootc.R;
import com.clark.qmshootc.modules.main.views.pullrecyclerview.gridview.viewholder.MainGridViewHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainGridViewAdapter extends BaseAdapter {

    private List<Integer> mDatas = new ArrayList<>(Arrays.asList(
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01, R.drawable.b01, R.drawable.b01, R.drawable.b01,
            R.drawable.b01));
    private Context context;
    private LayoutInflater inflater;

    public MainGridViewAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        网络加载解析数据
        MainGridViewHolder mainGridViewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.layout_like_detail_home, parent, false);
            mainGridViewHolder = new MainGridViewHolder();
            mainGridViewHolder.bigImageView = convertView.findViewById(R.id.id_iv_like);
            convertView.setTag(mainGridViewHolder);
        } else {
            mainGridViewHolder = (MainGridViewHolder) convertView.getTag();
        }
        mainGridViewHolder.bigImageView.setImageResource(mDatas.get(position));
        return convertView;
    }
}
