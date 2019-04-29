package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ViewPagerFragmentAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.InfoColumn;
import com.jcool.dev.travel.fragment.LoginAccFragment;
import com.jcool.dev.travel.fragment.LoginCodeFragment;
import com.jcool.dev.travel.iactivityview.LoginActivityView;
import com.jcool.dev.travel.persenter.LoginActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.log.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class LoginActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener, LoginActivityView {
    private LoginActivityPresenter mPresenter;
    private TabLayout tlMainTabtop;
    private ViewPager viewpager;
    private TextView icon_title;
    private ImageView qq;
    private ImageView wx;
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private String tabTitle[] = {"账号登录", "验证码登录"};
    private Platform plat;
    private int shareLoginType;
    private String wxOpenid;
    private String qqOpenid;
    private String accessToken;

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
        wx = findViewById(R.id.wx);
        qq = findViewById(R.id.qq);
        findViewById(R.id.icon_title_back).setOnClickListener(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new LoginActivityPresenter(this, this);
        icon_title.setText("登录");
        getFragmentData();
    }

    @Override
    protected void setListener() {
        qq.setOnClickListener(this);
        wx.setOnClickListener(this);
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
            case R.id.wx:
                Platform platWechat = ShareSDK.getPlatform(Wechat.NAME);
                ShareSDK.setActivity(this);//抖音登录适配安卓9.0
                platWechat.showUser(null);
                //这里开启一下SSO，防止OneKeyShare分享时调用了oks.disableSSOWhenAuthorize();把SSO关闭了
                platWechat.SSOSetting(false);
                platWechat.setPlatformActionListener(this);
                shareLoginType = 0;
                break;
            case R.id.qq:
                Platform platQQ = ShareSDK.getPlatform(QQ.NAME);
                ShareSDK.setActivity(this);//抖音登录适配安卓9.0
                platQQ.showUser(null);
                platQQ.SSOSetting(false);
                platQQ.setPlatformActionListener(this);
                shareLoginType = 1;
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, final HashMap<String, Object> hashMap) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (shareLoginType == 0) {//wx
                    LogUtil.i("hashMap", hashMap.toString());
                    wxOpenid = (String) hashMap.get("openid");
                    mPresenter.wxLogin();
                } else {//qq
                    qqOpenid = (String) hashMap.get("openid");
                    accessToken = (String) hashMap.get("accessToken");
                }

            }
        });

    }

    @Override
    public void onError(Platform platform, int i, final Throwable throwable) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LogUtil.i("hashMap", throwable.getMessage().toString());
            }
        });

    }

    @Override
    public void onCancel(Platform platform, int i) {
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("code", wxOpenid);
            object.put("openid", qqOpenid);
            object.put("accessToken", accessToken);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {

    }
}
