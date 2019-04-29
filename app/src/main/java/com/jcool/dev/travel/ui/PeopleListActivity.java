package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.PersonAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.iactivityview.PeopleListActivityView;
import com.jcool.dev.travel.persenter.PeopleListActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PeopleListActivity extends BaseActivity implements View.OnClickListener, PeopleListActivityView {
    private PeopleListActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private PersonAdapter mAdapter;
    private int index;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_people_list;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new PeopleListActivityPresenter(this, this);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.journeyPersonList(getToken());
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        mAdapter = new PersonAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener<PersonInfoBean>() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, PersonInfoBean content) {
                switch (ClickType) {
                    case 1://编辑
                        Intent intent = new Intent(PeopleListActivity.this, AddPersonActivity.class);
                        intent.putExtra("info", mAdapter.getList().get(position));
                        startActivity(intent);
                        break;
                    case 2://删除
                        index = position;
                        mPresenter.journeyPersonDelete(getToken(), mAdapter.getList().get(position).getId());
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.journeyPersonList(getToken());
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
        ToastUtils.showShortToast(mCallBackVo.getMsg());
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessPersonCallBack(CallBackVo<List<PersonInfoBean>> mCallBackVo) {
        if (mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mAdapter.onReference(mCallBackVo.getData());
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {
        ToastUtils.showShortToast("删除成功");
        mAdapter.getList().remove(index);
        mAdapter.notifyDataSetChanged();
    }
}
