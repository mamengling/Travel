package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.VisaListViewAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.iactivityview.VisaFragmentView;
import com.jcool.dev.travel.persenter.VisaFragmentPresenter;
import com.jcool.dev.travel.ui.TravelViseActivity;
import com.jcool.dev.travel.ui.VisaCommitActivity;
import com.jcool.dev.travel.ui.VisaDataActivity;
import com.jcool.dev.travel.ui.VisaDefuiltActivity;
import com.jcool.dev.travel.ui.VisaHotActivity;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 签证
 */
public class VisaFragment extends BaseFragment implements View.OnClickListener, VisaFragmentView {
    private VisaFragmentPresenter mPresenter;
    @BindView(R.id.tv_visa_hot)
    TextView tv_visa_hot;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private VisaListViewAdapter mAdapter;
    private List<VisaInfoDtoList.VisaInfoDtoListBean> data = new ArrayList<>();

    public static VisaFragment newInstance() {

        Bundle args = new Bundle();

        VisaFragment fragment = new VisaFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_visa;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void initTools() {
        mPresenter = new VisaFragmentPresenter(this, getContext());
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.journeyVisaInfo();
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (data.get(i).getViewType() == 101) {
                    return 3;
                } else if (data.get(i).getViewType() == 102) {
                    return 3;
                } else {
                    return 1;
                }
            }
        });
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new VisaListViewAdapter(getContext());
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mPresenter.journeyVisaInfo();
    }

    @Override
    protected void setListener() {
        tv_visa_hot.setOnClickListener(this);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener<VisaInfoDtoList.VisaInfoDtoListBean>() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, VisaInfoDtoList.VisaInfoDtoListBean content) {
                switch (ClickType) {
                    case 0:
                        Intent intent = new Intent(getContext(), TravelViseActivity.class);
                        String keyName = "";
                        if (!TextUtils.isEmpty(content.getVisaSortName())) {
                            keyName = content.getVisaSortName().replace("热门", "");
                        }
                        intent.putExtra("keyName", keyName);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intentCommit=new Intent(getContext(),VisaCommitActivity.class);
                        startActivity(intentCommit);
                        break;
                    case 2:
                        Intent intentInfo = new Intent(getContext(), VisaDefuiltActivity.class);
                        intentInfo.putExtra("visaId", content.getId());
                        startActivity(intentInfo);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_visa_hot:
                Intent intent = new Intent(getContext(), VisaHotActivity.class);
                startActivity(intent);
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
    public void excuteSuccessCallBack(CallBackVo<List<VisaInfoDtoList>> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            data.clear();
            for (int i = 0; i < mCallBackVo.getData().size(); i++) {
                VisaInfoDtoList.VisaInfoDtoListBean item = new VisaInfoDtoList.VisaInfoDtoListBean();
                item.setViewType(101);
                item.setVisaSortName(mCallBackVo.getData().get(i).getVisaSortName());
                item.setId(mCallBackVo.getData().get(i).getId());
                data.add(item);
                data.addAll(mCallBackVo.getData().get(i).getVisaInfoDtoList());
            }
            VisaInfoDtoList.VisaInfoDtoListBean item = new VisaInfoDtoList.VisaInfoDtoListBean();
            item.setViewType(102);
            data.add(item);
            mAdapter.onReference(data);
        }
        refreshLayout.finishRefresh();
    }
}
