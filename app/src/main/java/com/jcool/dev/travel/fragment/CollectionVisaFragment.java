package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.VisaListAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.bean.VisaBean;
import com.jcool.dev.travel.iactivityview.CollectionVisaFragmentView;
import com.jcool.dev.travel.persenter.CollectionVisaFragmentPresenter;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.ui.TravelViseActivity;
import com.jcool.dev.travel.ui.VisaDefuiltActivity;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.RecycleViewDivider;
import com.jcool.dev.travel.view.YRecycleview;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionVisaFragment extends BaseFragment implements CollectionVisaFragmentView {
    private CollectionVisaFragmentPresenter mPresenter;
    private int intNumber = 0;
    private int intHandler = 101;
    @BindView(R.id.yrecycleView)
    YRecycleview yrecycleView;
    private VisaListAdapter mAdapterData;
    private List<VisaBean.RecordsBean> mList = new ArrayList<>();

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_visa_collection;
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
        mAdapterData.setEnableLoadMore(false);
        yrecycleView.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intHandler = 101;
                intNumber = 0;
                mPresenter.getCollectionVisaList(getToken());
            }

            @Override
            public void onLoadMore() {
                intHandler = 102;
                intNumber += 1;
                mPresenter.getCollectionVisaList(getToken());
            }
        });


        mAdapterData.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), VisaDefuiltActivity.class);
                intent.putExtra("visaId", mAdapterData.getData().get((position - 1)).getId());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initTools() {
        mAdapterData = new VisaListAdapter(getContext(), R.layout.xml_item_visa_list, mList);
        yrecycleView.addItemDecoration(new RecycleViewDivider(
                getActivity(), LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(getActivity(), 1), getResources().getColor(R.color.white)));
        yrecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        yrecycleView.setAdapter(mAdapterData);
        mPresenter = new CollectionVisaFragmentPresenter(this, getContext());
    }

    @Override
    protected void initData() {
        mPresenter.getCollectionVisaList(getToken());
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
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<VisaBean> mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getRecords().size() > 0) {
                    mAdapterData.replaceData(mCallBackVo.getData().getRecords());
                }
                yrecycleView.setReFreshComplete();
                break;
            case 102:
                mAdapterData.addData(mCallBackVo.getData().getRecords());
                yrecycleView.setloadMoreComplete();
                break;
        }

    }
}
