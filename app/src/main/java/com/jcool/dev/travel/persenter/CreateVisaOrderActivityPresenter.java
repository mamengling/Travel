package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.iactivityview.CreateVisaOrderActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CreateVisaOrderActivityPresenter {
    private CreateVisaOrderActivityView mCreateVisaOrderActivityView;
    private Context mContext;

    public CreateVisaOrderActivityPresenter(CreateVisaOrderActivityView mCreateVisaOrderActivityView, Context mContext) {
        this.mCreateVisaOrderActivityView = mCreateVisaOrderActivityView;
        this.mContext = mContext;
    }


    public void journeyPersonList(String token) {
        mCreateVisaOrderActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCreateVisaOrderActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCreateVisaOrderActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCreateVisaOrderActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<List<PersonInfoBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<PersonInfoBean>>>() {
                    }.getType());
                    mCreateVisaOrderActivityView.excuteSuccessPersonCallBack(mCallBackVo);
                } else {
                    mCreateVisaOrderActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCreateVisaOrderActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCreateVisaOrderActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void addVisaOrder(String token) {
        mCreateVisaOrderActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_VISA_ADD_ORDER, token, mCreateVisaOrderActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCreateVisaOrderActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCreateVisaOrderActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCreateVisaOrderActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mCreateVisaOrderActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mCreateVisaOrderActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCreateVisaOrderActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCreateVisaOrderActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
