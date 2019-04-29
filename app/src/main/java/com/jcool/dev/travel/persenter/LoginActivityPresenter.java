package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.LoginActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by MLing on 2018/5/10 0010.
 */

public class LoginActivityPresenter {
    private LoginActivityView mLoginActivityView;
    private Context mContext;

    public LoginActivityPresenter(LoginActivityView mLoginActivityView, Context mContext) {
        this.mLoginActivityView = mLoginActivityView;
        this.mContext = mContext;
    }




    public void wxLogin() {
        mLoginActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_WX_LOGIN, mLoginActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mLoginActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mLoginActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mLoginActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
//                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
//                    }.getType());
//                    mLoginActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mLoginActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mLoginActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mLoginActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
