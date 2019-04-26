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
import com.jcool.dev.travel.fragment.LoginAccFragment;
import com.jcool.dev.travel.fragment.LoginCodeFragment;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TabLayout tlMainTabtop;
    private ViewPager viewpager;
    private TextView icon_title;
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private String tabTitle[] = {"账号登录", "验证码登录"};

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_login;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        tlMainTabtop = findViewById(R.id.tl_main_tabtop);
        viewpager = findViewById(R.id.viewpager);
        icon_title = findViewById(R.id.icon_title);
        findViewById(R.id.icon_title_back).setOnClickListener(this);
    }

    @Override
    protected void initTools() {
        icon_title.setText("登录");
        getFragmentData();
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
        fragmentList.add(LoginAccFragment.newInstance("", "N", ""));
        fragmentList.add(LoginCodeFragment.newInstance("", "Y", ""));
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
            case R.id.icon_title_back:
                finish();
                break;
        }
    }
}
