package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.RePasswordActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class RePasswordActivityPrensenter {
    private RePasswordActivityView mRePasswordActivityView;
    private Context mContext;

    public RePasswordActivityPrensenter(RePasswordActivityView mRePasswordActivityView, Context mContext) {
        this.mRePasswordActivityView = mRePasswordActivityView;
        this.mContext = mContext;
    }


    public void verifyPassword(RequestParams params, String token) {
        mRePasswordActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_USER_CHECK_PASS_WORD, token, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mRePasswordActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mRePasswordActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mRePasswordActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mRePasswordActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mRePasswordActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mRePasswordActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mRePasswordActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
