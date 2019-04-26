package com.jcool.dev.travel.ui;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;

import butterknife.BindView;

public class SearchKeyActivity extends BaseActivity {
    @BindView(R.id.title_bar1_edt)
    EditText title_bar1_edt;
    @BindView(R.id.tv_close)
    TextView tv_close;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_search_key;
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
}
