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
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelInfoLineAdapter extends BaseAdapter {
    private List<TravelInfoBean.LinesBean> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context mContext;
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();// 定义一个向量作为选中与否容器
    private int lastPosition = -1;// 记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose = false;// 表示当前适配器是否允许多选

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
            mImage_bs.clear();
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
        if (mList.get(position).isCheck()){
            holder.tv_line_lable.setBackgroundResource(R.drawable.x_edt_bg_rootcolor03);
        }else {
            holder.tv_line_lable.setBackgroundResource(R.drawable.x_edt_bg_rootcolor02);
        }
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.tv_line_lable)
        TextView tv_line_lable;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    // 修改选中的状态
    public void changeState(int position) {
        // 多选时
        if (multiChoose == true) {
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接取反即可
        }
        // 单选时
        else {
            if (lastPosition != -1)
                mImage_bs.setElementAt(false, lastPosition);// 取消上一次的选中状态
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接取反即可
            lastPosition = position; // 记录本次选中的位置
        }
        notifyDataSetChanged(); // 通知适配器进行更新
    }
}
