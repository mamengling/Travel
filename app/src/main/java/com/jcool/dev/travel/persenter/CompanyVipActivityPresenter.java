package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.CompanyVipActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CompanyVipActivityPresenter {
    private CompanyVipActivityView mCompanyVipActivityView;
    private Context mContext;

    public CompanyVipActivityPresenter(CompanyVipActivityView mCompanyVipActivityView, Context mContext) {
        this.mCompanyVipActivityView = mCompanyVipActivityView;
        this.mContext = mContext;
    }


    public void commitVip() {
        mCompanyVipActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_ADD, mCompanyVipActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCompanyVipActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCompanyVipActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCompanyVipActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo mCallBackVo = gson.fromJson(result, CallBackVo.class);
                if (mCallBackVo.isSuccess()) {
                    mCompanyVipActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mCompanyVipActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCompanyVipActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCompanyVipActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
