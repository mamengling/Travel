package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.TravelInfoBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelInfoLineAdapter extends BaseAdapter {
    private List<TravelInfoBean.LinesBean> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;

    public TravelInfoLineAdapter(Context mContext) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
    }

    public void onReference(List<TravelInfoBean.LinesBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        return mList.size();
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
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.xml_item_travel_lable, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_line_lable.setText(mList.get(position).getLineName());
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_line_lable)
        TextView tv_line_lable;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
