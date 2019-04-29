package com.jcool.dev.travel.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.base.OrderInfoPayYL;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoPay;
import com.jcool.dev.travel.bean.OrderInfoPayWx;
import com.jcool.dev.travel.iactivityview.PayActivityView;
import com.jcool.dev.travel.persenter.PayActivityPresenter;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.alipay.PayResult;
import com.jcool.dev.travel.view.CustomButtonDialog;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.unionpay.UPPayAssistEx;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PayActivity extends BaseActivity implements View.OnClickListener, PayActivityView {
    private static IWXAPI api;
    private PayActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.image_weixin)
    ImageView image_weixin;
    @BindView(R.id.image_alipay)
    ImageView image_alipay;
    @BindView(R.id.image_yinlian)
    ImageView image_yinlian;
    @BindView(R.id.line_weixin)
    LinearLayout line_weixin;
    @BindView(R.id.line_alipay)
    LinearLayout line_alipay;
    @BindView(R.id.line_yinlian)
    LinearLayout line_yinlian;
    private String money;
    private int payType = 1;
    private String goodsName;
    private String totalAmount;
    private String outOrderNo;
    private String productType;
    private String tn;
    private final String mMode = "00";

    @Override
    protected int getContentViewId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void getExtra() {
        money = getIntent().getStringExtra("totalAmount");
        goodsName = getIntent().getStringExtra("goodsName");
        outOrderNo = getIntent().getStringExtra("outOrderNo");
        totalAmount = getIntent().getStringExtra("totalAmount");
        productType = getIntent().getStringExtra("productType");//产品类型（01：旅游产品；02：签证产品）
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        api = WXAPIFactory.createWXAPI(this, AppConfigStatic.APP_ID);
        // 将该app注册到微信
        api.registerApp(AppConfigStatic.APP_ID);
        mPresenter = new PayActivityPresenter(this, this);
        tv_money.setText(money + "");
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        line_weixin.setOnClickListener(this);
        line_alipay.setOnClickListener(this);
        line_yinlian.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 101:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast("支付成功");
//                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        ToastUtils.showShortToast("支付失败" + resultStatus);
                        finish();
                    }
                    break;
                case 102:
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_weixin:
                image_weixin.setImageResource(R.mipmap.icon_pay_change);
                image_alipay.setImageResource(R.color.white);
                image_yinlian.setImageResource(R.color.white);
                payType = 2;
                break;
            case R.id.line_alipay:
                payType = 1;
                image_alipay.setImageResource(R.mipmap.icon_pay_change);
                image_weixin.setImageResource(R.color.white);
                image_yinlian.setImageResource(R.color.white);
                break;
            case R.id.line_yinlian:
                payType = 3;
                image_yinlian.setImageResource(R.mipmap.icon_pay_change);
                image_weixin.setImageResource(R.color.white);
                image_alipay.setImageResource(R.color.white);
                break;
            case R.id.btn_commit:
                if (payType == 2) {
//                    ToastUtils.showShortToast("微信支付");
                    mPresenter.createPreWXOrder();
                } else if (payType == 3) {
//                    ToastUtils.showShortToast("银联支付");
                    mPresenter.createPreYLOrder();
                } else {
//                    ToastUtils.showShortToast("支付宝支付");
                    mPresenter.createPreOrder();
                }
                break;
            case R.id.icon_title_back:
                final CustomButtonDialog dialog = new CustomButtonDialog(this);
                dialog.setText("放弃支付？");
                dialog.setLeftButtonText("取消");
                dialog.setLeftButtonTextColor(R.color.text_black);
                dialog.setRightButtonText("确定");
                dialog.setRightButtonTextColor(R.color.colorAccent);
                dialog.setButtonListener(new CustomButtonDialog.OnButtonListener() {
                    @Override
                    public void onLeftButtonClick(CustomButtonDialog var1) {
                        dialog.cancel();

                    }

                    @Override
                    public void onRightButtonClick(CustomButtonDialog var1) {
                        dialog.cancel();
                        Intent intent = new Intent(PayActivity.this, OrderTabActivity.class);
                        intent.putExtra("number", 1);
                        startActivity(intent);
                        finish();
                    }
                });
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("goodsName", goodsName);
            object.put("outOrderNo", outOrderNo);
            object.put("totalAmount", money);
            object.put("productType", productType);
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
    public void excuteSuccessCallBack(final CallBackVo<OrderInfoPay> mCallBackVo) {

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> payInfo = null;
                payInfo = alipay.payV2(mCallBackVo.getData().getOrderStr(), true);
                Message msg = new Message();
                msg.what = 101;
                msg.obj = payInfo;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public void excuteSuccessWxCallBack(CallBackVo<OrderInfoPayWx> mCallBackVo) {
        wechatPay(this, mCallBackVo.getData());
    }

    @Override
    public void excuteSuccessYlCallBack(CallBackVo<OrderInfoPayYL> mCallBackVo) {
        tn = mCallBackVo.getData().getTn();
        yinLPay();
    }

    private void yinLPay() {
        UPPayAssistEx.startPay(this, null, null, tn, mMode);
    }

    /**
     * 把支付信息提交给微信插件完成支付
     *
     * @throws JSONException
     */
    protected static void wechatPay(Context context, OrderInfoPayWx content) {
        PayReq payReq = new PayReq();
        payReq.appId = content.getAppid();
        payReq.partnerId = content.getMch_id();
        payReq.prepayId = content.getPrepay_id();
        payReq.packageValue = "Sign=WXPay";
        payReq.nonceStr = content.getNonceStr();
        payReq.timeStamp = content.getTimeStamp();

        payReq.sign = content.getPaySign();
        if (api == null) {
            api = WXAPIFactory.createWXAPI(context, payReq.appId);
            // 将该app注册到微信
            api.registerApp(payReq.appId);
        }
        boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (!isPaySupported) {
            Toast.makeText(context, "您没有安装微信或者微信版本太低", Toast.LENGTH_SHORT).show();
            return;
        }
        api.sendReq(payReq);
        Toast.makeText(context, "正在加载,请稍后...", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /*************************************************
         * 步骤3：处理银联手机支付控件返回的支付结果
         ************************************************/
        if (data == null) {
            return;
        }
        String msg = "";
        /*
         * 支付控件返回字符串:success、fail、cancel 分别代表支付成功，支付失败，支付取消
         */
        String str = data.getExtras().getString("pay_result");
        if (str.equalsIgnoreCase("success")) {

            // 如果想对结果数据验签，可使用下面这段代码，但建议不验签，直接去商户后台查询交易结果
            // result_data结构见c）result_data参数说明
            if (data.hasExtra("result_data")) {
                // 结果result_data为成功时，去商户后台查询一下再展示成功
                msg = "支付成功！";
            } else if (str.equalsIgnoreCase("fail")) {
                msg = "支付失败！";
            } else if (str.equalsIgnoreCase("cancel")) {
                msg = "用户取消了支付";
            }
            ToastUtils.showShortToast(msg);
            finish();
        }
    }
}