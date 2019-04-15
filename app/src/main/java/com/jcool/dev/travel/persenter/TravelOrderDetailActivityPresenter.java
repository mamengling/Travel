package com.jcool.dev.travel.persenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GroupBean;
import com.jcool.dev.travel.bean.TravelOrderInfo;
import com.jcool.dev.travel.bean.VisaOrderInfo;
import com.jcool.dev.travel.iactivityview.TravelOrderDetailActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class TravelOrderDetailActivityPresenter {
    private TravelOrderDetailActivityView mTravelOrderDetailActivityView;
    private Context mContext;

    public TravelOrderDetailActivityPresenter(TravelOrderDetailActivityView mTravelOrderDetailActivityView, Context mContext) {
        this.mTravelOrderDetailActivityView = mTravelOrderDetailActivityView;
        this.mContext = mContext;
    }


    public void getTravelOrderInfo(String token, String orderId) {
        mTravelOrderDetailActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_ORDER_JOURNEY_GOODS_FORM_QUERY_APP + orderId, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelOrderDetailActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelOrderDetailActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelOrderDetailActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<TravelOrderInfo> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelOrderInfo>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTravelOrderDetailActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mTravelOrderDetailActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelOrderDetailActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelOrderDetailActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }




    public void cancleVisaOrder(String url,String token) {
        mTravelOrderDetailActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mTravelOrderDetailActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelOrderDetailActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelOrderDetailActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelOrderDetailActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTravelOrderDetailActivityView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mTravelOrderDetailActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelOrderDetailActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelOrderDetailActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
    public void formVisaOrder(String url,String token) {
        mTravelOrderDetailActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mTravelOrderDetailActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelOrderDetailActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelOrderDetailActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelOrderDetailActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTravelOrderDetailActivityView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mTravelOrderDetailActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelOrderDetailActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelOrderDetailActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void refundVisaOrder(String url,String token) {
        mTravelOrderDetailActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mTravelOrderDetailActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelOrderDetailActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelOrderDetailActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelOrderDetailActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTravelOrderDetailActivityView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mTravelOrderDetailActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelOrderDetailActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelOrderDetailActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
