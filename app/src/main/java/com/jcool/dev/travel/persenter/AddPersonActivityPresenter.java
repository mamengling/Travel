package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.AddPersonActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class AddPersonActivityPresenter {
    private AddPersonActivityView mAddPersonActivityView;
    private Context mContext;

    public AddPersonActivityPresenter(AddPersonActivityView mAddPersonActivityView, Context mContext) {
        this.mAddPersonActivityView = mAddPersonActivityView;
        this.mContext = mContext;
    }


    public void addPerson(String token) {
        mAddPersonActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_COSTOMER_PERSON_ADD, token, mAddPersonActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mAddPersonActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mAddPersonActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mAddPersonActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mAddPersonActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mAddPersonActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mAddPersonActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mAddPersonActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void updatePerson(String token) {
        mAddPersonActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY_UPDATE, token, mAddPersonActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mAddPersonActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mAddPersonActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mAddPersonActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mAddPersonActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mAddPersonActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mAddPersonActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mAddPersonActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
