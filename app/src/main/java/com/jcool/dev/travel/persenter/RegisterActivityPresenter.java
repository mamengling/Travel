package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.iactivityview.RegisterActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;

public class RegisterActivityPresenter {
    private RegisterActivityView mRegisterActivityView;
    private Context mContext;

    public RegisterActivityPresenter(RegisterActivityView mRegisterActivityView, Context mContext) {
        this.mRegisterActivityView = mRegisterActivityView;
        this.mContext = mContext;
    }


    public void register() {
        mRegisterActivityView.showProgress();
        HttpUtil.get(mContext, Constants.BASE_URL + Constants.APP_HOME_API_VCODE_REGISTER, mRegisterActivityView.getParamentersRegister(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mRegisterActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mRegisterActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mRegisterActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mRegisterActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mRegisterActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void registerP(RequestParams params) {
        mRegisterActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_VCODE_CALIDATE, params, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mRegisterActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mRegisterActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mRegisterActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mRegisterActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mRegisterActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void registerGetCode(String phone) {
        mRegisterActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_VCODE_SEND_CODE + "?phone=" + phone, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mRegisterActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mRegisterActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mRegisterActivityView.closeProgress();
                Gson gson = new Gson();


                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<CodeBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<CodeBean>>() {
                    }.getType());
                    mRegisterActivityView.excuteSuccessCodeCallBack(mCallBackVo);
                } else {
                    mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mRegisterActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void verifyPhone() {
        mRegisterActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_PASSWORD_BACK, mRegisterActivityView.getParamentersPhone(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mRegisterActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mRegisterActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mRegisterActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    mRegisterActivityView.excuteSuccessCallBack(AppUtils.getFailure(gson, result));
                } else {
                    mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mRegisterActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void updatePhone(String token) {
        mRegisterActivityView.showProgress();
        HttpUtil.put(mContext, Constants.BASE_URL + Constants.APP_HOME_API_USER_UPDATE_PHONE, token, mRegisterActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mRegisterActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mRegisterActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mRegisterActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    mRegisterActivityView.excuteSuccessUpdateCallBack(AppUtils.getFailure(gson, result));
                } else {
                    mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mRegisterActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mRegisterActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
