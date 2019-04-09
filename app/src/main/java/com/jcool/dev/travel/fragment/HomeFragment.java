package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.HomeGoodsSalesAdapter;
import com.jcool.dev.travel.adapter.ViewPagerFragmentAdapter;
import com.jcool.dev.travel.adapter.ViewpagerAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.BannerBean;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GoodsBean;
import com.jcool.dev.travel.bean.InfoColumn;
import com.jcool.dev.travel.iactivityview.HomeFragmentView;
import com.jcool.dev.travel.persenter.HomeFragmentPresenter;
import com.jcool.dev.travel.ui.CityPickerActivity;
import com.jcool.dev.travel.ui.CompanyVipActivity;
import com.jcool.dev.travel.ui.PersonVipActivity;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.ui.TravelListActivity;
import com.jcool.dev.travel.ui.TravelViseActivity;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.view.FixedGridView;
import com.jcool.dev.travel.view.rollviewpage.OnItemClickListener;
import com.jcool.dev.travel.view.rollviewpage.RollPagerView;
import com.jcool.dev.travel.view.rollviewpage.hintview.ColorPointHintView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements HomeFragmentView, View.OnClickListener {
    private HomeFragmentPresenter mPresenter;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private TabLayout tlMainTabtop;
    private AppBarLayout appbar;
    private ViewPager viewpager;
    private NestedScrollView nsv;
    private RollPagerView rollPagerView;
    private SmartRefreshLayout refreshLayout;
    private HomeGoodsSalesAdapter mAdapterGoods;
    private FixedGridView fixedGridView;
    private ViewpagerAdapter mPagerAdapter;
    private List<InfoColumn> infoColumnList;
    private List<Fragment> fragmentList;
    private List<GoodsBean.RecordsBean> goodsList = new ArrayList<>();
    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;
    private String tabTitle[] = {"出境游", "国内游", "周边游"};

    private TextView tv_city;
    private TextView icon_message;
    private TextView icon_phone;


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        rollPagerView = view.findViewById(R.id.normal_view_pager);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        toolbar = view.findViewById(R.id.toolbar);
        collapsingToolbar = view.findViewById(R.id.collapsingToolbar);
        tlMainTabtop = view.findViewById(R.id.tl_main_tabtop);
        appbar = view.findViewById(R.id.appbar);
        viewpager = view.findViewById(R.id.viewpager);
        nsv = view.findViewById(R.id.nsv);
        fixedGridView = view.findViewById(R.id.fixedGridView);
        tv_city = view.findViewById(R.id.tv_title);
        icon_message = view.findViewById(R.id.icon_right);
        icon_phone = view.findViewById(R.id.icon_back);
        view.findViewById(R.id.btn_go_out).setOnClickListener(this);
        view.findViewById(R.id.btn_search).setOnClickListener(this);
        view.findViewById(R.id.btn_vise).setOnClickListener(this);
        view.findViewById(R.id.btn_all_around).setOnClickListener(this);
        view.findViewById(R.id.btn_in).setOnClickListener(this);
        view.findViewById(R.id.btn_mine).setOnClickListener(this);
        view.findViewById(R.id.btn_company).setOnClickListener(this);
        view.findViewById(R.id.btn_user).setOnClickListener(this);

        getFragmentData();
    }

    @Override
    protected void initTools() {
        if (isAdded()) {
            tv_city.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_home_arrow_down), null);
        }
        tv_city.setText("济南");
        mPresenter = new HomeFragmentPresenter(this, getContext());
        mPagerAdapter = new ViewpagerAdapter(getContext());
        rollPagerView.setPlayDelay(3000);
        rollPagerView.setAdapter(mPagerAdapter);
        rollPagerView.setHintView(new ColorPointHintView(getContext(), Color.parseColor("#5ac5b3"), Color.WHITE));
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int positionChild) {

            }
        });

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh();
            }
        });
        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderPulling(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }

            @Override
            public void onHeaderReleasing(RefreshHeader header, float percent, int offset, int bottomHeight, int extendHeight) {
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            //verticalOffset是当前appbarLayout的高度与最开始appbarlayout高度的差，向上滑动的话是负数
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                //Y轴偏移量
                float scrollY = Math.abs(verticalOffset);
                //变化率 Toolbar与header高度的差值
                float headerBarOffsetY = appBarLayout.getHeight() - toolbar.getHeight();
                float offset = 1 - Math.max((headerBarOffsetY - scrollY) / headerBarOffsetY, 0f);
                //Toolbar背景色透明度
                toolbar.setBackgroundColor(Color.argb((int) (offset * 255), 253, 214, 85));
            }
        });
        //设置还没收缩时状态下字体颜色
        collapsingToolbar.setExpandedTitleColor(Color.parseColor("#00ffffff"));
        //设置收缩后Toolbar上字体的
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        mAdapterGoods = new HomeGoodsSalesAdapter(getActivity(), goodsList);
        fixedGridView.setAdapter(mAdapterGoods);

        fixedGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), TravelDefuiltActivity.class);
                intent.putExtra("travelId", goodsList.get(position).getId());
                getContext().startActivity(intent);
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.getBanner();
        mPresenter.journeyGoodsSales();
    }


    @Override
    protected void setListener() {
        tv_city.setOnClickListener(this);
        icon_message.setOnClickListener(this);
        icon_phone.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_title:
                Intent intent = new Intent(getContext(), CityPickerActivity.class);
                startActivity(intent);
                break;
            case R.id.icon_right:
                // TODO: 2019/4/3 消息
                break;
            case R.id.icon_back:
                // TODO: 2019/4/3 打电话
                break;
            case R.id.btn_go_out:
                Intent intent1 = new Intent(getContext(), TravelListActivity.class);
                intent1.putExtra("number", 1);
                startActivity(intent1);
                break;
            case R.id.btn_search:
                break;
            case R.id.btn_vise:
                Intent intentV = new Intent(getContext(), TravelViseActivity.class);
                startActivity(intentV);
                break;
            case R.id.btn_all_around:
                Intent intent2 = new Intent(getContext(), TravelListActivity.class);
                intent2.putExtra("number", 3);
                startActivity(intent2);
                break;
            case R.id.btn_in:
                Intent intent3 = new Intent(getContext(), TravelListActivity.class);
                intent3.putExtra("number", 2);
                startActivity(intent3);
                break;
            case R.id.btn_mine:
                Intent intent4 = new Intent(getContext(), TravelListActivity.class);
                intent4.putExtra("number", 4);
                startActivity(intent4);
                break;
            case R.id.btn_company:
                Intent intent5 = new Intent(getContext(), CompanyVipActivity.class);
                startActivity(intent5);
                break;
            case R.id.btn_user:
                Intent intent6 = new Intent(getContext(), PersonVipActivity.class);
                startActivity(intent6);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case AppConfigStatic.CITY:
                if (data.getStringExtra("city").contains("市") || data.getStringExtra("city").contains("县")) {
                    tv_city.setText(data.getStringExtra("city"));
                } else {
                    tv_city.setText(data.getStringExtra("city") + "市");
                }

                String city = data.getStringExtra("city") + "市";
                String location = city;
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject params = new JSONObject();
        try {
            params.put("pageSize", "3");
            params.put("pageIndex", "0");
            params.put("state", "");//状态【进行中-ING,将开始-FUTURE，已结束：ENDED】
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<List<BannerBean>> mCallBackVo, CallBackVo<GoodsBean> goodsCallBackBo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mPagerAdapter.onReference(mCallBackVo.getData());
        }
        if (goodsCallBackBo != null && goodsCallBackBo.getData() != null && goodsCallBackBo.getData().getRecords() != null && goodsCallBackBo.getData().getRecords().size() > 0) {
            goodsList.clear();
            goodsList.addAll(goodsCallBackBo.getData().getRecords());
            mAdapterGoods.notifyDataSetChanged();
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
        fragmentList.add(HomeTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "N", ""));
        fragmentList.add(HomeTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "Y", ""));
        fragmentList.add(HomeTabFragment.newInstance(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY, "", "1005"));
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
