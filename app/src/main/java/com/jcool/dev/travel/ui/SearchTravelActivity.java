package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.HistoryAdapter;
import com.jcool.dev.travel.adapter.SearchTravelListAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.iactivityview.SearchTravelActivityView;
import com.jcool.dev.travel.persenter.SearchTravelActivityPresenter;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.utils.ZxSharedPre;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.RecycleViewDivider;
import com.jcool.dev.travel.view.YRecycleview;
import com.jcool.dev.travel.view.rollviewpage.OnItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchTravelActivity extends BaseActivity implements View.OnClickListener, SearchTravelActivityView {
    private SearchTravelActivityPresenter mPresenter;
    private TextView tv_close;
    private EditText title_bar1_edt;
    private List<String> listKey1s;
    private HistoryAdapter mAdapter;
    private String search_info;
    private RecyclerView recycler_view_history;
    private YRecycleview recycler_view;
    private LinearLayout line_history;
    private RelativeLayout relative;
    private RelativeLayout relative_no;
    private SearchTravelListAdapter mAdapterTravel;
    private List<TravelBean.RecordsBean> mList = new ArrayList<>();
    private int intNumber = 0;
    private int intHandler = 101;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_search_travel;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        tv_close = findViewById(R.id.tv_close);
        title_bar1_edt = findViewById(R.id.title_bar1_edt);
        recycler_view_history = findViewById(R.id.recycler_view_history);
        recycler_view = findViewById(R.id.recycler_view);
        relative = findViewById(R.id.relative);
        line_history = findViewById(R.id.line_history);
        relative_no = findViewById(R.id.relative_no);
        findViewById(R.id.tv_history).setOnClickListener(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new SearchTravelActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        setHistoryLayout();
        tv_close.setOnClickListener(this);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view_history.setLayoutManager(layoutManager);
        //水平分割线
        recycler_view_history.addItemDecoration(new RecycleViewDivider(
                this, LinearLayoutManager.HORIZONTAL, UiUtil.dip2px(this, 1), getResources().getColor(R.color.root_bg_color1)));
        mAdapter = new HistoryAdapter(this);
        mAdapter.setOnItemOnclickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, Object content) {
                search_info = (String) content;
                intNumber = 0;
                intHandler = 101;
                recycler_view_history.setVisibility(View.GONE);
                relative.setVisibility(View.GONE);
                line_history.setVisibility(View.GONE);
                recycler_view.setVisibility(View.VISIBLE);
                mPresenter.journeyGoodsSales(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY);
            }
        });
        title_bar1_edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (TextUtils.isEmpty(search_info)) {
//                    setHistoryLayout();
//                    recycler_view_history.setVisibility(View.VISIBLE);
//                    recycler_view.setVisibility(View.GONE);
//                    relative.setVisibility(View.VISIBLE);
//                    line_history.setVisibility(View.VISIBLE);
//                    relative_no.setVisibility(View.GONE);
//                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                search_info = title_bar1_edt.getText().toString().trim();
                if (TextUtils.isEmpty(search_info)) {
                    setHistoryLayout();
                } else {
                    List<String> list = ZxSharedPre.getInstance(SearchTravelActivity.this).getListInfo("searchHis");
                    if (!list.contains(search_info)) {
                        list.add(search_info);
                        ZxSharedPre.getInstance(SearchTravelActivity.this).setListInfo(list, "searchHis");
                    }

                }
            }
        });
        recycler_view_history.setAdapter(mAdapter);
        title_bar1_edt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {//EditorInfo.IME_ACTION_SEARCH、EditorInfo.IME_ACTION_SEND等分别对应EditText的imeOptions属性
                    if (!TextUtils.isEmpty(search_info)) {
                        intNumber = 0;
                        intHandler = 101;
                        recycler_view_history.setVisibility(View.GONE);
                        relative.setVisibility(View.GONE);
                        line_history.setVisibility(View.GONE);
                        recycler_view.setVisibility(View.VISIBLE);
                        mPresenter.journeyGoodsSales(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY);
                    } else {
                        ToastUtils.showShortToast("请输入搜索内容");

                    }
                    return true;
                }
                return false;
            }
        });


        recycler_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        mAdapterTravel = new SearchTravelListAdapter(this);
        //设置adapter
        recycler_view.setAdapter(mAdapterTravel);
        mAdapterTravel.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(SearchTravelActivity.this, TravelDefuiltActivity.class);
                intent.putExtra("travelId", mList.get(position).getId());
                startActivity(intent);
            }
        });
        recycler_view.setRefreshAndLoadMoreListener(new YRecycleview.OnRefreshAndLoadMoreListener() {
            @Override
            public void onRefresh() {
                intNumber = 0;
                intHandler = 101;
                mPresenter.journeyGoodsSales(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY);
            }

            @Override
            public void onLoadMore() {
                intNumber += 1;
                intHandler = 102;
                mPresenter.journeyGoodsSales(Constants.APP_HOME_API_JOURNEY_GOODS_QUERY);
            }
        });
        setHistoryLayout();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    public void setHistoryLayout() {
        try {
            listKey1s = ZxSharedPre.getInstance(this).getListInfo("searchHis");
            //没有搜索历史不显示清空历史记录
            if (listKey1s != null && listKey1s.size() > 0) {
                mAdapter.onReference(listKey1s);
            } else {
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_history:
                List<String> list = null;
                ZxSharedPre.getInstance(SearchTravelActivity.this).setListInfo(list, "searchHis");
                mAdapter.onReference(null);
                break;
            case R.id.tv_close:
                finish();
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("pageIndex", "0");
            object.put("pageSize", "10");
            object.put("queryStr", search_info);//检索关键字
            object.put("isGroup", "");//是否是组团游（Y组团游玩，N自由行）
//            object.put("isDomestic", isDomestic);//是否是境内游（ Y境内游，N境外游）
//            object.put("aroundCity", aroundCity);//所属周边城市
            object.put("onLine", "");//目的地
            object.put("onLine", "");//是否在架
//            object.put("orderBy", "sellNumber");//排序属性(销量 sellNumber、价格 minPrice)
//            object.put("descOrAsc", "asc");//升序、降序（desc\asc）
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
                recycler_view.setReFreshComplete();
                break;
            case 102:
                recycler_view.setloadMoreComplete();
                break;
        }
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<TravelBean> mCallBackVo) {
        switch (intHandler) {
            case 101:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getRecords() != null && mCallBackVo.getData().getRecords().size() > 0) {
                    relative_no.setVisibility(View.GONE);
                    mList.clear();
                    mList.addAll(mCallBackVo.getData().getRecords());
                    mAdapterTravel.onReference(mList);
                } else {
                    relative_no.setVisibility(View.VISIBLE);
                    recycler_view.setVisibility(View.GONE);
                }
                recycler_view.setReFreshComplete();
                break;
            case 102:
                if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getRecords() != null && mCallBackVo.getData().getRecords().size() > 0) {
                    relative_no.setVisibility(View.GONE);
                    mList.clear();
                    mList.addAll(mCallBackVo.getData().getRecords());
                    mAdapterTravel.addOnReference(mList);
                } else {
                    relative_no.setVisibility(View.GONE);
                    recycler_view.setVisibility(View.VISIBLE);
                }

                recycler_view.setloadMoreComplete();
                break;
        }


    }
}
