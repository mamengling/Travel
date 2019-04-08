package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.VisaInfoViewAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.bean.VisaInfoBeanView;
import com.jcool.dev.travel.iactivityview.VisaInfoActivityView;
import com.jcool.dev.travel.persenter.VisaInfoActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.view.group.GroupItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaDefuiltActivity extends BaseActivity implements View.OnClickListener, VisaInfoActivityView {
    VisaInfoActivityPresenter mPresenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String visaId;
    private VisaInfoViewAdapter mAdapter;
    private List<VisaInfoBeanView> data = new ArrayList<>();
    private int a, b, c, d;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_visa_defulit;
    }

    @Override
    protected void getExtra() {
        visaId = getIntent().getStringExtra("visaId");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new VisaInfoActivityPresenter(this, this);
        icon_title.setText("签证详情");
        icon_right.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.drawable.ic_home_black_24dp), null);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.journeyVisaInfo(visaId);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, TravelInfoBean.LinesBean.CharacteristicBean>());
        mAdapter = new VisaInfoViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.journeyVisaInfo(visaId);
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
            case R.id.icon_right:
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

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<VisaInfoBean> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            data.clear();
            VisaInfoBeanView infoBeanView = new VisaInfoBeanView();
            infoBeanView.setViewType(101);
            infoBeanView.setmCallBackVo(mCallBackVo);
            data.add(infoBeanView);
        }

//        if (mCallBackVoLine != null && mCallBackVoLine.getData() != null) {
        VisaInfoBeanView itemBean = new VisaInfoBeanView();
        itemBean.setViewType(102);
        itemBean.setTitle("所需材料");
        data.add(itemBean);
        a = 1;
//        if (mCallBackVoLine.getData().getCharacteristic() != null) {
//            for (int i = 0; i < mCallBackVoLine.getData().getCharacteristic().size(); i++) {
//                TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
//                itemBeanChara.setViewType(103);
//                itemBeanChara.setItemImage(mCallBackVoLine.getData().getCharacteristic().get(i));
//                data.add(itemBeanChara);
//            }
//        }
        b = data.size() + 1;
        VisaInfoBeanView itemBean1 = new VisaInfoBeanView();
        TravelInfoBean.LinesBean.CharacteristicBean item1 = new TravelInfoBean.LinesBean.CharacteristicBean();
        itemBean1.setViewType(102);
        itemBean1.setTitle("产品说明");
        data.add(itemBean1);
//        if (mCallBackVoLine.getData().getSynopsis() != null) {
//            for (int i = 0; i < mCallBackVoLine.getData().getSynopsis().size(); i++) {
//                TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
//                itemBeanChara.setViewType(103);
//                itemBeanChara.setItemImage(mCallBackVoLine.getData().getSynopsis().get(i));
//                data.add(itemBeanChara);
//            }
//        }
        c = data.size() + 1;
        VisaInfoBeanView itemBean2 = new VisaInfoBeanView();
        TravelInfoBean.LinesBean.CharacteristicBean item2 = new TravelInfoBean.LinesBean.CharacteristicBean();
        itemBean2.setViewType(102);
        itemBean2.setTitle("购买须知");
        data.add(itemBean2);

        d = data.size() + 1;
        VisaInfoBeanView itemBean3 = new VisaInfoBeanView();
        TravelInfoBean.LinesBean.CharacteristicBean item3 = new TravelInfoBean.LinesBean.CharacteristicBean();
        itemBean3.setViewType(102);
        itemBean3.setTitle("产品详情");
        data.add(itemBean3);
//        if (mCallBackVoLine.getData().getNotice() != null) {
//            for (int i = 0; i < mCallBackVoLine.getData().getNotice().size(); i++) {
//                TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
//                itemBeanChara.setViewType(103);
//                itemBeanChara.setItemImage(mCallBackVoLine.getData().getNotice().get(i));
//                data.add(itemBeanChara);
//            }
//        }

//        }
        mAdapter.onReference(data);
        refreshLayout.finishRefresh();
    }
}
