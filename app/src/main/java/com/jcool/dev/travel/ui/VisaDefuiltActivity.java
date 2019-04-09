package com.jcool.dev.travel.ui;

import android.content.Intent;
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
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
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
    private VisaInfoActivityPresenter mPresenter;
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
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, Object content) {
                switch (ClickType) {
                    case 1:
                        if (positionChild == 0) {
                            mRecyclerView.smoothScrollToPosition(a);
                        } else if (positionChild == 1) {
                            mRecyclerView.smoothScrollToPosition(b);
                        } else if (positionChild == 2) {
                            mRecyclerView.smoothScrollToPosition(c);
                        } else {
                            mRecyclerView.smoothScrollToPosition(d);
                        }
                        break;
                    case 2:
                        if (positionChild == 0) {
                            Intent intent = new Intent(VisaDefuiltActivity.this, VisaDataActivity.class);
                            intent.putParcelableArrayListExtra("list", mAdapter.getList().get(position).getmCallBackVo().getData().getWorkingDataList());
                            startActivity(intent);
                        } else if (positionChild == 1) {
                            Intent intent = new Intent(VisaDefuiltActivity.this, VisaDataActivity.class);
                            intent.putParcelableArrayListExtra("list", mAdapter.getList().get(position).getmCallBackVo().getData().getFreeDataList());
                            startActivity(intent);
                        } else if (positionChild == 2) {

                        } else if (positionChild == 3) {

                        } else {

                        }
                        break;
                }
            }
        });
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
        refreshLayout.finishRefresh();
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

        b = data.size() + 1;
        VisaInfoBeanView itemBean1 = new VisaInfoBeanView();
        itemBean1.setViewType(102);
        itemBean1.setTitle("产品说明");
        itemBean1.setImageUrl(mCallBackVo.getData().getVisaExplainImage());
        data.add(itemBean1);

        VisaInfoBeanView itemBean11 = new VisaInfoBeanView();
        itemBean11.setViewType(103);
        itemBean11.setImageUrl(mCallBackVo.getData().getVisaExplainImage());
        data.add(itemBean11);

        c = data.size() + 1;
        VisaInfoBeanView itemBean2 = new VisaInfoBeanView();
        itemBean2.setViewType(102);
        itemBean2.setTitle("购买须知");
        data.add(itemBean2);

        VisaInfoBeanView itemBean21 = new VisaInfoBeanView();
        itemBean21.setViewType(103);
        itemBean21.setImageUrl(mCallBackVo.getData().getVisaNoticeImage());
        data.add(itemBean21);

        d = data.size() + 1;
        VisaInfoBeanView itemBean3 = new VisaInfoBeanView();
        itemBean3.setViewType(102);
        itemBean3.setTitle("产品详情");
        itemBean3.setImageUrl(mCallBackVo.getData().getVisaDeatilImage());
        data.add(itemBean3);

        VisaInfoBeanView itemBean31 = new VisaInfoBeanView();
        itemBean31.setViewType(103);
        itemBean31.setImageUrl(mCallBackVo.getData().getVisaDeatilImage());
        data.add(itemBean31);
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
