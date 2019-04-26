package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.bean.UserEntity;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.iactivityview.LoginFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class LoginFragmentPresenter {
    private LoginFragmentView mLoginFragmentView;
    private Context mContext;

    public LoginFragmentPresenter(LoginFragmentView mLoginFragmentView, Context mContext) {
        this.mLoginFragmentView = mLoginFragmentView;
        this.mContext = mContext;
    }

    public void loginGetCode(String phone) {
        mLoginFragmentView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_VCODE_SEND_CODE + "?phone=" + phone, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mLoginFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mLoginFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mLoginFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<CodeBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<CodeBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mLoginFragmentView.excuteSuccessCodeCallBack(mCallBackVo);
                } else {
                    mLoginFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mLoginFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mLoginFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    /**
     * 验证码登录
     */
    public void loginCode(RequestParams params) {
        mLoginFragmentView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_LOGIN_CODE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mLoginFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mLoginFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mLoginFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<UserInfo> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<UserInfo>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mLoginFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mLoginFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mLoginFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mLoginFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    /**
     * 手机号密码登录
     */
    public void loginPhone() {
        mLoginFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_LOGIN, mLoginFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mLoginFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mLoginFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mLoginFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<UserInfo> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<UserInfo>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mLoginFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mLoginFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mLoginFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mLoginFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
