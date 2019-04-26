package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelOrderInfo;
import com.jcool.dev.travel.iactivityview.TravelOrderDetailActivityView;
import com.jcool.dev.travel.persenter.TravelOrderDetailActivityPresenter;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelOrderDetailActivity extends BaseActivity implements TravelOrderDetailActivityView, View.OnClickListener {
    private TravelOrderDetailActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right_one)
    TextView icon_right_one;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_order_status)
    TextView tv_order_status;
    @BindView(R.id.tv_order_number)
    TextView tv_order_number;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.tv_travel_name)
    TextView tv_travel_name;
    @BindView(R.id.tv_data_day01)
    TextView tv_data_day01;
    @BindView(R.id.tv_data_day02)
    TextView tv_data_day02;
    @BindView(R.id.tv_week_day01)
    TextView tv_week_day01;
    @BindView(R.id.tv_week_day)
    TextView tv_week_day;
    @BindView(R.id.tv_data_day)
    TextView tv_data_day;
    @BindView(R.id.tv_user_number)
    TextView tv_user_number;
    @BindView(R.id.tv_link_name)
    TextView tv_link_name;
    @BindView(R.id.tv_link_phone)
    TextView tv_link_phone;
    @BindView(R.id.tv_link_email)
    TextView tv_link_email;
    @BindView(R.id.tv_btn_right)
    TextView tv_btn_right;
    @BindView(R.id.tv_btn_life)
    TextView tv_btn_life;
    @BindView(R.id.tv_btn_center)
    TextView tv_btn_center;
    @BindView(R.id.line_user)
    LinearLayout line_user;
    @BindView(R.id.rela_travel_info)
    RelativeLayout rela_travel_info;
    private String orderId;
    private String phoneNumber;
    private int totalAmount;
    private String goodsName;
    private String outOrderNo;
    private int typeOrder;
    private String goodsId;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.avtivity_travel_detail_order;
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
        mPresenter = new TravelOrderDetailActivityPresenter(this, this);

    }

    @Override
    protected void setListener() {
        icon_right_one.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        icon_title_back.setOnClickListener(this);
        tv_btn_life.setOnClickListener(this);
        tv_btn_center.setOnClickListener(this);
        tv_btn_right.setOnClickListener(this);
        rela_travel_info.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.getTravelOrderInfo(getToken(), orderId);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

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

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<TravelOrderInfo> mCallBackVo) {
        goodsId = mCallBackVo.getData().getGoodsId();
        totalAmount = mCallBackVo.getData().getTotalMoney();
        goodsName = mCallBackVo.getData().getGoodsName();
        outOrderNo = mCallBackVo.getData().getId();
        phoneNumber = mCallBackVo.getData().getLinkPhone();
        tv_order_number.setText(mCallBackVo.getData().getId());
        tv_money.setText("¥" + mCallBackVo.getData().getTotalMoney());
        tv_travel_name.setText("¥" + mCallBackVo.getData().getGoodsName());
        tv_data_day01.setText(AppUtils.getDate2String(mCallBackVo.getData().getFormDatetime(), "MM-dd日"));
        tv_data_day02.setText(AppUtils.getDate2String(mCallBackVo.getData().getFormDatetime(), "MM-dd日"));
        tv_week_day01.setText(AppUtils.getWeek(mCallBackVo.getData().getFormDatetime()) + "出发");
        tv_week_day.setText(AppUtils.getWeek(mCallBackVo.getData().getFormDatetime()) + "出发");
        tv_data_day.setText(AppUtils.getDate2String(mCallBackVo.getData().getFormDatetime(), "yyyy-MM-dd"));
        if (mCallBackVo.getData().getPeople() != null && mCallBackVo.getData().getPeople().size() > 0) {
            line_user.removeAllViews();
            int a = 0;//成人数量计算器
            int b = 0;//儿童数量计算器
            for (int i = 0; i < mCallBackVo.getData().getPeople().size(); i++) {//旅客年龄段(01:0-12周岁；02：儿童；03：成人)
                if (TextUtils.equals("03", mCallBackVo.getData().getPeople().get(i).getCustAge())) {
                    a += 1;
                } else {
                    b += 1;
                }

                View customerItem = LayoutInflater.from(TravelOrderDetailActivity.this).inflate(R.layout.xml_item_person, null);
                TextView tv_userName = customerItem.findViewById(R.id.tv_userName);
                TextView tv_userSex = customerItem.findViewById(R.id.tv_userSex);
                TextView tv_userType = customerItem.findViewById(R.id.tv_userType);
                TextView tv_userCordNumber = customerItem.findViewById(R.id.tv_userCordNumber);
                tv_userName.setText(mCallBackVo.getData().getPeople().get(i).getCustName());

                //客户类型(01:在职；02：在校学生；03：退休；04：学龄儿童)
                if (TextUtils.equals("01", mCallBackVo.getData().getPeople().get(i).getCustAge())) {
                    tv_userType.setText("年龄段：" + "0-12周岁");
                } else if (TextUtils.equals("02", mCallBackVo.getData().getPeople().get(i).getCustAge())) {
                    tv_userType.setText("年龄段：" + "儿童");
                } else if (TextUtils.equals("03", mCallBackVo.getData().getPeople().get(i).getCustAge())) {
                    tv_userType.setText("年龄段：" + "成人");
                }

                if (TextUtils.equals("girl", mCallBackVo.getData().getPeople().get(i).getCustSex())) {//旅客性别(boy,girl)
                    tv_userSex.setText("申请人性别：" + "女");
                } else {
                    tv_userSex.setText("申请人性别：" + "男");
                }
                tv_userCordNumber.setText("证件号码：" + mCallBackVo.getData().getPeople().get(i).getCustCert());
                tv_userName.setText("旅客姓名：" + mCallBackVo.getData().getPeople().get(i).getCustName());
                line_user.addView(customerItem);
            }
            tv_user_number.setText(a + "成人" + b + "儿童");
        } else {
            tv_user_number.setText(0 + "成人" + 0 + "儿童");
        }
        tv_link_name.setText(mCallBackVo.getData().getLinkMan());
        tv_link_phone.setText(mCallBackVo.getData().getLinkPhone());
        tv_link_email.setText(mCallBackVo.getData().getLinkMail());
        // （可多个，用逗号隔开）CREATE订单已经提交;PAY订单已经支付;REFUNDING退款中;REFUNDED已退款;USED订单已经出行;EVALUATE订单已经评价;CLOSE订单取消或是关闭
        if (TextUtils.equals("REFUNDING", mCallBackVo.getData().getState())) {
            tv_order_status.setText("退款中");
            tv_btn_life.setVisibility(View.GONE);
            tv_btn_center.setVisibility(View.GONE);
            tv_btn_life.setText("查看详情");
        } else if (TextUtils.equals("EVALUATE", mCallBackVo.getData().getState())) {
            tv_order_status.setText("已评价");
            tv_btn_life.setVisibility(View.GONE);
            tv_btn_center.setVisibility(View.GONE);
            tv_btn_life.setText("查看详情");
            tv_btn_right.setText("查看详情");
        } else if (TextUtils.equals("CLOSE", mCallBackVo.getData().getState())) {
            tv_order_status.setText("已取消");

            tv_btn_life.setVisibility(View.GONE);
            tv_btn_center.setVisibility(View.GONE);
            tv_btn_right.setText("查看详情");
        } else if (TextUtils.equals("PAY", mCallBackVo.getData().getState())) {
            tv_order_status.setText("待出行");
            tv_btn_life.setVisibility(View.VISIBLE);
            tv_btn_center.setVisibility(View.VISIBLE);

            tv_btn_life.setText("退款");
            tv_btn_center.setText("查看详情");
            tv_btn_right.setText("确认出行");
        } else if (TextUtils.equals("CREATE", mCallBackVo.getData().getState())) {
            tv_order_status.setText("待付款");
            tv_btn_life.setVisibility(View.VISIBLE);
            tv_btn_center.setVisibility(View.VISIBLE);

            tv_btn_life.setText("取消");
            tv_btn_center.setText("查看详情");
            tv_btn_right.setText("立即支付");
        } else if (TextUtils.equals("USED", mCallBackVo.getData().getState())) {
            tv_order_status.setText("待评价");
            tv_btn_life.setVisibility(View.GONE);
            tv_btn_center.setVisibility(View.VISIBLE);

            tv_btn_center.setText("查看详情");
            tv_btn_right.setText("去评价");
        }
    }

    @Override
    public void excuteSuccessOrderCallBack(CallBackVo<String> mCallBackVo) {
// （可多个，用逗号隔开）CREATE订单已经提交;PAY订单已经支付;REFUNDING退款中;REFUNDED已退款;USED订单已经出行;EVALUATE订单已经评价;CLOSE订单取消或是关闭
        if (typeOrder == 1) {
            ToastUtils.showShortToast("订单退款成功");
        } else if (typeOrder == 2) {
            ToastUtils.showShortToast("订单取消成功");
        } else if (typeOrder == 3) {
            ToastUtils.showShortToast("订单已经出行成功");
        }
        mPresenter.getTravelOrderInfo(getToken(), orderId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_right_one:
                Intent intent = new Intent(this, WebviewDefulitActivity.class);
                intent.putExtra("loadUrl", "http://p.qiao.baidu.com/cps/chatIndex");
                intent.putExtra("title", "在线客服");
                startActivity(intent);
                break;
            case R.id.icon_right:
                callPhone(phoneNumber);
                break;
            case R.id.icon_title_back:
                finish();
                break;
            case R.id.rela_travel_info:
                Intent intentInfo = new Intent(this, TravelDefuiltActivity.class);
                intentInfo.putExtra("travelId", goodsId);
                startActivity(intentInfo);
                break;
            case R.id.tv_btn_life:
                switch (tv_btn_life.getText().toString()) {
                    case "取消":
                        typeOrder = 2;
                        mPresenter.cancleVisaOrder(Constants.APP_HOME_API_TRAVEL_ORDER_CANCLE, getToken());
                        break;
                    case "退款":
                        typeOrder = 1;
                        mPresenter.refundVisaOrder(Constants.APP_HOME_API_TRAVEL_ORDER_REFUND, getToken());
                        break;
                }
                break;
            case R.id.tv_btn_center:
                finish();
                break;
            case R.id.tv_btn_right:
                switch (tv_btn_right.getText().toString()) {
                    case "立即支付":
                        Intent intentPay = new Intent(this, PayActivity.class);
                        intentPay.putExtra("totalAmount", totalAmount + "");
                        intentPay.putExtra("goodsName", goodsName + "");
                        intentPay.putExtra("outOrderNo", orderId + "");
                        intentPay.putExtra("productType", "01");
                        startActivity(intentPay);
                        break;
                    case "确认出行":
                        typeOrder = 3;
                        mPresenter.formVisaOrder(Constants.APP_HOME_API_TRAVEL_ORDER_FORM, getToken());
                        break;
                    case "去评价":
                        Intent intentEvaluate = new Intent(this, OrderEvaluateActivity.class);
                        intentEvaluate.putExtra("totalAmount", totalAmount + "");
                        intentEvaluate.putExtra("goodsName", goodsName + "");
                        intentEvaluate.putExtra("outOrderNo", orderId + "");
                        intentEvaluate.putExtra("productType", "01");
                        startActivity(intentEvaluate);
                        break;
                }
                break;
        }
    }
}
