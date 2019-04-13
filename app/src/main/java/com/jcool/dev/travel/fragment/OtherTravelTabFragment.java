package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.OrderTravelListAdapter;
import com.jcool.dev.travel.adapter.TravelListAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderTravelInfoBean;
import com.jcool.dev.travel.iactivityview.OtherTravelTabFragmentView;
import com.jcool.dev.travel.persenter.OtherTravelTabFragmentPresenter;
import com.jcool.dev.travel.ui.TravelOrderDetailActivity;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.RecycleViewDivider;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OtherTravelTabFragment extends BaseFragment implements OtherTravelTabFragmentView {
    private OtherTravelTabFragmentPresenter mPresenter;
    private RecyclerView recycler_view_tab;
    private SmartRefreshLayout refreshLayout;
    private List<OrderTravelInfoBean.RecordsBean> mList = new ArrayList<>();
    private String url;
    private String orderStatus;
    private int intNumber = 0;
    private int intHandler = 101;
    private OrderTravelListAdapter mAdapter;

    public static OtherTravelTabFragment newInstance(String url, String orderStatus) {
        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("orderStatus", orderStatus);
        OtherTravelTabFragment fragment = new OtherTravelTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fargment_travel_order;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        recycler_view_tab = view.findViewById(R.id.mRecyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);
    }

    @Override
    protected void setListener() {
        url = getArguments().getString("url");
        orderStatus = getArguments().getString("orderStatus");
        //水平分割线
        recycler_view_tab.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(getActivity(), 10), getResources().getColor(R.color.root_bg_color1)));

        recycler_view_tab.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new OrderTravelListAdapter(getContext(), R.layout.xml_item_order_travel_list, mList);
        //设置adapter
        recycler_view_tab.setAdapter(mAdapter);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                intNumber = 0;
                intHandler = 101;
                mPresenter.getOrderList(url, getToken());
            }
        });
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                intNumber += 1;
                intHandler = 102;
                mPresenter.getOrderList(url, getToken());
            }
        }, recycler_view_tab);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), TravelOrderDetailActivity.class);
                intent.putExtra("orderId", mAdapter.getData().get(position).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initTools() {
        mPresenter = new OtherTravelTabFragmentPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        mPresenter.getOrderList(url, getToken());
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("state", orderStatus);
            object.put("pageIndex", intNumber);
            object.put("pageSize", 10);
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
        switch (intHandler) {
            case 101:
                refreshLayout.finishRefresh();
                break;
            case 102:
                mAdapter.setEnableLoadMore(true);
                mAdapter.loadMoreComplete();
                break;
        }
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<OrderTravelInfoBean> mCallBackVo) {
//        switch (intHandler) {
//            case 101:
//                if (mCallBackVo.getData() != null && mCallBackVo.getData().getRecords()!=null&&mCallBackVo.getData().getRecords().size() > 0) {
//                    mAdapter.onReference(mCallBackVo.getData().getRecords());
//                }
//                refreshLayout.finishRefresh();
//                break;
//            case 102:
//                if (mCallBackVo.getData() != null && mCallBackVo.getData().getRecords()!=null&&mCallBackVo.getData().getRecords().size() > 0) {
//                    mAdapter.addOnReference(mCallBackVo.getData().getRecords());
//                }
////                refreshLayout.finishLoadmore();
//                break;
//        }
        switch (intHandler) {
            case 101:
                mAdapter.replaceData(mCallBackVo.getData().getRecords());
                refreshLayout.finishRefresh();
                if (mAdapter.getData().size() < mCallBackVo.getData().getTotal()) {
                    mAdapter.setEnableLoadMore(true);
                    mAdapter.openLoadAnimation();
                    mAdapter.loadMoreComplete();
                } else {
                    ToastUtils.showShortToast("数据全部加载完毕");
                    mAdapter.setEnableLoadMore(false);
                }
                break;
            case 102:
                if (mAdapter.getData().size() < mCallBackVo.getData().getTotal()) {
                    mAdapter.setEnableLoadMore(true);
                    mAdapter.addData(mCallBackVo.getData().getRecords());
                    mAdapter.loadMoreComplete();
                } else {
                    ToastUtils.showShortToast("数据全部加载完毕");
                    mAdapter.setEnableLoadMore(false);
                }
                break;
        }
    }
}
