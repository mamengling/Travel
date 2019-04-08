package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_user_birthday)
    TextView tv_user_birthday;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_head)
    TextView tv_head;
    @BindView(R.id.image_head)
    ImageView image_head;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_user_info;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        icon_right.setVisibility(View.GONE);
        icon_title.setText("个人信息");
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_city.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        tv_user_birthday.setOnClickListener(this);
        tv_head.setOnClickListener(this);
        image_head.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_title_back:
                finish();
                break;
            case R.id.tv_city:
                // TODO: 2019/4/3
                break;
            case R.id.tv_phone:
                // TODO: 2019/4/3
                break;
            case R.id.tv_sex:
                // TODO: 2019/4/3
                break;
            case R.id.tv_user_birthday:
                // TODO: 2019/4/3
                break;
            case R.id.tv_user_name:
                // TODO: 2019/4/3
                break;
            case R.id.image_head:
            case R.id.tv_head:
                // TODO: 2019/4/3
                break;
        }
    }
}
