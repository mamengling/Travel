package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.iactivityview.FindPswActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class FindPswActivityPresenter {
    private FindPswActivityView mFindPswActivityView;
    private Context mContext;

    public FindPswActivityPresenter(FindPswActivityView mFindPswActivityView, Context mContext) {
        this.mFindPswActivityView = mFindPswActivityView;
        this.mContext = mContext;
    }


    public void verifyCode(RequestParams params) {
        mFindPswActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_VCODE_CALIDATE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mFindPswActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mFindPswActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mFindPswActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mFindPswActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mFindPswActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mFindPswActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mFindPswActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }



    public void registerGetCode(String phone) {
        mFindPswActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_VCODE_SEND_CODE + "?phone=" + phone, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mFindPswActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mFindPswActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mFindPswActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<CodeBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<CodeBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mFindPswActivityView.excuteSuccessCodeCallBack(mCallBackVo);
                } else {
                    mFindPswActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mFindPswActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mFindPswActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
