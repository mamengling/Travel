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

public class OrderGoodsFragment extends BaseFragment {
    private String tabTitle[] = {"全部", "待付款", "待出行", "待评价", "已完成"};
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private ViewPager viewpagerOrderTravel;
    private TabLayout tlMainTabtopOrderTravel;
    private int number;

    public static OrderGoodsFragment newInstance() {

        Bundle args = new Bundle();

        OrderGoodsFragment fragment = new OrderGoodsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tab_travel_order;
    }

    @Override
    protected void getExtra() {
        number = getActivity().getIntent().getIntExtra("number", 0);
    }

    @Override
    protected void initView(View view) {
        viewpagerOrderTravel = view.findViewById(R.id.viewpager);
        tlMainTabtopOrderTravel = view.findViewById(R.id.tl_main_tabtop);
    }

    @Override
    protected void setListener() {
        getFragmentData();
//        isView(number);
    }

    @Override
    protected void initTools() {
//        getFragmentData();
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

        fragmentList = new ArrayList<>();//订单状态值
        // （可多个，用逗号隔开）CREATE订单已经提交;PAY订单已经支付;REFUNDING退款中;REFUNDED已退款;USED订单已经出行;EVALUATE订单已经评价;CLOSE订单取消或是关闭

        fragmentList.add(OtherTravelTabFragment.newInstance(Constants.APP_HOME_API_VISA_ORDER_JOURNEY_GOODS_FORM_QUERY_APP, ""));//全部
        fragmentList.add(OtherTravelTabFragment.newInstance(Constants.APP_HOME_API_VISA_ORDER_JOURNEY_GOODS_FORM_QUERY_APP, "CREATE"));//待付款
        fragmentList.add(OtherTravelTabFragment.newInstance(Constants.APP_HOME_API_VISA_ORDER_JOURNEY_GOODS_FORM_QUERY_APP, "PAY"));//待出行
        fragmentList.add(OtherTravelTabFragment.newInstance(Constants.APP_HOME_API_VISA_ORDER_JOURNEY_GOODS_FORM_QUERY_APP, "USED"));//待评价
        fragmentList.add(OtherTravelTabFragment.newInstance(Constants.APP_HOME_API_VISA_ORDER_JOURNEY_GOODS_FORM_QUERY_APP, "EVALUATE"));//已完成
        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager());
        viewpagerOrderTravel.setAdapter(viewPagerFragmentAdapter);
        tlMainTabtopOrderTravel.setupWithViewPager(viewpagerOrderTravel);
        // 更新适配器数据
        viewPagerFragmentAdapter.setList(infoColumnList);
        viewPagerFragmentAdapter.setListData(fragmentList);
//        app:tabMode="fixed"
        tlMainTabtopOrderTravel.setTabMode(TabLayout.MODE_FIXED);
        viewpagerOrderTravel.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tlMainTabtopOrderTravel));
        tlMainTabtopOrderTravel.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewpagerOrderTravel.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    private void isView(int i) {
        switch (i) {
            case 1:
                viewpagerOrderTravel.setCurrentItem(1, true);
                break;
            case 2:
                viewpagerOrderTravel.setCurrentItem(2, true);
                break;
            case 3:
                viewpagerOrderTravel.setCurrentItem(3, true);
                break;
            case 4:
                viewpagerOrderTravel.setCurrentItem(4, true);
                break;
            case 5:
                viewpagerOrderTravel.setCurrentItem(0, true);
                break;
            default:
                viewpagerOrderTravel.setCurrentItem(0);
                break;
        }
    }
}
