package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderTravelInfoBean;
import com.jcool.dev.travel.iactivityview.OtherTravelTabFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class OtherTravelTabFragmentPresenter {
    private OtherTravelTabFragmentView mOtherTravelTabFragmentView;
    private Context mContext;

    public OtherTravelTabFragmentPresenter(OtherTravelTabFragmentView mOtherTravelTabFragmentView, Context mContext) {
        this.mOtherTravelTabFragmentView = mOtherTravelTabFragmentView;
        this.mContext = mContext;
    }

    public void getOrderList(String url, String token) {
        mOtherTravelTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url, token, mOtherTravelTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherTravelTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherTravelTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherTravelTabFragmentView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<OrderTravelInfoBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<OrderTravelInfoBean>>() {
                    }.getType());
                    mOtherTravelTabFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherTravelTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }


    public void cancleVisaOrder(String url, String token) {
        mOtherTravelTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url, token, mOtherTravelTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherTravelTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherTravelTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherTravelTabFragmentView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mOtherTravelTabFragmentView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherTravelTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void formVisaOrder(String url, String token) {
        mOtherTravelTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url, token, mOtherTravelTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherTravelTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherTravelTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherTravelTabFragmentView.closeProgress();
                Gson gson = new Gson();


                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mOtherTravelTabFragmentView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherTravelTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void refundVisaOrder(String url, String token) {
        mOtherTravelTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url, token, mOtherTravelTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherTravelTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherTravelTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherTravelTabFragmentView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mOtherTravelTabFragmentView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherTravelTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherTravelTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
