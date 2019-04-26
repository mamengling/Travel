package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.SetPasswordActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class SetPasswordActivityPresenter {
    private SetPasswordActivityView mSetPasswordActivityView;
    private Context mContext;

    public SetPasswordActivityPresenter(SetPasswordActivityView mSetPasswordActivityView, Context mContext) {
        this.mSetPasswordActivityView = mSetPasswordActivityView;
        this.mContext = mContext;
    }
    public void register() {
        mSetPasswordActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_VCODE_REGISTER, mSetPasswordActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mSetPasswordActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSetPasswordActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mSetPasswordActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mSetPasswordActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mSetPasswordActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mSetPasswordActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mSetPasswordActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }


    public void setPassword(RequestParams params, String token) {
        mSetPasswordActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_USER_UPDATE_PASS_WORD, token, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mSetPasswordActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSetPasswordActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mSetPasswordActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mSetPasswordActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mSetPasswordActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mSetPasswordActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mSetPasswordActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
