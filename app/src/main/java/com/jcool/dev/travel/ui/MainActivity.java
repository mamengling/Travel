package com.jcool.dev.travel.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationListener;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.MyFragmentAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.fragment.DestinationFragment;
import com.jcool.dev.travel.fragment.HomeFragment;
import com.jcool.dev.travel.fragment.UserFragment;
import com.jcool.dev.travel.fragment.VisaFragment;
import com.jcool.dev.travel.iactivityview.UserInfoGetView;
import com.jcool.dev.travel.persenter.UserInfoGetPresenter;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.AppTravelLocation;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.log.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener, AMapLocationListener, UserInfoGetView {
    private UserInfoGetPresenter mPresenter;
    //设置要用到的变量的引用
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList;
    private BottomNavigationView navigation;
    private MyFragmentAdapter myFragmentAdapter;
    private static final int BAIDU_READ_PHONE_STATE = 100;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setTranslucent(this, 0);
        return R.layout.activity_main;
    }

    @Override
    protected void getExtra() {

    }

    /**
     * 获取StatusBar颜色，默认黑色 当前为主题色
     *
     * @return
     */
    protected @ColorInt
    int getStatusBarColor() {
        return Color.parseColor("#FFE25A");
    }

    @Override
    protected void initView() {
        /**
         * 初始化tab
         */
        navigation = findViewById(R.id.navigation);

        /**
         * 初始化viewPager
         */
        viewPager = findViewById(R.id.viewPager);
    }

    @Override
    protected void initTools() {
        mPresenter = new UserInfoGetPresenter(this, this);
        //判断是否为android6.0系统版本，如果是，需要动态添加权限
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            AppTravelLocation.getInstance(MainActivity.this).InitLocation(this);
        }
    }

    @Override
    protected void initData() {
        if (isLogin() && getToken() != null)
            mPresenter.getUserInfoToken(getToken());
    }

    @Override
    protected void setListener() {
        viewPager.addOnPageChangeListener(this);
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_dashboard:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_notifications:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_main:
                        viewPager.setCurrentItem(3);
                        return true;
                }
                return false;
            }
        });
        /**
         * 初始化fragment列表
         */
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.newInstance());
        fragmentList.add(DestinationFragment.newInstance());
        fragmentList.add(VisaFragment.newInstance());
        fragmentList.add(UserFragment.newInstance());
        /**
         * 初始化viewPager适配器
         */
        myFragmentAdapter = new MyFragmentAdapter(getSupportFragmentManager(), fragmentList);
        /**
         * 注入适配器
         */
        viewPager.setAdapter(myFragmentAdapter);
    }


    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        navigation.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                AppConfigStatic.APP_AMAP_LATITUDE = aMapLocation.getLatitude();//获取纬度
                AppConfigStatic.APP_AMAP_LONGITUDE = aMapLocation.getLongitude();//获取经度
                AppConfigStatic.APP_AMAP_CITY = aMapLocation.getCity();//获取城市
                LogUtil.i("LOCATION", "\n*****\n當前经度：" + aMapLocation.getLongitude() + "\n当前维度：" + aMapLocation.getLatitude() + "\n当前城市：" + aMapLocation.getCity() + "\n城市代码：" + aMapLocation.getCityCode() + "\n******");
            } else {

            }
        }
    }

    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
//            Toast.makeText(getApplicationContext(), "没有权限,请手动开启定位权限", Toast.LENGTH_SHORT).show();
            // 申请一个（或多个）权限，并提供用于回调返回的获取码（用户定义）
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE}, BAIDU_READ_PHONE_STATE);
        } else {
            AppTravelLocation.getInstance(MainActivity.this).InitLocation(this);
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("", "");
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
        ToastUtils.showShortToast("失败：" + mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessUserCallBack(CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            setUserInfo(mCallBackVo.getData());
        }
    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {

    }
}
