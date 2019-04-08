package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jcool.dev.travel.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    public List<String> data;
    public Context context;

    public ListAdapter(Context context, List<String> arrayList) {
        data = arrayList;
        this.context = context;
    }

    public void changeData(List<String> list) {
        if (data == null) {
            data = list;
        } else {
            data.clear();
            data.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_search, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = view.findViewById(R.id.cityName);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText(data.get(i));
        return view;
    }

    public class ViewHolder {
        TextView textView;
    }
}
