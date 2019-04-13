package com.jcool.dev.travel.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ViewPagerFragmentAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.InfoColumn;
import com.jcool.dev.travel.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class OrderVisaFragment extends BaseFragment {
    private String tabTitle[] = {"全部", "待提交资料", "已审核", "已退审"};
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private ViewPager viewpager;
    private TabLayout tlMainTabtop;


    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tab_visa_order;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        viewpager = view.findViewById(R.id.viewpager);
        tlMainTabtop = view.findViewById(R.id.tl_main_tabtop);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initTools() {
        getFragmentData();
    }

    @Override
    protected void initData() {

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
        fragmentList.add(OtherVisaTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, ""));
        fragmentList.add(OtherVisaTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, "03"));
        fragmentList.add(OtherVisaTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, "01"));
        fragmentList.add(OtherVisaTabFragment.newInstance(Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_LIST, "02"));
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager());
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
