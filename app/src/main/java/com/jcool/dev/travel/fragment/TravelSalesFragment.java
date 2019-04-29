package com.jcool.dev.travel.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.TravelSalesAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GoodsBean;
import com.jcool.dev.travel.iactivityview.TravelSalesFragmentView;
import com.jcool.dev.travel.persenter.TravelSalesFragmentPresenter;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.RecycleViewDivider;
import com.jcool.dev.travel.view.YRecycleview;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelSalesFragment extends BaseFragment implements TravelSalesFragmentView {
    private TravelSalesFragmentPresenter mPresenter;
    @BindView(R.id.yrecycleView)
    YRecycleview yrecycleView;
    private String url;
    private String status;
    private TravelSalesAdapter mAdapter;
    private int intHandler = 101;
    private int intNumber = 0;

    public static TravelSalesFragment newInstance(String url, String status, String a, String b) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("status", status);
        TravelSalesFragment fragment = new TravelSalesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_sales;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void setListener() {
        yrecycleView.setRefreshing(true);
        yrecycleView.setReFreshEnabled(true);
        yrecycleView.setLoadMoreEnabled(true);
        yrecycleView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(getActivity(), 1), getResources().getColor(R.color.white)));
        yrecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TravelSalesAdapter(getContext());
        yrecycleView.setAdapter(mAdapter);
        yrecycleView.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumber = 0;
                mPresenter.journeyGoodsSales(url);
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumber += 1;
                mPresenter.journeyGoodsSales(url);
            }
        });

    }

    @Override
    protected void initTools() {
        url = getArguments().getString("url");
        status = getArguments().getString("status");
        mPresenter = new TravelSalesFragmentPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        mPresenter.journeyGoodsSales(url);
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject params = new JSONObject();
        try {
            params.put("pageSize", "10");
            params.put("pageIndex", intNumber);
            params.put("state", status);//状态【进行中-ING,将开始-FUTURE，已结束：ENDED】
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
        switch (intHandler) {
            case 101:

                yrecycleView.setReFreshComplete();
                break;
            case 102:

                yrecycleView.setloadMoreComplete();
                break;
        }
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<GoodsBean> mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getRecords().size() > 0) {
                    mAdapter.onReference(mCallBackVo.getData().getRecords());
                }
                yrecycleView.setReFreshComplete();
                break;
            case 102:
                mAdapter.addOnReference(mCallBackVo.getData().getRecords());
                yrecycleView.setloadMoreComplete();
                break;
        }

    }
}
