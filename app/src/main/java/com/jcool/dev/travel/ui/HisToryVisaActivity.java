package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.StatusBarUtil;

public class HisToryVisaActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_visa_history;
    }

    @Override
    protected void getExtra() {

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

    @Override
    public void onClick(View v) {

    }
}
