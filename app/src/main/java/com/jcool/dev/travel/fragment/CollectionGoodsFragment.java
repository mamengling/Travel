package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.TravelListAdapter;
import com.jcool.dev.travel.adapter.VisaListAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.bean.VisaBean;
import com.jcool.dev.travel.iactivityview.CollectionGoodsFragmentView;
import com.jcool.dev.travel.persenter.CollectionGoodsFragmentPresenter;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.RecycleViewDivider;
import com.jcool.dev.travel.view.YRecycleview;
import com.scwang.smartrefresh.layout.util.DensityUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionGoodsFragment extends BaseFragment implements CollectionGoodsFragmentView {
    private CollectionGoodsFragmentPresenter mPresenter;
    private int intNumber = 0;
    private int intHandler = 101;
    @BindView(R.id.yrecycleView)
    YRecycleview yrecycleView;
    private List<TravelBean.RecordsBean> mList = new ArrayList<>();
    private TravelListAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_travel_collection;
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
        yrecycleView.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumber = 0;
                mPresenter.getCollectionTravelList(getToken());
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumber += 1;
                mPresenter.getCollectionTravelList(getToken());
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), TravelDefuiltActivity.class);

                intent.putExtra("travelId", mAdapter.getData().get((position - 1)).getId());
                startActivity(intent);
            }
        });


    }

    @Override
    protected void initTools() {
        mAdapter = new TravelListAdapter(getContext(), R.layout.xml_item_visa_list, mList);
        yrecycleView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(getActivity(), 1), getResources().getColor(R.color.white)));
        yrecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yrecycleView.setAdapter(mAdapter);
        mPresenter = new CollectionGoodsFragmentPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        mPresenter.getCollectionTravelList(getToken());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<TravelBean> mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getRecords().size() > 0) {
                    mAdapter.replaceData(mCallBackVo.getData().getRecords());
                }
                yrecycleView.setReFreshComplete();
                break;
            case 102:
                mAdapter.addData(mCallBackVo.getData().getRecords());
                yrecycleView.setloadMoreComplete();
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("pageIndex", intNumber);
            object.put("pageSize", "10");
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
}
