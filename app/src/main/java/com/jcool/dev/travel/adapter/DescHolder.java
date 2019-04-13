package com.jcool.dev.travel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;

/**
 * Created by yushuangping on 2018/8/23.
 */

public class DescHolder extends RecyclerView.ViewHolder {
    public TextView tv_title_name_item;
    public ImageView descView;

    public DescHolder(View itemView) {
        super(itemView);
        initView();
    }

    private void initView() {
        tv_title_name_item = (TextView) itemView.findViewById(R.id.tv_title_name_item);
        descView = (ImageView) itemView.findViewById(R.id.tv_image_card);
    }
}
