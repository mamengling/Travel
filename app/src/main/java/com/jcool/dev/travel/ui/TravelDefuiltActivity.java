package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.TravelInfoViewAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.TravelInfoBeanView;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.iactivityview.TravelInfoActivityView;
import com.jcool.dev.travel.persenter.TravelInfoActivityPresenter;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.ConstmChangeSpecPicker;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.ConstmSharePicker;
import com.jcool.dev.travel.view.group.GroupItemDecoration;
import com.mob.MobSDK;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.onekeyshare.ShareContentCustomizeCallback;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class TravelDefuiltActivity extends BaseActivity implements View.OnClickListener, TravelInfoActivityView, PlatformActionListener {
    private TravelInfoActivityPresenter mPresenter;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_back)
    TextView icon_title_back;
    @BindView(R.id.tv_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.tv_buy)
    TextView tv_buy;
    @BindView(R.id.tv_call_phone)
    TextView tv_call_phone;
    @BindView(R.id.tv_chat)
    TextView tv_chat;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private String travelId;
    private int a, b, c;
    private TravelInfoViewAdapter mAdapter;
    private List<TravelInfoBeanView> data = new ArrayList<>();
    private List<TravelInfoBeanView> dataOther = new ArrayList<>();
    private String dateEnd;
    private String dateBegin;
    private ConstmSharePicker mPicker;
    private String isCollect;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setTranslucent(this, 0);
        return R.layout.activity_travel_defulit;
    }

    @Override
    protected void getExtra() {
        travelId = getIntent().getStringExtra("travelId");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        icon_title_back.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.mipmap.icon_travel_back), null, null, null);
        icon_right.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_share_info), null);
        mPresenter = new TravelInfoActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
        tv_chat.setOnClickListener(this);
        tv_call_phone.setOnClickListener(this);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.journeyTravelInfo(travelId);
            }

            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {

            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, TravelInfoBean.LinesBean.CharacteristicBean>());
        mAdapter = new TravelInfoViewAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener() {
            @Override
            public void clickItem(View view, int position, int positionChild, int ClickType, Object content) {
                switch (ClickType) {
                    case 1:
                        if (positionChild == 0) {
                            mRecyclerView.smoothScrollToPosition(a);
                        } else if (positionChild == 1) {
                            mRecyclerView.smoothScrollToPosition(b);
                        } else {
                            mRecyclerView.smoothScrollToPosition(c);
                        }
                        break;
                    case 2:
                        Intent intent = new Intent(TravelDefuiltActivity.this, CalendarActivity.class);
                        intent.putExtra("travelId", travelId);
                        intent.putExtra("travelName", mAdapter.getList().get(0).getmCallBackVo().getData().getName());
                        if (mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods() != null && mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().size() > 0) {
                            intent.putExtra("priceNow", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(0).getPriceNow());
                            intent.putExtra("goodsDate", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(0).getId());
                        }
                        startActivity(intent);
                        break;
                    case 3:
                        mPresenter.journeyTravelLineInfo(mAdapter.getList().get(0).getmCallBackVo().getData().getLines().get(positionChild).getId());
                        break;
                    case 4:
                        if (positionChild == 1) {
                            Intent intent1 = new Intent(TravelDefuiltActivity.this, CalendarActivity.class);
                            intent1.putExtra("travelId", travelId);
                            intent1.putExtra("travelName", mAdapter.getList().get(0).getmCallBackVo().getData().getName());
                            intent1.putExtra("priceNow", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(0).getPriceNow());
                            intent1.putExtra("goodsDate", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(0).getId());
                            startActivity(intent1);
                        } else if (positionChild == 2) {
                            Intent intent2 = new Intent(TravelDefuiltActivity.this, CalendarActivity.class);
                            intent2.putExtra("travelId", travelId);
                            intent2.putExtra("goodsDate", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(1).getGoodsDate());
                            intent2.putExtra("priceNow", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(1).getPriceNow());
                            intent2.putExtra("goodsDate", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(1).getId());
                            startActivity(intent2);
                        }

                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.journeyTravelInfo(travelId);
        mPresenter.getCollectStatus(travelId, getToken());

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
            case R.id.icon_right:
                initPersonPicker();
                break;
            case R.id.tv_collect:
                if (TextUtils.equals("true", isCollect)) {
                    JSONArray array = new JSONArray();
                    array.put(travelId);
                    mPresenter.journeyTravelCollectDelete(array,getToken());

                } else {

                    mPresenter.journeyTravelCollect(getToken());
                }
                break;
            case R.id.tv_call_phone:
                callPhone(getUserPhone());
                break;
            case R.id.tv_chat:
                Intent intentChat = new Intent(this, WebviewDefulitActivity.class);
                intentChat.putExtra("loadUrl", "http://p.qiao.baidu.com/cps/chatIndex");
                intentChat.putExtra("title", "在线客服");
                startActivity(intentChat);
                break;
            case R.id.tv_buy:
                Intent intent = new Intent(TravelDefuiltActivity.this, CalendarActivity.class);
                intent.putExtra("travelId", travelId);
                intent.putExtra("travelName", mAdapter.getList().get(0).getmCallBackVo().getData().getName());
                if (mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods() != null && mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().size() > 0) {
                    intent.putExtra("priceNow", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(0).getPriceNow());
                    intent.putExtra("goodsDate", mAdapter.getList().get(0).getmCallBackVo().getData().getDataAndGoods().get(0).getId());
                }
                startActivity(intent);
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("goodsId", travelId);
            object.put("dateBegin", AppUtils.getTimeAtt("yyyy-MM-dd"));
            object.put("dateEnd", AppUtils.dayAdd(10));
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
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<TravelInfoBean> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            data.clear();
            TravelInfoBeanView itemBean = new TravelInfoBeanView();
            itemBean.setViewType(101);
            itemBean.setmCallBackVo(mCallBackVo);
            data.add(itemBean);
            if (mCallBackVo.getData().getLines() != null) {
                if (mCallBackVo.getData().getLines().size() > 0) {
                    mPresenter.journeyTravelLineInfo(mCallBackVo.getData().getLines().get(0).getId());
                }
            }
            mPresenter.journeyTravelTimeList(travelId);
            mAdapter.onReference(data);
        }
        dataOther.clear();
        TravelInfoBeanView itemBean = new TravelInfoBeanView();
        TravelInfoBean.LinesBean.CharacteristicBean item = new TravelInfoBean.LinesBean.CharacteristicBean();
        itemBean.setViewType(102);
        item.setTitle("产品特色");
        itemBean.setItemImage(item);
        dataOther.add(itemBean);
        a = 1;
        b = dataOther.size() + 1;
        TravelInfoBeanView itemBean1 = new TravelInfoBeanView();
        TravelInfoBean.LinesBean.CharacteristicBean item1 = new TravelInfoBean.LinesBean.CharacteristicBean();
        itemBean1.setViewType(102);
        item1.setTitle("行程介绍");
        itemBean1.setItemImage(item1);
        dataOther.add(itemBean1);
        c = dataOther.size() + 1;
        TravelInfoBeanView itemBean2 = new TravelInfoBeanView();
        TravelInfoBean.LinesBean.CharacteristicBean item2 = new TravelInfoBean.LinesBean.CharacteristicBean();
        itemBean2.setViewType(102);
        item2.setTitle("预订须知");
        itemBean2.setItemImage(item2);
        dataOther.add(itemBean2);
//        data.addAll(dataOther);
        mAdapter.addOnReference(dataOther);
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessLineCallBack(CallBackVo<TravelInfoBean.LinesBean> mCallBackVo) {
        mAdapter.getList().removeAll(dataOther);
        dataOther.clear();
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            TravelInfoBeanView itemBean = new TravelInfoBeanView();
            TravelInfoBean.LinesBean.CharacteristicBean item = new TravelInfoBean.LinesBean.CharacteristicBean();
            itemBean.setViewType(102);
            item.setTitle("产品特色");
            itemBean.setItemImage(item);
            dataOther.add(itemBean);
            a = 1;
            if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getCharacteristic() != null) {
                for (int i = 0; i < mCallBackVo.getData().getCharacteristic().size(); i++) {
                    TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
                    itemBeanChara.setViewType(103);
                    itemBeanChara.setItemImage(mCallBackVo.getData().getCharacteristic().get(i));
                    dataOther.add(itemBeanChara);
                }
            }
            b = dataOther.size() + 1;
            TravelInfoBeanView itemBean1 = new TravelInfoBeanView();
            TravelInfoBean.LinesBean.CharacteristicBean item1 = new TravelInfoBean.LinesBean.CharacteristicBean();
            itemBean1.setViewType(102);
            item1.setTitle("行程介绍");
            itemBean1.setItemImage(item1);
            dataOther.add(itemBean1);
            if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getSynopsis() != null) {
                for (int i = 0; i < mCallBackVo.getData().getSynopsis().size(); i++) {
                    TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
                    itemBeanChara.setViewType(103);
                    itemBeanChara.setItemImage(mCallBackVo.getData().getSynopsis().get(i));
                    dataOther.add(itemBeanChara);
                }
            }
            c = dataOther.size() + 1;
            TravelInfoBeanView itemBean2 = new TravelInfoBeanView();
            TravelInfoBean.LinesBean.CharacteristicBean item2 = new TravelInfoBean.LinesBean.CharacteristicBean();
            itemBean2.setViewType(102);
            item2.setTitle("预订须知");
            itemBean2.setItemImage(item2);
            dataOther.add(itemBean2);
            if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().getNotice() != null) {
                for (int i = 0; i < mCallBackVo.getData().getNotice().size(); i++) {
                    TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
                    itemBeanChara.setViewType(103);
                    itemBeanChara.setItemImage(mCallBackVo.getData().getNotice().get(i));
                    dataOther.add(itemBeanChara);
                }
            }

        }
        mAdapter.addOnReference(dataOther);
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessCollectCallBack(CallBackVo<String> mCallBackVo) {
        if (TextUtils.equals("true", mCallBackVo.getData())) {
            isCollect = "true";
            tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.icon_collect_sel), null, null);
        } else {
            tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.icon_collect_unsel), null, null);
            isCollect = "false";
        }
    }

    @Override
    public void excuteSuccessGoodsCallBack(CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo) {
        if (data != null && data.size() > 0) {
            data.get(0).getmCallBackVo().getData().setDataAndGoods(mCallBackVo.getData());
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void excuteSuccessDelCollectCallBack(CallBackVo mCallBackVo) {
        isCollect = "false";
        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.icon_collect_unsel), null, null);
    }

    @Override
    public void excuteSuccessAddCollectCallBack(CallBackVo mCallBackVo) {
        isCollect = "true";
        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.icon_collect_sel), null, null);
    }


    private void initPersonPicker() {
        // 通过时间戳初始化日期，毫秒级别
        mPicker = new ConstmSharePicker(this, new ConstmSharePicker.Callback() {
            @Override
            public void onSelected(int type) {
                switch (type) {
                    case 0:
                        shareWebPager(Wechat.NAME);
                        break;
                    case 1:
                        shareWebPager(WechatMoments.NAME);
                        break;
                    case 2:
                        shareWebPager(QQ.NAME);
                        break;
                    case 3:
                        shareWebPager(QZone.NAME);
                        break;
                }
            }
        });
        // 不允许点击屏幕或物理返回键关闭
        mPicker.setCancelable(true);
        // 不允许滚动动画
        mPicker.show();
    }

    public void shareWebPager(String type) {
        Platform platform = ShareSDK.getPlatform(type);
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setText("中青国旅");
        shareParams.setTitle("中青国旅");
        shareParams.setUrl("http://www.baidu.com");
        shareParams.setShareType(Platform.SHARE_WEBPAGE);
        platform.setPlatformActionListener(this);
        platform.share(shareParams);
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {

    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
