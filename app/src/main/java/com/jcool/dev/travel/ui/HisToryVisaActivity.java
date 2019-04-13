package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ViewPagerFragmentAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.InfoColumn;
import com.jcool.dev.travel.fragment.OtherTabFragment;
import com.jcool.dev.travel.fragment.TravelTabFragment;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

public class HisToryVisaActivity extends BaseActivity implements View.OnClickListener {
    private String tabTitle[] = {"全部", "待审核", "已审核", "已退审"};
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private ViewPager viewpager;
    private TabLayout tlMainTabtop;
    private TextView icon_back;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_visa_history;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        icon_back = findViewById(R.id.icon_title_back);
        viewpager = findViewById(R.id.viewpager);
        tlMainTabtop = findViewById(R.id.tl_main_tabtop);
    }

    @Override
    protected void initTools() {
        getFragmentData();
    }

    @Override
    protected void setListener() {
        icon_back.setOnClickListener(this);
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


    public void getFragmentData() {
        infoColumnList = new ArrayList<>();
        for (int i = 0; i < tabTitle.length; i++) {
            InfoColumn infoColumn = new InfoColumn();
            infoColumn.setColumnCode("" + i);
            infoColumn.setColumnName(tabTitle[i]);
            infoColumn.setColumnType("0" + i);
            infoColumnList.add(infoColumn);
        }

        fragmentList = new ArrayList<>();//订单总状态（01：通过；02：驳回；03：待审核）
        fragmentList.add(OtherTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, ""));
        fragmentList.add(OtherTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, "03"));
        fragmentList.add(OtherTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, "01"));
        fragmentList.add(OtherTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, "02"));
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(viewPagerFragmentAdapter);
        tlMainTabtop.setupWithViewPager(viewpager);
        // 更新适配器数据
        viewPagerFragmentAdapter.setList(infoColumnList);
        viewPagerFragmentAdapter.setListData(fragmentList);
//        app:tabMode="fixed"
        tlMainTabtop.setTabMode(TabLayout.MODE_FIXED);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMainTabtop));
        tlMainTabtop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

}
