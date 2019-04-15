package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.StringBean;

import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StringListAdapter extends BaseAdapter {
    private List<StringBean> mList;
    private Context mContext;
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();// 定义一个向量作为选中与否容器
    private int lastPosition = -1;// 记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose = true;// 表示当前适配器是否允许多选

    public StringListAdapter(Context mContext, List<StringBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
//        for (int i = 0; i < mList.size(); i++) {
//            mImage_bs.add(false);
//            mList.get(i).setCheck(false);
//        }
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
        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_picker_str_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        viewHolder.tv_name.setText(mList.get(position).getTitle());
        viewHolder.tv_content.setText(mList.get(position).getContent());
        return convertView;
    }

    public List<StringBean> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_content)
        TextView tv_content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
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
            if (lastPosition != -1) {
                mImage_bs.setElementAt(false, lastPosition);// 取消上一次的选中状态
            }
            mImage_bs.setElementAt(!mImage_bs.elementAt(position), position); // 直接取反即可
            lastPosition = position; // 记录本次选中的位置
        }
        notifyDataSetChanged(); // 通知适配器进行更新
    }
}
