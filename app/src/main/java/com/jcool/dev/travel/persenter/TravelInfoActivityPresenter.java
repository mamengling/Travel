package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.TravelInfoActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class TravelInfoActivityPresenter {
    private TravelInfoActivityView mTravelInfoActivityView;
    private Context mContext;

    public TravelInfoActivityPresenter(TravelInfoActivityView mTravelInfoActivityView, Context mContext) {
        this.mTravelInfoActivityView = mTravelInfoActivityView;
        this.mContext = mContext;
    }

    public void journeyTravelInfo(String id) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_INFO + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<TravelInfoBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelInfoBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTravelInfoActivityView.excuteSuccessCallBack(mCallBackVo, null, null);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelLineInfo(String id) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_GOODSSLINE_INFO + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<TravelInfoBean.LinesBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelInfoBean.LinesBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTravelInfoActivityView.excuteSuccessCallBack(null, mCallBackVo, null);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelCollect() {
        mTravelInfoActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_COLLECT_TRAVEL, mTravelInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo mCallBackVo = gson.fromJson(result, CallBackVo.class);
                if (mCallBackVo.isSuccess()) {
                    mTravelInfoActivityView.excuteSuccessCallBack(null, null, mCallBackVo);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
