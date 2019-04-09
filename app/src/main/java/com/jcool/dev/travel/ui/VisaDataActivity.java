package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.VisaWorkDataAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaDataActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    private List<VisaInfoBean.VisaSpecBean> mList;
    private VisaWorkDataAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_visa_data;
    }

    @Override
    protected void getExtra() {
        mList = getIntent().getParcelableArrayListExtra("list");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        icon_title.setText("所需材料");
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new VisaWorkDataAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setEnableLoadmore(false);
        mAdapter.onReference(mList);
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
        }
    }
}
