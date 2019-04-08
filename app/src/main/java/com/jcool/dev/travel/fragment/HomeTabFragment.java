package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.HomeTabGoodsAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.iactivityview.HomeTabFragmentView;
import com.jcool.dev.travel.persenter.HomeTabFragmentPresenter;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.view.rollviewpage.OnItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeTabFragment extends BaseFragment implements HomeTabFragmentView {
    private HomeTabFragmentPresenter mPresenter;
    private RecyclerView recycler_view_tab;
    private List<TravelBean.RecordsBean> mList = new ArrayList<>();
    private HomeTabGoodsAdapter mAdapter;
    private String url;
    private String isDomestic;
    private String aroundCity;

    public static HomeTabFragment newInstance(String url, String isDomestic, String aroundCity) {

        Bundle args = new Bundle();
        args.putString("url", url);
        args.putString("isDomestic", isDomestic);
        args.putString("aroundCity", aroundCity);
        HomeTabFragment fragment = new HomeTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_tab_home;
    }

    @Override
    protected void getExtra() {
        url = getArguments().getString("url", "");
        isDomestic = getArguments().getString("isDomestic", "");
        aroundCity = getArguments().getString("aroundCity", "");
    }
    @Override
    protected void initView(View view) {
        recycler_view_tab = view.findViewById(R.id.mRecyclerView);

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initTools() {
        mPresenter = new HomeTabFragmentPresenter(this, getContext());

        mList.clear();
        recycler_view_tab.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recycler_view_tab.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new HomeTabGoodsAdapter(getActivity());
        //设置adapter
        recycler_view_tab.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
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
            object.put("pageIndex", "0");
            object.put("pageSize", "5");
            object.put("queryStr", "");//检索关键字
            object.put("isGroup", "");//是否是组团游（Y组团游玩，N自由行）
            object.put("isDomestic", isDomestic);//是否是境内游（ Y境内游，N境外游）
            object.put("aroundCity", aroundCity);//所属周边城市
            object.put("onLine", "");//目的地
            object.put("onLine", "");//是否在架
            object.put("orderBy", "sellNumber");//排序属性(销量 sellNumber、价格 minPrice)
            object.put("descOrAsc", "asc");//升序、降序（desc\asc）
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
        mList.clear();
        mList.addAll(mCallBackVo.getData().getRecords());
        mAdapter.onReference(mList);
    }
}
