package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.LineDefultListAdapter;
import com.jcool.dev.travel.adapter.TravelInfoBannerAdapter;
import com.jcool.dev.travel.adapter.TravelInfoLineAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.TravelInfoActivityView;
import com.jcool.dev.travel.persenter.TravelInfoActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.view.FixedGridView;
import com.jcool.dev.travel.view.FullyLinearLayoutManager;
import com.jcool.dev.travel.view.group.GroupItemDecoration;
import com.jcool.dev.travel.view.rollviewpage.OnItemClickListener;
import com.jcool.dev.travel.view.rollviewpage.RollPagerView;
import com.jcool.dev.travel.view.rollviewpage.hintview.ColorPointHintView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelInfoActivity extends BaseActivity implements View.OnClickListener, TravelInfoActivityView {
    private TravelInfoActivityPresenter mPresenter;
    @BindView(R.id.normal_view_pager)
    RollPagerView rollPagerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_back)
    TextView icon_title_back;
    @BindView(R.id.tv_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_number)
    TextView tv_number;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.fixedGridView)
    FixedGridView fixedGridView;
    @BindView(R.id.radiogroup_full)
    RadioGroup radiogroup_full;
    private String travelId;
    private int a, b, c;
    private TravelInfoLineAdapter mLineAdapter;
    private TravelInfoBannerAdapter mPagerAdapter;
    private LineDefultListAdapter imageAdapter;
    private List<TravelInfoBean.LinesBean.CharacteristicBean> data = new ArrayList<>();
    private FullyLinearLayoutManager manager;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        StatusBarUtils.setStatusTextColor(true, this);
        return R.layout.activity_travel_info;
    }

    @Override
    protected void getExtra() {
        travelId = getIntent().getStringExtra("travelId");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        icon_title_back.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.icon_lift_arrow), null, null, null);
        icon_right.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_right_arrow), null);
        mPresenter = new TravelInfoActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.journeyTravelInfo(travelId);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
        mPagerAdapter = new TravelInfoBannerAdapter(this);
        rollPagerView.setPlayDelay(3000);
        rollPagerView.setAdapter(mPagerAdapter);
        rollPagerView.setHintView(new ColorPointHintView(this, Color.parseColor("#5ac5b3"), Color.WHITE));
        rollPagerView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int positionChild) {

            }
        });
        manager = new FullyLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, TravelInfoBean.LinesBean.CharacteristicBean>());
        imageAdapter = new LineDefultListAdapter(this);
        mRecyclerView.setAdapter(imageAdapter);
        mLineAdapter = new TravelInfoLineAdapter(this);
        fixedGridView.setAdapter(mLineAdapter);
        radiogroup_full.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton0:
                        // 滚动到指定位置
//                        mRecyclerView.scrollToPosition(position);
                        // 平滑滚动到指定位置
                        mRecyclerView.smoothScrollToPosition(a);
                        break;
                    case R.id.radiobutton1:
                        mRecyclerView.smoothScrollToPosition(b);
                        break;
                    case R.id.radiobutton2:
                        mRecyclerView.smoothScrollToPosition(c);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.journeyTravelInfo(travelId);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
            case R.id.icon_right:
                break;
            case R.id.tv_collect:
                mRecyclerView.scrollToPosition(5);
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<TravelInfoBean> mCallBackVo, CallBackVo<TravelInfoBean.LinesBean> mCallBackVoLine, CallBackVo mCallCollect) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            tv_info.setText(mCallBackVo.getData().getName());
            tv_money.setText("¥" + mCallBackVo.getData().getMinPrice());
            tv_number.setText(mCallBackVo.getData().getDefaultSellNumber() + "人一起出游");
            if (mCallBackVo.getData().getLines() != null) {
                mLineAdapter.onReference(mCallBackVo.getData().getLines());
                if (mCallBackVo.getData().getLines().size() > 0) {
                    mPresenter.journeyTravelLineInfo(mCallBackVo.getData().getLines().get(0).getId());
                }
            }
            if (mCallBackVo.getData().getBanner() != null) {
                mPagerAdapter.onReference(mCallBackVo.getData().getBanner());
            }

        }
        if (mCallBackVoLine != null && mCallBackVoLine.getData() != null) {
            data.clear();
            TravelInfoBean.LinesBean.CharacteristicBean item = new TravelInfoBean.LinesBean.CharacteristicBean();
            item.setViewType(101);
            item.setTitle("产品特色");
            data.add(item);
            a = 0;
            if (mCallBackVoLine.getData().getCharacteristic() != null) {
                data.addAll(mCallBackVoLine.getData().getCharacteristic());
            }
            b = data.size();
            TravelInfoBean.LinesBean.CharacteristicBean item1 = new TravelInfoBean.LinesBean.CharacteristicBean();
            item1.setViewType(101);
            item1.setTitle("行程介绍");
            data.add(item1);
            if (mCallBackVoLine.getData().getSynopsis() != null) {
                data.addAll(mCallBackVoLine.getData().getSynopsis());
            }
            c = data.size();
            TravelInfoBean.LinesBean.CharacteristicBean item2 = new TravelInfoBean.LinesBean.CharacteristicBean();
            item2.setViewType(101);
            item2.setTitle("预订须知");
            data.add(item2);
            if (mCallBackVoLine.getData().getNotice() != null) {
                data.addAll(mCallBackVoLine.getData().getNotice());
            }

            imageAdapter.onReference(data);
        }
        refreshLayout.finishRefresh();
    }

}
