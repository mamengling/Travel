package com.jcool.dev.travel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.StatusBarUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaCommitNextActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true,this);
//        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_commit_visa_next;
    }

    @Override
    protected void getExtra() {
        String orderid = getIntent().getStringExtra("orderid");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
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
