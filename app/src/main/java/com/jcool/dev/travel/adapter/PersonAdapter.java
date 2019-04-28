package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.MyRecyclerViewItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {
    private List<PersonInfoBean> mList = new ArrayList<>();
    private Context mContext;
    private ConstmOnItemOnclickListener<PersonInfoBean> mOnItemClickListener;

    public PersonAdapter(Context mContext) {
        this.mContext = mContext;
    }


    public void onReference(List<PersonInfoBean> list1) {
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
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.xml_item_person_list, null);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int position) {
        viewHolder.recyclerViewItem.apply();
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

        //旅客年龄段(01:0-12周岁；02：儿童；03：成人)
        if (TextUtils.equals("3", mList.get(position).getCustAge())||TextUtils.equals("03", mList.get(position).getCustAge())) {
            viewHolder.tv_person_type.setText("成人");
        } else {
            viewHolder.tv_person_type.setText("儿童");
        }
        viewHolder.image_bianji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(viewHolder.image_bianji, position, 0, 1, mList.get(position));
            }
        });
        viewHolder.click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(viewHolder.image_bianji, position, 0, 2, mList.get(position));
            }
        });


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public List<PersonInfoBean> getList() {
        return mList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.image_bianji)
        ImageView image_bianji;
        @BindView(R.id.tv_sex)
        TextView tv_sex;
        @BindView(R.id.tv_person_type)
        TextView tv_person_type;
        @BindView(R.id.tv_work_type)
        TextView tv_work_type;
        @BindView(R.id.click)
        TextView click;
        @BindView(R.id.myRecyclerViewItem)
        MyRecyclerViewItem recyclerViewItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(ConstmOnItemOnclickListener<PersonInfoBean> listener) {
        this.mOnItemClickListener = listener;
    }
}
