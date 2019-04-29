package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.SearchKeyAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CityBeanHot;
import com.jcool.dev.travel.iactivityview.SearchKeyActivityView;
import com.jcool.dev.travel.persenter.SearchKeyActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.RecycleViewDivider;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchKeyActivity extends BaseActivity implements View.OnClickListener, SearchKeyActivityView {
    private SearchKeyActivityPresenter mPresenter;
    @BindView(R.id.title_bar1_edt)
    EditText title_bar1_edt;
    @BindView(R.id.tv_close)
    TextView tv_close;
    @BindView(R.id.recycler_view)
    RecyclerView recycler_view;
    private int flag;
    private SearchKeyAdapter mAdapter;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_search_key;
    }

    @Override
    protected void getExtra() {
        flag = getIntent().getIntExtra("flag", 0);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new SearchKeyActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        tv_close.setOnClickListener(this);
        title_bar1_edt.setOnEditorActionListener(new mEditorActionListener());
        recycler_view.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(this, 1), getResources().getColor(R.color.white)));
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new SearchKeyAdapter(this);
        recycler_view.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener<CityBeanHot>() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, CityBeanHot content) {
                Intent intent = new Intent(SearchKeyActivity.this, TravelViseActivity.class);
                intent.putExtra("keyName", mAdapter.getList().get(position).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        if (flag == 101) {
            mPresenter.getVisaCity("05");
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_close:
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

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<List<CityBeanHot>> mCallBackVo) {
        mAdapter.onReference(mCallBackVo.getData());
    }


    private class mEditorActionListener implements TextView.OnEditorActionListener {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            switch (v.getId()) {
                case R.id.title_bar1_edt://搜索
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (!TextUtils.isEmpty(title_bar1_edt.getText().toString().trim())) {
                            Intent intent = new Intent(SearchKeyActivity.this, TravelViseActivity.class);
                            intent.putExtra("keyName", title_bar1_edt.getText().toString().trim());
                            startActivity(intent);
                        }
                    }
                    break;
            }
            return false;
        }
    }
}