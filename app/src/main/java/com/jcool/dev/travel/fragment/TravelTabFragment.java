package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.TravelListAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.iactivityview.HomeTabFragmentView;
import com.jcool.dev.travel.persenter.HomeTabFragmentPresenter;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.utils.ToastUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TravelTabFragment extends BaseFragment implements HomeTabFragmentView {
    private HomeTabFragmentPresenter mPresenter;
    private RecyclerView recycler_view_tab;
    private SmartRefreshLayout refreshLayout;
    private List<TravelBean.RecordsBean> mList = new ArrayList<>();
    private TravelListAdapter mAdapter;
    private String url;
    private String isDomestic;
    private String aroundCity;
    private String isGroup;
    private int intNumber = 0;
    private int intHandler = 101;

    public static TravelTabFragment newInstance(String url, String isDomestic, String isGroup, String aroundCity) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("isDomestic", isDomestic);
        args.putString("isGroup", isGroup);
        args.putString("aroundCity", aroundCity);
        TravelTabFragment fragment = new TravelTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tab_travel;
    }

    @Override
    protected void getExtra() {
        url = getArguments().getString("url", "");
        isDomestic = getArguments().getString("isDomestic", "");
        aroundCity = getArguments().getString("aroundCity", "");
        isGroup = getArguments().getString("isGroup", "");
    }

    @Override
    protected void initView(View view) {
        recycler_view_tab = view.findViewById(R.id.mRecyclerView);
        refreshLayout = view.findViewById(R.id.refreshLayout);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initTools() {
        mPresenter = new HomeTabFragmentPresenter(this, getContext());
        recycler_view_tab.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recycler_view_tab.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new TravelListAdapter(getContext(), R.layout.xml_item_travel_list, mList);
        //设置adapter
        recycler_view_tab.setAdapter(mAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                intNumber = 0;
                intHandler = 101;
                mPresenter.journeyGoodsSales(url);
            }
        });
        //打开或关闭加载
        mAdapter.setEnableLoadMore(true);
        mAdapter.openLoadAnimation();
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                intNumber += 1;
                intHandler = 102;
                mPresenter.journeyGoodsSales(url);
            }
        }, recycler_view_tab);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), TravelDefuiltActivity.class);
                intent.putExtra("travelId", mList.get(position).getId());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.journeyGoodsSales(url);
    }


    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("pageIndex", intNumber);
            object.put("pageSize", 10);
            object.put("isDomestic", isDomestic);//是否是境内游（ Y境内游，N境外游）
            object.put("aroundCity", aroundCity);//所属周边城市
            object.put("isGroup", isGroup);//是否是组团游（Y组团游玩，N自由行）
            object.put("queryStr", "");//检索关键字
            object.put("onLine", "");//是否在架
            object.put("orderBy", "");//排序属性(销量 sellNumber、价格 minPrice)
            object.put("descOrAsc", "");//升序、降序（desc\asc）

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
    public void excuteSuccessCallBack(CallBackVo<TravelBean> mCallBackVo) {
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
