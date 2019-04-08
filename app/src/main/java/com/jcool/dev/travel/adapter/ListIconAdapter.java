package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.HomeIconBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListIconAdapter extends BaseAdapter {
    private List<HomeIconBean> mList;
    private Context mContext;

    public ListIconAdapter(List<HomeIconBean> mList, Context mContext) {
        this.mList = mList;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list_icon_user_info_h, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.tv_name.setText(mList.get(position).getName());
        return convertView;
    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
