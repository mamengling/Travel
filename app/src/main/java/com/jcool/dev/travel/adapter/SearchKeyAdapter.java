package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.CityBeanHot;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchKeyAdapter extends RecyclerView.Adapter<SearchKeyAdapter.ViewHolder> {
    private Context mContext;
    private List<CityBeanHot> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener<CityBeanHot> mOnItemClickListener;

    public SearchKeyAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<CityBeanHot> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_hot_city, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.tv_city_name.setText(mList.get(i).getName());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(viewHolder.itemView, i, 0, 0, mList.get(i));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<CityBeanHot> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_city_name)
        TextView tv_city_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(ConstmOnItemOnclickListener<CityBeanHot> listener) {
        this.mOnItemClickListener = listener;
    }
}
