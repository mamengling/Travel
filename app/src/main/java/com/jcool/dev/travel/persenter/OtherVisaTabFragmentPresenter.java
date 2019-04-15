package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderVisaInfo;
import com.jcool.dev.travel.iactivityview.OtherVisaTabFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class OtherVisaTabFragmentPresenter {
    private OtherVisaTabFragmentView mOtherVisaTabFragmentView;
    private Context mContext;

    public OtherVisaTabFragmentPresenter(OtherVisaTabFragmentView mOtherVisaTabFragmentView, Context mContext) {
        this.mOtherVisaTabFragmentView = mOtherVisaTabFragmentView;
        this.mContext = mContext;
    }

    public void getOrderList(String url,String token) {
        mOtherVisaTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mOtherVisaTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherVisaTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherVisaTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherVisaTabFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<OrderVisaInfo> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<OrderVisaInfo>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mOtherVisaTabFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mOtherVisaTabFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherVisaTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherVisaTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void cancleVisaOrder(String url,String token) {
        mOtherVisaTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mOtherVisaTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherVisaTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherVisaTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherVisaTabFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mOtherVisaTabFragmentView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mOtherVisaTabFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherVisaTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherVisaTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

 public void refundVisaOrder(String url,String token) {
        mOtherVisaTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mOtherVisaTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherVisaTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherVisaTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherVisaTabFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mOtherVisaTabFragmentView.excuteSuccessOrderCallBack(mCallBackVo);
                } else {
                    mOtherVisaTabFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherVisaTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherVisaTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }


}
