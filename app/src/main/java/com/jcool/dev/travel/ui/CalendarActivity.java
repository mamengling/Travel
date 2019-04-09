package com.jcool.dev.travel.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.haibin.calendarview.CalendarView;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.calendarView)
    CalendarView calendarView;
    @BindView(R.id.radiogroup_full)
    RadioGroup radiogroup_full;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_message)
    TextView tv_message;
    @BindView(R.id.tv_buy)
    TextView tv_buy;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_calender;
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

    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
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
