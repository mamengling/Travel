package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ImageSeleteAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaOrderInfo;
import com.jcool.dev.travel.iactivityview.VisaOrderDetailActivityView;
import com.jcool.dev.travel.persenter.VisaOrderDetailActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OtherOrderDetailActivity extends BaseActivity implements View.OnClickListener, VisaOrderDetailActivityView {
    private VisaOrderDetailActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_order_status)
    TextView tv_order_status;
    @BindView(R.id.tv_order_number)
    TextView tv_order_number;
    @BindView(R.id.tv_visa_name)
    TextView tv_visa_name;
    @BindView(R.id.tv_visa_time)
    TextView tv_visa_time;
    @BindView(R.id.tv_count)
    TextView tv_count;
    @BindView(R.id.tv_days)
    TextView tv_days;
    @BindView(R.id.tv_link_name)
    TextView tv_link_name;
    @BindView(R.id.tv_link_phone)
    TextView tv_link_phone;
    @BindView(R.id.tv_link_email)
    TextView tv_link_email;
    @BindView(R.id.line_user)
    LinearLayout line_user;
    @BindView(R.id.visa_info)
    RelativeLayout visa_info;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private String orderId;
    private TextView tv_more;
    private List<VisaOrderInfo.CustomerBean> mList;
    private String visaId;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity__other_order_detail;
    }

    @Override
    protected void getExtra() {
        orderId = getIntent().getStringExtra("orderId");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new VisaOrderDetailActivityPresenter(this, this);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getVisaOrderInfo(getToken(), orderId);
            }
        });
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        visa_info.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        if (tv_more != null) {
            tv_more.setOnClickListener(this);
        }
    }

    @Override
    protected void initData() {
        mPresenter.getVisaOrderInfo(getToken(), orderId);
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
            case R.id.btn_commit:
                break;
            case R.id.visa_info:
                Intent intent = new Intent(OtherOrderDetailActivity.this, VisaDefuiltActivity.class);
                intent.putExtra("visaId", visaId);
                startActivity(intent);
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("id", orderId);
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
        ToastUtils.showShortToast(mCallBackVo.getMsg());
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<VisaOrderInfo> mCallBackVo) {
        if (mCallBackVo.getData() != null) {
            if (TextUtils.equals("01", mCallBackVo.getData().getOrderStatus())) {
                tv_order_status.setText("已审核");
            } else if (TextUtils.equals("02", mCallBackVo.getData().getOrderStatus())) {
                tv_order_status.setText("已退审");
            } else {
                tv_order_status.setText("待审核");
            }
            visaId = mCallBackVo.getData().getVisaId();
            tv_order_number.setText(mCallBackVo.getData().getId());
            tv_visa_name.setText(mCallBackVo.getData().getVisaName());
            tv_visa_time.setText(mCallBackVo.getData().getValidityDate() + "天");
            tv_count.setText(mCallBackVo.getData().getEntryCount() + "次");
            tv_days.setText(mCallBackVo.getData().getStayDays() + "天");
            tv_link_name.setText(TextUtils.isEmpty(mCallBackVo.getData().getLinkName()) ? "" : mCallBackVo.getData().getLinkName());
            tv_link_phone.setText(TextUtils.isEmpty(mCallBackVo.getData().getLinkPhone()) ? "" : mCallBackVo.getData().getLinkPhone());
            tv_link_email.setText(TextUtils.isEmpty(mCallBackVo.getData().getLinkEmail()) ? "" : mCallBackVo.getData().getLinkEmail());

            if (mCallBackVo.getData().getCustomer() != null && mCallBackVo.getData().getCustomer().size() > 0) {
                mList = mCallBackVo.getData().getCustomer();
                line_user.removeAllViews();
                //循环旅客信息
                for (int i = 0; i < mCallBackVo.getData().getCustomer().size(); i++) {
                    View customerViews = LayoutInflater.from(OtherOrderDetailActivity.this).inflate(R.layout.x_item_commit_person, null);
                    LinearLayout line_card = customerViews.findViewById(R.id.line_card);
                    TextView tv_user_name = customerViews.findViewById(R.id.tv_user_name);
                    TextView tv_user_sex = customerViews.findViewById(R.id.tv_user_sex);
                    TextView tv_user_type = customerViews.findViewById(R.id.tv_user_type);//成人 儿童
                    TextView tv_card_type = customerViews.findViewById(R.id.tv_card_type);//身份证 护照
                    TextView tv_card_number = customerViews.findViewById(R.id.tv_card_number);//证件号码
                    final TextView tv_more = customerViews.findViewById(R.id.tv_more);
                    tv_more.setTag(i);
                    tv_more.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            int flag = (int) tv_more.getTag();
                            ToastUtils.showShortToast("第" + flag + "条数据");
                            Intent intent = new Intent(OtherOrderDetailActivity.this, DatumCommitActivity.class);
                            intent.putParcelableArrayListExtra("list", mList.get(flag).getDataList());
                            intent.putExtra("orderId", orderId);
                            intent.putExtra("custId", mList.get(flag).getId());
                            startActivity(intent);
                        }
                    });

                    tv_user_name.setText(mCallBackVo.getData().getCustomer().get(i).getCustName());
                    if (TextUtils.equals("girl", mCallBackVo.getData().getCustomer().get(i).getCustSex())) {//旅客性别(boy,girl)
                        tv_user_sex.setText("女");
                    } else {
                        tv_user_sex.setText("男");
                    }
                    //客户类型(01:在职；02：在校学生；03：退休；04：学龄儿童)
                    if (TextUtils.equals("01", mCallBackVo.getData().getCustomer().get(i).getCustType())) {
                        tv_user_type.setText("在职");
                    } else if (TextUtils.equals("02", mCallBackVo.getData().getCustomer().get(i).getCustType())) {
                        tv_user_type.setText("在校学生");
                    } else if (TextUtils.equals("03", mCallBackVo.getData().getCustomer().get(i).getCustType())) {
                        tv_user_type.setText("退休");
                    } else {
                        tv_user_type.setText("学龄儿童");
                    }

                    if (TextUtils.equals("ID", mCallBackVo.getData().getCustomer().get(i).getCertType())) {//旅客性别(boy,girl)
                        tv_card_type.setText("身份证");
                    } else {
                        tv_card_type.setText("护照");
                    }

                    tv_card_number.setText(mCallBackVo.getData().getCustomer().get(i).getCustCert());


                    //循环 证件信息
                    if (mCallBackVo.getData().getCustomer().get(i).getDataList() != null && mCallBackVo.getData().getCustomer().get(i).getDataList().size() > 0) {
                        for (int j = 0; j < mCallBackVo.getData().getCustomer().get(i).getDataList().size(); j++) {
                            LogUtil.i("TAG 证件信息", mCallBackVo.getData().getCustomer().get(i).getDataList().get(j).toString());
                            View customerItem = LayoutInflater.from(OtherOrderDetailActivity.this).inflate(R.layout.xml_item_card, null);
                            final TextView tv_card_name = customerItem.findViewById(R.id.tv_card_name);//证件号码
                            final TextView tv_card_right = customerItem.findViewById(R.id.tv_card_right);//证件号码
                            final RecyclerView recyclerView_image = customerItem.findViewById(R.id.recyclerView_image);//证件w号码
                            tv_card_name.setText(mCallBackVo.getData().getCustomer().get(i).getDataList().get(j).getTitle());
                            tv_card_right.setTag(i);
                            recyclerView_image.setTag(i);
                            tv_card_right.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    recyclerView_image.setVisibility(View.VISIBLE);
                                }
                            });
                            LinearLayoutManager manager = new LinearLayoutManager(OtherOrderDetailActivity.this);
                            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            recyclerView_image.setLayoutManager(manager);
                            ImageSeleteAdapter mAdapter = new ImageSeleteAdapter(OtherOrderDetailActivity.this);
                            recyclerView_image.setAdapter(mAdapter);
                            if (mCallBackVo.getData().getCustomer().get(i).getDataList().get(j).getInfoList() != null && mCallBackVo.getData().getCustomer().get(i).getDataList().get(j).getInfoList().size() > 0) {
                                mAdapter.onReference(mCallBackVo.getData().getCustomer().get(i).getDataList().get(j).getInfoList());
                            }
                            mAdapter.setOnItemClickListener(new ConstmOnItemOnclickListener() {
                                @Override
                                public void clickItem(View view, int position, int positionChild, int ClickType, Object content) {
                                    switch (ClickType) {
                                        case 0:
                                            Intent intent = new Intent(OtherOrderDetailActivity.this, DatumCommitActivity.class);
                                            intent.putParcelableArrayListExtra("list", mList.get((int) recyclerView_image.getTag()).getDataList());
                                            intent.putExtra("orderId", orderId);
                                            intent.putExtra("custId", mList.get((int) recyclerView_image.getTag()).getId());
                                            startActivity(intent);
                                            break;
                                        case 1:
                                            break;
                                        case 2:
                                            break;
                                    }
                                }
                            });
                            line_card.addView(customerItem);
                        }
                    }
                    line_user.addView(customerViews);
                }
            }
        }
        refreshLayout.finishRefresh();
    }

    @Override
    public void excuteSuccessOrderCallBack(CallBackVo<String> mCallBackVo) {

    }
}
