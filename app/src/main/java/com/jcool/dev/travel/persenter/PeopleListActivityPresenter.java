package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.iactivityview.PeopleListActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PeopleListActivityPresenter {
    private PeopleListActivityView mPeopleListActivityView;
    private Context mContext;

    public PeopleListActivityPresenter(PeopleListActivityView mPeopleListActivityView, Context mContext) {
        this.mPeopleListActivityView = mPeopleListActivityView;
        this.mContext = mContext;
    }


    public void journeyPersonList(String token) {
        mPeopleListActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mPeopleListActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mPeopleListActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mPeopleListActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<PersonInfoBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<PersonInfoBean>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mPeopleListActivityView.excuteSuccessPersonCallBack(mCallBackVo);
                } else {
                    mPeopleListActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mPeopleListActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mPeopleListActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyPersonDelete(String token, String id) {
        mPeopleListActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY_DELETE + id, token, mPeopleListActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mPeopleListActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mPeopleListActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mPeopleListActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mPeopleListActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mPeopleListActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mPeopleListActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mPeopleListActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
