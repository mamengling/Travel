package com.jcool.dev.travel.ui;

import android.os.Bundle;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;

public class TravelOrderDetailActivity extends BaseActivity {

    private String orderId;

    @Override
    protected int getContentViewId() {
        return R.layout.avtivity_travel_detail_order;
    }

    @Override
    protected void getExtra() {
        orderId = getIntent().getStringExtra("orderId");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTools() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }
}
