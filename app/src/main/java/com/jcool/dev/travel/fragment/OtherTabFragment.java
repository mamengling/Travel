package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.OrderOtherAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoOthBean;
import com.jcool.dev.travel.iactivityview.OtherTabFragmentView;
import com.jcool.dev.travel.persenter.OtherTabFragmentPresenter;
import com.jcool.dev.travel.ui.VisaOrderDetailActivity;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherTabFragment extends BaseFragment implements OtherTabFragmentView {
    private OtherTabFragmentPresenter mPresenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private OrderOtherAdapter mAdapter;
    private String url;
    private String orderStatus;

    public static Fragment newInstance(String url, String orderStatus) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("orderStatus", orderStatus);
        OtherTabFragment fragment = new OtherTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tab_other_order;
    }

    @Override
    protected void getExtra() {
        url = getArguments().getString("url");
        orderStatus = getArguments().getString("orderStatus");
    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void setListener() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //水平分割线
        recyclerView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(getActivity(), 10), getResources().getColor(R.color.root_bg_color1)));
        mAdapter = new OrderOtherAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener<OrderInfoOthBean>() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, OrderInfoOthBean content) {
                Intent intent = new Intent(getContext(), VisaOrderDetailActivity.class);
                intent.putExtra("orderId", content.getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initTools() {
        mPresenter = new OtherTabFragmentPresenter(this, getContext());
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

                refreshLayout.finishRefresh();
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getOrderList(url, getToken());
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("orderStatus", orderStatus);
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

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<List<OrderInfoOthBean>> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mAdapter.onReference(mCallBackVo.getData());
        }
    }
}
