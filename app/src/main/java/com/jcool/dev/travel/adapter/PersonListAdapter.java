package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.content.Intent;
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
import com.jcool.dev.travel.ui.AddPersonActivity;

import java.util.List;
import java.util.Vector;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonListAdapter extends BaseAdapter {
    private List<PersonInfoBean> mList;
    private Context mContext;
    private Vector<Boolean> mImage_bs = new Vector<Boolean>();// 定义一个向量作为选中与否容器
    private int lastPosition = -1;// 记录上一次选中的图片位置，-1表示未选中任何图片
    private boolean multiChoose = true;// 表示当前适配器是否允许多选

    public PersonListAdapter(Context mContext, List<PersonInfoBean> mList) {
        this.mContext = mContext;
        this.mList = mList;
        for (int i = 0; i < mList.size(); i++) {
            mImage_bs.add(false);
            mList.get(i).setCheck(false);
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_picker_person_list, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        if (mImage_bs.get(position)) {
            viewHolder.check_image.setImageResource(R.mipmap.icon_check_sel);
        } else {
            viewHolder.check_image.setImageResource(R.mipmap.icon_check_unsel);
        }

        viewHolder.tv_name.setText(mList.get(position).getCustName());
        if (TextUtils.equals("girl", mList.get(position).getCustSex())) {//旅客性别(boy,girl)
            viewHolder.tv_sex.setText("女");
        } else {
            viewHolder.tv_sex.setText("男");
        }

        //客户类型(01:在职；02：在校学生；03：退休；04：学龄儿童)
        if (TextUtils.equals("01", mList.get(position).getCustType())) {
            viewHolder.tv_work_type.setText("在职");
        } else if (TextUtils.equals("02", mList.get(position).getCustType())) {
            viewHolder.tv_work_type.setText("在校学生");
        } else if (TextUtils.equals("03", mList.get(position).getCustType())) {
            viewHolder.tv_work_type.setText("退休");
        } else {
            viewHolder.tv_work_type.setText("学龄儿童");
        }

        viewHolder.image_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, AddPersonActivity.class);
                intent.putExtra("info", mList.get(position));
                mContext.startActivity(intent);
            }
        });
        //旅客年龄段(01:0-12周岁；02：儿童；03：成人)
        if (TextUtils.equals("3", mList.get(position).getCustAge()) || TextUtils.equals("03", mList.get(position).getCustAge())) {
            viewHolder.tv_person_type.setText("成人");
        } else {
            viewHolder.tv_person_type.setText("儿童");
        }
        return convertView;
    }

    public List<PersonInfoBean> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.image_bianji)
        ImageView image_bianji;
        @BindView(R.id.check_image)
        ImageView check_image;
        @BindView(R.id.tv_sex)
        TextView tv_sex;
        @BindView(R.id.tv_person_type)
        TextView tv_person_type;
        @BindView(R.id.tv_work_type)
        TextView tv_work_type;

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
            if (mList != null) {
                mList.get(position).setCheck(!mList.get(position).isCheck());
            }
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
