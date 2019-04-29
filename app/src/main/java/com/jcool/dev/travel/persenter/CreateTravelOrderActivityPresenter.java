package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.TravelOrderInfoAdd;
import com.jcool.dev.travel.iactivityview.CreateTravelOrderActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CreateTravelOrderActivityPresenter {
    private CreateTravelOrderActivityView mCreateTravelOrderActivityView;
    private Context mContext;

    public CreateTravelOrderActivityPresenter(CreateTravelOrderActivityView mCreateTravelOrderActivityView, Context mContext) {
        this.mCreateTravelOrderActivityView = mCreateTravelOrderActivityView;
        this.mContext = mContext;
    }

    public void journeyPersonList(String token) {
        mCreateTravelOrderActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCreateTravelOrderActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCreateTravelOrderActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCreateTravelOrderActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<List<PersonInfoBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<PersonInfoBean>>>() {
                    }.getType());
                    mCreateTravelOrderActivityView.excuteSuccessPersonCallBack(mCallBackVo);
                } else {
                    mCreateTravelOrderActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCreateTravelOrderActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCreateTravelOrderActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }


    public void addTravelOrder(String token) {
        mCreateTravelOrderActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_TORDER, token, mCreateTravelOrderActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCreateTravelOrderActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCreateTravelOrderActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCreateTravelOrderActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<TravelOrderInfoAdd> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelOrderInfoAdd>>() {
                    }.getType());
                    mCreateTravelOrderActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mCreateTravelOrderActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCreateTravelOrderActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCreateTravelOrderActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
