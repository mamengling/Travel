package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.TravelInfoViewAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.TravelInfoBeanView;
import com.jcool.dev.travel.iactivityview.TravelInfoActivityView;
import com.jcool.dev.travel.persenter.TravelInfoActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.group.GroupItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelDefuiltActivity extends BaseActivity implements View.OnClickListener, TravelInfoActivityView {
    private TravelInfoActivityPresenter mPresenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_back)
    TextView icon_title_back;
    @BindView(R.id.tv_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String travelId;
    private int a, b, c;
    private TravelInfoViewAdapter mAdapter;
    private List<TravelInfoBeanView> data = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true,this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_travel_defulit;
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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, TravelInfoBean.LinesBean.CharacteristicBean>());
        mAdapter = new TravelInfoViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, Object content) {
                switch (ClickType) {
                    case 1:
                        if (positionChild == 0) {
                            mRecyclerView.smoothScrollToPosition(a);
                        } else if (positionChild == 1) {
                            mRecyclerView.smoothScrollToPosition(b);
                        } else {
                            mRecyclerView.smoothScrollToPosition(c);
                        }
                        break;
                    case 2:
                        Intent intent = new Intent(TravelDefuiltActivity.this, CalendarActivity.class);
                        intent.putExtra("", "");
                        intent.putExtra("", "");
                        startActivity(intent);
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
                mPresenter.journeyTravelCollect();
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object=new JSONObject();
        try {
            object.put("goodsId",travelId);
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
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<TravelInfoBean> mCallBackVo, CallBackVo<TravelInfoBean.LinesBean> mCallBackVoLine, CallBackVo mCallCollect) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            data.clear();
            TravelInfoBeanView itemBean = new TravelInfoBeanView();
            itemBean.setViewType(101);
            itemBean.setmCallBackVo(mCallBackVo);
            data.add(itemBean);
            if (mCallBackVo.getData().getLines() != null) {
                if (mCallBackVo.getData().getLines().size() > 0) {
                    mPresenter.journeyTravelLineInfo(mCallBackVo.getData().getLines().get(0).getId());
                }
            }
        }
        if (mCallBackVoLine != null && mCallBackVoLine.getData() != null) {

            TravelInfoBeanView itemBean = new TravelInfoBeanView();
            TravelInfoBean.LinesBean.CharacteristicBean item = new TravelInfoBean.LinesBean.CharacteristicBean();
            itemBean.setViewType(102);
            item.setTitle("产品特色");
            itemBean.setItemImage(item);
            data.add(itemBean);
            a = 1;
            if (mCallBackVoLine.getData().getCharacteristic() != null) {
                for (int i = 0; i < mCallBackVoLine.getData().getCharacteristic().size(); i++) {
                    TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
                    itemBeanChara.setViewType(103);
                    itemBeanChara.setItemImage(mCallBackVoLine.getData().getCharacteristic().get(i));
                    data.add(itemBeanChara);
                }
            }
            b = data.size() + 1;
            TravelInfoBeanView itemBean1 = new TravelInfoBeanView();
            TravelInfoBean.LinesBean.CharacteristicBean item1 = new TravelInfoBean.LinesBean.CharacteristicBean();
            itemBean1.setViewType(102);
            item1.setTitle("行程介绍");
            itemBean1.setItemImage(item1);
            data.add(itemBean1);
            if (mCallBackVoLine.getData().getSynopsis() != null) {
                for (int i = 0; i < mCallBackVoLine.getData().getSynopsis().size(); i++) {
                    TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
                    itemBeanChara.setViewType(103);
                    itemBeanChara.setItemImage(mCallBackVoLine.getData().getSynopsis().get(i));
                    data.add(itemBeanChara);
                }
            }
            c = data.size() + 1;
            TravelInfoBeanView itemBean2 = new TravelInfoBeanView();
            TravelInfoBean.LinesBean.CharacteristicBean item2 = new TravelInfoBean.LinesBean.CharacteristicBean();
            itemBean2.setViewType(102);
            item2.setTitle("预订须知");
            itemBean2.setItemImage(item2);
            data.add(itemBean2);
            if (mCallBackVoLine.getData().getNotice() != null) {
                for (int i = 0; i < mCallBackVoLine.getData().getNotice().size(); i++) {
                    TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
                    itemBeanChara.setViewType(103);
                    itemBeanChara.setItemImage(mCallBackVoLine.getData().getNotice().get(i));
                    data.add(itemBeanChara);
                }
            }

        }
        mAdapter.onReference(data);
        refreshLayout.finishRefresh();
    }
}
