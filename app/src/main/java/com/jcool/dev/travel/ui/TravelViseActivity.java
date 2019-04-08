package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.GirdDropDownAdapter;
import com.jcool.dev.travel.adapter.VisaListAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaBean;
import com.jcool.dev.travel.iactivityview.TravelViseActivityView;
import com.jcool.dev.travel.persenter.TravelViseActivityPresenter;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.DropDownMenu;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TravelViseActivity extends BaseActivity implements View.OnClickListener, TravelViseActivityView {
    private TravelViseActivityPresenter mPresenter;
    private DropDownMenu mDropDownMenu;
    private String headers[] = {"综合排序", "常用送签地"};
    private List<View> popupViews = new ArrayList<>();
    private String topArr[] = {"综合排序", "近期销量最高", "价格从高到低", "价格从低到高"};
    private String topArrId[] = {"", "01", "02", "03"};
    private String twoArr[] = {"常用送签地", "北京", "上海"};
    private String twoArrId[] = {"", "北京", "上海"};
    private GirdDropDownAdapter mAdapter;//综合
    private GirdDropDownAdapter mAdapterPlice;//目的地
    private RecyclerView contentView;
    private SmartRefreshLayout refreshLayout;
    private List<VisaBean.RecordsBean> mList = new ArrayList<>();
    private VisaListAdapter mAdapterData;
    private View constellationView;
    private String sort = "";
    private String commonPlace = "";
    private int intNumber = 0;
    private int intHandler = 101;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_vise_travel;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        mDropDownMenu = findViewById(R.id.dropDownMenu);
    }

    @Override
    protected void initTools() {
        mPresenter = new TravelViseActivityPresenter(this, this);
        final ListView listView = new ListView(this);
        mAdapter = new GirdDropDownAdapter(this, Arrays.asList(topArr));
        listView.setDividerHeight(0);
        listView.setAdapter(mAdapter);
        final ListView listViewPlice = new ListView(this);
        mAdapterPlice = new GirdDropDownAdapter(this, Arrays.asList(twoArr));
        listViewPlice.setDividerHeight(0);
        listViewPlice.setAdapter(mAdapterPlice);
        //init popupViews
        popupViews.clear();
        popupViews.add(listView);
        popupViews.add(listViewPlice);

        //add item click event
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sort = topArrId[position];
                mAdapter.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[0] : topArr[position]);
                mDropDownMenu.closeMenu();
                intNumber = 0;
                intHandler = 101;
                mPresenter.journeyGoodsSales();
            }
        });

        listViewPlice.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                commonPlace = twoArrId[position];
                mAdapterPlice.setCheckItem(position);
                mDropDownMenu.setTabText(position == 0 ? headers[1] : twoArr[position]);
                mDropDownMenu.closeMenu();
                intNumber = 0;
                intHandler = 101;
                mPresenter.journeyGoodsSales();
            }
        });
        constellationView = getLayoutInflater().inflate(R.layout.travel_custom_layout, null);
        contentView = constellationView.findViewById(R.id.mRecyclerView);
        refreshLayout = constellationView.findViewById(R.id.refreshLayout);
        contentView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        contentView.setLayoutManager(new LinearLayoutManager(this));
        mAdapterData = new VisaListAdapter(this, R.layout.xml_item_visa_list, mList);
        //设置adapter
        contentView.setAdapter(mAdapterData);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                intNumber = 0;
                intHandler = 101;
                mPresenter.journeyGoodsSales();
            }
        });
        //打开或关闭加载
        mAdapterData.setEnableLoadMore(true);
        mAdapterData.openLoadAnimation();
        mAdapterData.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                intNumber += 1;
                intHandler = 102;
                mPresenter.journeyGoodsSales();
            }
        }, contentView);
        mAdapterData.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(TravelViseActivity.this, VisaDefuiltActivity.class);
                intent.putExtra("visaId", mAdapterData.getData().get(position).getId());
                startActivity(intent);
            }
        });
        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, constellationView);
    }

    @Override
    protected void setListener() {
        findViewById(R.id.icon_back).setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.journeyGoodsSales();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_back:
                finish();
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("pageIndex", intNumber);
            object.put("pageSize", 10);
            object.put("regionSort", "");//目的地id
            object.put("targetPlace", "");//目的地名称
            object.put("commonPlace", commonPlace);//常用送签地
            object.put("sort", sort);//排序（01：销量；02价格由高到低；03价格由低到高）
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
    public void excuteSuccessCallBack(CallBackVo<VisaBean> mCallBackVo) {
        switch (intHandler) {
            case 101:
                mAdapterData.replaceData(mCallBackVo.getData().getRecords());
                if (mAdapterData.getData().size() < mCallBackVo.getData().getTotal()) {
                    mAdapterData.setEnableLoadMore(true);
                    mAdapterData.openLoadAnimation();
                } else {
                    ToastUtils.showShortToast("数据全部加载完毕");
                    mAdapterData.setEnableLoadMore(false);
                }
                refreshLayout.finishRefresh();
                break;
            case 102:
                mAdapterData.addData(mCallBackVo.getData().getRecords());
                if (mAdapterData.getData().size() < mCallBackVo.getData().getTotal()) {
                    mAdapterData.setEnableLoadMore(true);
                    mAdapterData.openLoadAnimation();
                } else {
                    ToastUtils.showShortToast("数据全部加载完毕");
                    mAdapterData.setEnableLoadMore(false);
                }
                mAdapterData.loadMoreComplete();
                break;
        }
    }
}
