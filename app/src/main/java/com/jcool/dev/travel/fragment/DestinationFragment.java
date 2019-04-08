package com.jcool.dev.travel.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ContextRightListAdapter;
import com.jcool.dev.travel.adapter.TitleLiftListAdapter;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.DestinationBean;
import com.jcool.dev.travel.iactivityview.DestinationFragmentView;
import com.jcool.dev.travel.persenter.DestinationFragmentPresenter;
import com.jcool.dev.travel.utils.DividerItemDecoration;
import com.jcool.dev.travel.utils.UiUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

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
    private EditText edt_search;
    private ImageView image_top;
    private ListView list_item;
    private RecyclerView recycler_view;
    private List<DestinationBean> mCallBackVo;
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
        icon_back = view.findViewById(R.id.icon_back);
        edt_search = view.findViewById(R.id.edt_search);
        image_top = view.findViewById(R.id.image_top);
        list_item = view.findViewById(R.id.list_item);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        recycler_view = view.findViewById(R.id.fragment_content);
    }

    @Override
    protected void initTools() {
        mPresenter = new DestinationFragmentPresenter(this, getContext());
        icon_back.setVisibility(View.GONE);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {

            }
        });
        mAdapterRight = new ContextRightListAdapter(getContext());
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
//        int screenWidth = UiUtil.getScreenWidth(getContext());
//        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return (3 - position % 3);
//            }
//        });

        recycler_view.setLayoutManager(manager);

        recycler_view.setAdapter(mAdapterRight);
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
                    mList.addAll(mCallBackVo.get(position).getSecondPlace());
                    mAdapterRight.onReference(mList);
                }
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

    }

    @Override
    public void excuteSuccessCallBack(List<DestinationBean> mCallBackVo) {
        this.mCallBackVo = mCallBackVo;
        uiHandler.sendEmptyMessage(101);
    }


    /**
     * handler处理消息机制
     */
    private Handler uiHandler = new Handler() {
        public void handleMessage(Message message) {
            switch (message.what) {
                case 101:
                    mAdapter = new TitleLiftListAdapter(getContext(), mCallBackVo);
                    list_item.setAdapter(mAdapter);
                    break;
                case 102:
                    break;
                case 103:
                    break;
            }
        }
    };
}
