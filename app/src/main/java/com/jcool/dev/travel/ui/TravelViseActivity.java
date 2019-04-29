package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
import com.jcool.dev.travel.utils.StatusBarUtils;
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
    private EditText edt_search;
    private RelativeLayout relative_no;
//    private String headers[] = {"综合排序", "常用送签地"};
    private String headers[] = {"综合排序"};
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
    private String keyName = "";
    private String regionSort="";

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_vise_travel;
    }

    @Override
    protected void getExtra() {
        keyName = getIntent().getStringExtra("keyName");
        regionSort = getIntent().getStringExtra("regionSort");
    }

    @Override
    protected void initView() {
        mDropDownMenu = findViewById(R.id.dropDownMenu);
        edt_search = findViewById(R.id.edt_search);
    }

    @Override
    protected void initTools() {
        edt_search.setText(keyName);
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
//        popupViews.add(listViewPlice);

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
        relative_no = constellationView.findViewById(R.id.relative_no);
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

        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                keyName = edt_search.getText().toString();
                if (TextUtils.isEmpty(keyName)) {
                    keyName="";
                    mPresenter.journeyGoodsSales();
                }else {
                    intNumber = 0;
                    intHandler = 101;
                    contentView.setVisibility(View.VISIBLE);
                    mPresenter.journeyGoodsSales();
                }
            }
        });
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    if (!TextUtils.isEmpty(keyName)) {
                        intNumber = 0;
                        intHandler = 101;
                        contentView.setVisibility(View.VISIBLE);
                        mPresenter.journeyGoodsSales();
                    }
                    return true;
                }
                return false;
            }
        });


        //init dropdownview
        mDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, constellationView);


    }

    @Override
    protected void setListener() {
        findViewById(R.id.icon_back).setOnClickListener(this);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                keyName = edt_search.getText().toString().trim();
                if (!TextUtils.isEmpty(keyName)) {
                    mPresenter.journeyGoodsSales();
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
            object.put("regionSort", regionSort);//目的地id
            object.put("targetPlace", keyName);//目的地名称
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
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getRecords() != null && mCallBackVo.getData().getRecords().size() > 0) {
                    mAdapterData.replaceData(mCallBackVo.getData().getRecords());
                    if (mAdapterData.getData().size() < mCallBackVo.getData().getTotal()) {
                        mAdapterData.setEnableLoadMore(true);
                        mAdapterData.openLoadAnimation();
                    } else {
                        ToastUtils.showShortToast("数据全部加载完毕");
                        mAdapterData.setEnableLoadMore(false);
                    }
                    relative_no.setVisibility(View.GONE);
                    contentView.setVisibility(View.VISIBLE);
                }else {
                    relative_no.setVisibility(View.VISIBLE);
                    contentView.setVisibility(View.GONE);
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
