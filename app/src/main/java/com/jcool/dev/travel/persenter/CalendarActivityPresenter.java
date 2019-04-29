package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.iactivityview.CalendarActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class CalendarActivityPresenter {
    private CalendarActivityView mCalendarActivityView;
    private Context mContext;

    public CalendarActivityPresenter(CalendarActivityView mCalendarActivityView, Context mContext) {
        this.mCalendarActivityView = mCalendarActivityView;
        this.mContext = mContext;
    }


    public void journeyTravelTimeList(String id) {
        mCalendarActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_TIME_LIST, mCalendarActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCalendarActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCalendarActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCalendarActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<TravelInfoBean.GoodsAndDateBean>>>() {
                    }.getType());
                    mCalendarActivityView.excuteSuccessGoodsCallBack(mCallBackVo);
                } else {
                    mCalendarActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCalendarActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCalendarActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelTime(String id) {
        mCalendarActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_TIME_LIST, mCalendarActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCalendarActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCalendarActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCalendarActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<TravelInfoBean.GoodsAndDateBean>>>() {
                    }.getType());
                    mCalendarActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mCalendarActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCalendarActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCalendarActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelTimeInfo(String id) {
        mCalendarActivityView.showProgress();
        HttpUtil.get(mContext, Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_TIME_LIST_INFO + id, mCalendarActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCalendarActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCalendarActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCalendarActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<TravelInfoBean.GoodsAndDateBean>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mCalendarActivityView.excuteSuccessGoodsCallBack(mCallBackVo);
                } else {
                    mCalendarActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCalendarActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCalendarActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
