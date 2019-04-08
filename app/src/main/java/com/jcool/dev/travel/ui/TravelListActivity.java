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
import com.jcool.dev.travel.fragment.HomeTabFragment;
import com.jcool.dev.travel.fragment.TravelTabFragment;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

public class TravelListActivity extends BaseActivity implements View.OnClickListener {
    private String tabTitle[] = {"全部", "出境游", "国内游", "周边游", "自由行"};
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private ViewPager viewpager;
    private TabLayout tlMainTabtop;
    private TextView icon_back;
    private int number;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_list_travel;
    }

    @Override
    protected void getExtra() {
        number = getIntent().getIntExtra("number", 0);
    }

    @Override
    protected void initView() {
        viewpager = findViewById(R.id.viewpager);
        icon_back = findViewById(R.id.icon_back);
        tlMainTabtop = findViewById(R.id.tl_main_tabtop);
    }

    @Override
    protected void initTools() {
        getFragmentData();
    }

    @Override
    protected void setListener() {
        icon_back.setOnClickListener(this);
        isView(number);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

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
        fragmentList.add(TravelTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "", "", ""));
        fragmentList.add(TravelTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "N", "", ""));
        fragmentList.add(TravelTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "Y", "", ""));
        fragmentList.add(TravelTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "", "", "济南市"));
        fragmentList.add(TravelTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "", "N", ""));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }


    private void isView(int i) {
        switch (i) {
            case 1:
                viewpager.setCurrentItem(1, true);
                break;
            case 2:
                viewpager.setCurrentItem(2, true);
                break;
            case 3:
                viewpager.setCurrentItem(3, true);
                break;
            case 4:
                viewpager.setCurrentItem(4, true);
                break;
            case 5:
                viewpager.setCurrentItem(0, true);
                break;
            default:
                viewpager.setCurrentItem(0);
                break;
        }
    }
}
