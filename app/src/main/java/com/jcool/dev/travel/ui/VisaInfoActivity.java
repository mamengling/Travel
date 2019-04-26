package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.iactivityview.VisaInfoActivityView;
import com.jcool.dev.travel.persenter.VisaInfoActivityPresenter;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaInfoActivity extends BaseActivity implements View.OnClickListener, VisaInfoActivityView {
    VisaInfoActivityPresenter mPresenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_persone)
    TextView tv_persone;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_goods_title)
    TextView tv_goods_title;
    @BindView(R.id.tv_goods_day)
    TextView tv_goods_day;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv_place)
    TextView tv_place;
    @BindView(R.id.tv_days)
    TextView tv_days;
    @BindView(R.id.tv_stay_days)
    TextView tv_stay_days;
    @BindView(R.id.tv_faces)
    TextView tv_faces;
    @BindView(R.id.tv_entry_count)
    TextView tv_entry_count;
    @BindView(R.id.image_goods)
    ImageView image_goods;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private String visaId;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_visa_info;
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
            ImageLoaderUtils.showImageViewToRoundedCorners(this, mCallBackVo.getData().getVisaImage(), image_goods, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
            tv_goods_title.setText(mCallBackVo.getData().getVisaName());
            tv_money.setText("¥" + mCallBackVo.getData().getVisaPrice());
            tv_persone.setText("已办理" + mCallBackVo.getData().getSalesVolume() + "人");
            tv_time.setText(mCallBackVo.getData().getHandleTime());
            tv_place.setText(mCallBackVo.getData().getCommonPlace());
            tv_days.setText(mCallBackVo.getData().getValidityDate() + "天");
            tv_stay_days.setText(mCallBackVo.getData().getStayDays() + "天");
            tv_entry_count.setText(mCallBackVo.getData().getEntryCount() + "次");
            tv_faces.setText(TextUtils.equals("02", mCallBackVo.getData().getIsMeet()) ? "不需要面试" : "需要面试");
        }
    }

    @Override
    public void excuteSuccessCollectCallBack(CallBackVo<String> mCallBackVo) {

    }

    @Override
    public void excuteSuccessAddCollectCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessDelCollectCallBack(CallBackVo mCallBackVo) {

    }
}
