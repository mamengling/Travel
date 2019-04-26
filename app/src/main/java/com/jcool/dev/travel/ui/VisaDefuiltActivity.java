package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.VisaInfoViewAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.StringBean;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.bean.VisaInfoBeanView;
import com.jcool.dev.travel.iactivityview.VisaInfoActivityView;
import com.jcool.dev.travel.persenter.VisaInfoActivityPresenter;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.view.ConstmChangeSpecPicker;
import com.jcool.dev.travel.view.ConstmChangeStringPicker;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.ConstmSharePicker;
import com.jcool.dev.travel.view.group.GroupItemDecoration;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

public class VisaDefuiltActivity extends BaseActivity implements View.OnClickListener, VisaInfoActivityView, PlatformActionListener {

    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.btn_to_pay)
    Button btn_to_pay;
    @BindView(R.id.tv_collect)
    TextView tv_collect;
    @BindView(R.id.tv_call_phone)
    TextView tv_call_phone;
    @BindView(R.id.tv_chat)
    TextView tv_chat;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private VisaInfoActivityPresenter mPresenter;
    private String visaId;
    private VisaInfoViewAdapter mAdapter;
    private List<VisaInfoBeanView> data = new ArrayList<>();
    private CallBackVo<VisaInfoBean> mCallBackVo;
    private int a, b, c, d;
    private ConstmChangeSpecPicker mPicker;
    private String visaPlace;
    private int visaPrice;
    private String visaImage;
    private String visaName;
    private String visaTime;
    private ConstmSharePicker mSharePicker;
    private String isCollect;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_visa_defulit;
    }

    @Override
    protected void getExtra() {
        visaId = getIntent().getStringExtra("visaId");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new VisaInfoActivityPresenter(this, this);
        icon_title.setText("签证详情");
        icon_right.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().getDrawable(R.mipmap.icon_share_visa_info_right), null);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        btn_to_pay.setOnClickListener(this);
        tv_collect.setOnClickListener(this);
        tv_chat.setOnClickListener(this);
        tv_call_phone.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPresenter.journeyVisaInfo(visaId);
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new GroupItemDecoration<String, TravelInfoBean.LinesBean.CharacteristicBean>());
        mAdapter = new VisaInfoViewAdapter(this);
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
                        } else if (positionChild == 2) {
                            mRecyclerView.smoothScrollToPosition(c);
                        } else {
                            mRecyclerView.smoothScrollToPosition(d);
                        }
                        break;
                    case 2:
                        if (positionChild == 0) {
                            Intent intent = new Intent(VisaDefuiltActivity.this, VisaDataActivity.class);
                            intent.putParcelableArrayListExtra("list", mAdapter.getList().get(position).getmCallBackVo().getData().getWorkingDataList());
                            startActivity(intent);
                        } else if (positionChild == 1) {
                            Intent intent = new Intent(VisaDefuiltActivity.this, VisaDataActivity.class);
                            intent.putParcelableArrayListExtra("list", mAdapter.getList().get(position).getmCallBackVo().getData().getFreeDataList());
                            startActivity(intent);
                        } else if (positionChild == 2) {

                        } else if (positionChild == 3) {

                        } else {

                        }
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getCollectStatus(visaId, getToken());
        mPresenter.journeyVisaInfo(visaId);
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
            case R.id.icon_right:
                initPicker();
                break;
            case R.id.btn_to_pay:
                initPersonPicker();
                break;
            case R.id.tv_collect:
                if (TextUtils.equals("true", isCollect)) {
                    JSONArray array = new JSONArray();
                    array.put(visaId);
                    mPresenter.journeyTravelCollectDelete(array, getToken());
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
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("goodsId", visaId);
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
    public void excuteSuccessCallBack(CallBackVo<VisaInfoBean> mCallBackVo) {


        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            this.mCallBackVo = mCallBackVo;
            data.clear();
            VisaInfoBeanView infoBeanView = new VisaInfoBeanView();
            infoBeanView.setViewType(101);
            infoBeanView.setmCallBackVo(mCallBackVo);
            data.add(infoBeanView);
            visaPlace = mCallBackVo.getData().getCommonPlace();
            visaName = mCallBackVo.getData().getVisaName();
            visaImage = mCallBackVo.getData().getVisaImage();
            visaPrice = mCallBackVo.getData().getVisaPrice();
            visaTime = mCallBackVo.getData().getHandleTime();
        }

//        if (mCallBackVoLine != null && mCallBackVoLine.getData() != null) {
        VisaInfoBeanView itemBean = new VisaInfoBeanView();
        itemBean.setViewType(102);
        itemBean.setTitle("所需材料");
        data.add(itemBean);
        a = 1;

        b = data.size() + 1;
        VisaInfoBeanView itemBean1 = new VisaInfoBeanView();
        itemBean1.setViewType(102);
        itemBean1.setTitle("产品说明");
        itemBean1.setImageUrl(mCallBackVo.getData().getVisaExplainImage());
        data.add(itemBean1);

        VisaInfoBeanView itemBean11 = new VisaInfoBeanView();
        itemBean11.setViewType(103);
        itemBean11.setImageUrl(mCallBackVo.getData().getVisaExplainImage());
        data.add(itemBean11);

        c = data.size() + 1;
        VisaInfoBeanView itemBean2 = new VisaInfoBeanView();
        itemBean2.setViewType(102);
        itemBean2.setTitle("购买须知");
        data.add(itemBean2);

        VisaInfoBeanView itemBean21 = new VisaInfoBeanView();
        itemBean21.setViewType(103);
        itemBean21.setImageUrl(mCallBackVo.getData().getVisaNoticeImage());
        data.add(itemBean21);

        d = data.size() + 1;
        VisaInfoBeanView itemBean3 = new VisaInfoBeanView();
        itemBean3.setViewType(102);
        itemBean3.setTitle("产品详情");
        itemBean3.setImageUrl(mCallBackVo.getData().getVisaDeatilImage());
        data.add(itemBean3);

        VisaInfoBeanView itemBean31 = new VisaInfoBeanView();
        itemBean31.setViewType(103);
        itemBean31.setImageUrl(mCallBackVo.getData().getVisaDeatilImage());
        data.add(itemBean31);
//        if (mCallBackVoLine.getData().getNotice() != null) {
//            for (int i = 0; i < mCallBackVoLine.getData().getNotice().size(); i++) {
//                TravelInfoBeanView itemBeanChara = new TravelInfoBeanView();
//                itemBeanChara.setViewType(103);
//                itemBeanChara.setItemImage(mCallBackVoLine.getData().getNotice().get(i));
//                data.add(itemBeanChara);
//            }
//        }

//        }
        mAdapter.onReference(data);
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
    public void excuteSuccessAddCollectCallBack(CallBackVo mCallBackVo) {
        isCollect = "true";
        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.icon_collect_sel), null, null);
    }

    @Override
    public void excuteSuccessDelCollectCallBack(CallBackVo mCallBackVo) {
        isCollect = "false";
        tv_collect.setCompoundDrawablesWithIntrinsicBounds(null, getResources().getDrawable(R.mipmap.icon_collect_unsel), null, null);
    }

    private void initPicker() {
        // 通过时间戳初始化日期，毫秒级别
        mSharePicker = new ConstmSharePicker(this, new ConstmSharePicker.Callback() {
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
        mSharePicker.setCancelable(true);
        // 不允许滚动动画
        mSharePicker.show();
    }


    private void initPersonPicker() {
        // 通过时间戳初始化日期，毫秒级别
        mPicker = new ConstmChangeSpecPicker(this, new ConstmChangeSpecPicker.Callback() {
            @Override
            public void onSelected(VisaInfoBean.VisaSpecBean mStrUnits) {
                Intent intent = new Intent(VisaDefuiltActivity.this, CreateVisaOrderActivity.class);
                intent.putExtra("visaId", visaId);
                intent.putExtra("visaPlace", visaPlace + "");
                intent.putExtra("visaName", visaName + "");
                intent.putExtra("visaImage", visaImage + "");
                intent.putExtra("visaPrice", visaPrice + "");
                intent.putExtra("visaTime", visaTime + "");
                intent.putExtra("specName", mStrUnits.getName() + "");
                intent.putExtra("specIndex", mStrUnits.getIndex() + "");
                intent.putExtra("specPrice", mStrUnits.getPrice());
                startActivity(intent);
            }
        }, mCallBackVo.getData().getVisaSpecList());
        // 不允许点击屏幕或物理返回键关闭
        mPicker.setCancelable(true);
        // 不允许滚动动画
        mPicker.show();
    }

    public void shareWebPager(String type) {
        Platform platform = ShareSDK.getPlatform(type);
        Platform.ShareParams shareParams = new Platform.ShareParams();
        shareParams.setText("中青国旅");
        shareParams.setTitle("哈哈");
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
