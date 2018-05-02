package com.clark.qmshootc.common.views.hscrollview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.clark.qmshootc.R;

import java.util.List;

public class PlaceViewAdapter implements BaseHorizontalAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Integer> mData;

    public PlaceViewAdapter(Context context, List<Integer> mDatas) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.mData = mDatas;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    public Object getItem(int position) {
        return mData.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(
                    R.layout.layout_place_detail_home, parent, false);
            viewHolder.mImg = convertView
                    .findViewById(R.id.id_iv_place);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.mImg.setImageResource(mData.get(position));

        return convertView;
    }

    private class ViewHolder {
        ImageView mImg;
    }

}
