package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AccMangerActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_pas)
    TextView tv_pas;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_acc_mannger;
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
        ActivityCollector.getInstance().flagActivity(this);
        if (isLogin() && getUserPhone() != null) {
            tv_phone.setText(AppUtils.getPhoneCode(getUserPhone()));
        }
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_pas.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
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
            case R.id.tv_phone:
                Intent intent = new Intent(this, SetPhoneActivity.class);
                intent.putExtra("phone", getUserPhone());
                startActivity(intent);
                break;
            case R.id.tv_pas:
                Intent intentPass = new Intent(this, RePasswordActivity.class);
                startActivity(intentPass);
                break;
        }
    }
}
