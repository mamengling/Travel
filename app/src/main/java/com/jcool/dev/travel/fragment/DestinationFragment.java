package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ContextRightListAdapter;
import com.jcool.dev.travel.adapter.TitleLiftListAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CityBean;
import com.jcool.dev.travel.bean.DestinationBean;
import com.jcool.dev.travel.iactivityview.DestinationFragmentView;
import com.jcool.dev.travel.persenter.DestinationFragmentPresenter;
import com.jcool.dev.travel.ui.SearchTravelActivity;
import com.jcool.dev.travel.ui.TravelListActivity;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.UiUtil;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 目的地
 */
public class DestinationFragment extends BaseFragment implements DestinationFragmentView, View.OnClickListener {
    private DestinationFragmentPresenter mPresenter;
    private TitleLiftListAdapter mAdapter;
    private ContextRightListAdapter mAdapterRight;
    private TextView icon_back;
    private TextView edt_search;
    private ImageView image_top;
    private ListView list_item;
    private RecyclerView recycler_view;
    private List<DestinationBean> mCallBackVo;
    private List<DestinationBean> mGroupList = new ArrayList<>();
    private List<DestinationBean.SecondPlaceBean> mList = new ArrayList<>();
    private SmartRefreshLayout refreshLayout;

    public static DestinationFragment newInstance() {

        Bundle args = new Bundle();

        DestinationFragment fragment = new DestinationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_destnation;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        edt_search = view.findViewById(R.id.edt_search);
        image_top = view.findViewById(R.id.image_top);
        list_item = view.findViewById(R.id.list_item);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        recycler_view = view.findViewById(R.id.fragment_content);
    }

    @Override
    protected void initTools() {
        mPresenter = new DestinationFragmentPresenter(this, getContext());
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshLayout.finishRefresh();
            }
        });
        mAdapterRight = new ContextRightListAdapter(getContext());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 6);
        int screenWidth = UiUtil.getScreenWidth(getContext());
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (TextUtils.equals("03", mList.get(position).getPlaceType())) {
                    return 6;
                } else if (TextUtils.equals("04", mList.get(position).getPlaceType())) {
                    return 2;
                } else {
                    return 3;
                }
            }
        });
        recycler_view.setLayoutManager(manager);

        recycler_view.setAdapter(mAdapterRight);
        mAdapterRight.setOnItemClickListener(new ConstmOnItemOnclickListener<String>() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, String content) {
                Intent intent3 = new Intent(getContext(), TravelListActivity.class);
                intent3.putExtra("number", 3);
                intent3.putExtra("city", mAdapterRight.getList().get(position).getPlaceName());
                startActivity(intent3);
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.querySecond();
    }

    @Override
    protected void setListener() {
        list_item.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAdapter != null) {
                    mAdapter.changeState(position);
                    mList.clear();
                    if (position != 0) {
                        DestinationBean.SecondPlaceBean bean = new DestinationBean.SecondPlaceBean();
                        bean.setPlaceName("推荐");
                        bean.setPlaceType("03");
                        bean.setId("0");
                        mList.add(bean);
                    }
                    mList.addAll(mGroupList.get(position).getSecondPlace());
                    mAdapterRight.onReference(mList);
                }
            }
        });
        edt_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentS = new Intent(getContext(), SearchTravelActivity.class);
                startActivity(intentS);
            }
        });
    }


    @Override
    public void onClick(View v) {

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
        initOnlineData();
    }

    @Override
    public void excuteSuccessCallBack(List<DestinationBean> mCallBackVo) {
//        initOnlineData();
        this.mCallBackVo = mCallBackVo;
        if (mCallBackVo != null) {
            mGroupList.clear();
            mGroupList.addAll(mCallBackVo);
            uiHandler.sendEmptyMessage(101);
        }
    }


    /**
     * handler处理消息机制
     */
    private Handler uiHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    mAdapter = new TitleLiftListAdapter(getContext(), mGroupList);
                    list_item.setAdapter(mAdapter);
                    break;
                case 102:
                    break;
                case 103:
                    break;
            }
        }
    };

    private void initOnlineData() {
        mGroupList.clear();
        DestinationBean itemFirst = new DestinationBean();
        itemFirst.setPlaceName("境内");
        String cityStr = AppUtils.initJsonData(getContext(), "areaCity.json");
        Gson gson = new Gson();
        List<CityBean> mCityList = gson.fromJson(cityStr, new TypeToken<List<CityBean>>() {
        }.getType());
        List<DestinationBean.SecondPlaceBean> mListCityItem = new ArrayList<>();
        for (int i = 0; i < mCityList.size(); i++) {
            DestinationBean.SecondPlaceBean bean = new DestinationBean.SecondPlaceBean();
            bean.setPlaceName(mCityList.get(i).getArea_name());
            bean.setPlaceType("03");
            bean.setId(mCityList.get(i).getArea_id() + "");
            mListCityItem.add(bean);
            for (int j = 0; j < mCityList.get(i).getSub().size(); j++) {
                DestinationBean.SecondPlaceBean beanItem = new DestinationBean.SecondPlaceBean();
                beanItem.setPlaceName(mCityList.get(i).getSub().get(j).getArea_name());
                beanItem.setPlaceType("04");
                beanItem.setId(mCityList.get(i).getSub().get(j).getArea_id() + "");
                mListCityItem.add(beanItem);
            }
        }
        itemFirst.setSecondPlace(mListCityItem);
        mGroupList.add(itemFirst);
    }
}
