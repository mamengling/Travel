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
import com.jcool.dev.travel.fragment.TravelSalesFragment;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelSalesActivity extends BaseActivity implements View.OnClickListener {
    private String tabTitle[] = {"全部", "正在进行", "即将开始", "已售罄"};
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.tl_main_tabtop)
    TabLayout tlMainTabtop;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_travel_sales;
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
        getFragmentData();
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

        fragmentList = new ArrayList<>();
        fragmentList.add(TravelSalesFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_SALES_QUERY, "", "", ""));
        fragmentList.add(TravelSalesFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_SALES_QUERY, "ING", "", ""));
        fragmentList.add(TravelSalesFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_SALES_QUERY, "FUTURE", "", ""));
        fragmentList.add(TravelSalesFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_SALES_QUERY, "ENDED", "", ""));
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
