package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoPay;
import com.jcool.dev.travel.bean.OrderInfoPayWx;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.PayActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class PayActivityPresenter {
    private PayActivityView mPayActivityView;
    private Context mContext;

    public PayActivityPresenter(PayActivityView mPayActivityView, Context mContext) {
        this.mPayActivityView = mPayActivityView;
        this.mContext = mContext;
    }

    /**
     * 创建支付宝预订单
     */
    public void createPreOrder() {
        mPayActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_ALIPAY_CREATE_PRE_ORDER, mPayActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mPayActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mPayActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mPayActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<OrderInfoPay> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<OrderInfoPay>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mPayActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mPayActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mPayActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mPayActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
    /**
     * 创建支付宝预订单
     */
    public void createPreWXOrder() {
        mPayActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_WX_CREATE_PRE_ORDER, mPayActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mPayActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mPayActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mPayActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<OrderInfoPayWx> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<OrderInfoPayWx>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mPayActivityView.excuteSuccessWxCallBack(mCallBackVo);
                } else {
                    mPayActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mPayActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mPayActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
