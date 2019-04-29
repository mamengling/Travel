package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.iactivityview.SearchTravelActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class SearchTravelActivityPresenter {
    private SearchTravelActivityView mSearchTravelActivityView;
    private Context mContext;

    public SearchTravelActivityPresenter(SearchTravelActivityView mSearchTravelActivityView, Context mContext) {
        this.mSearchTravelActivityView = mSearchTravelActivityView;
        this.mContext = mContext;
    }



    public void journeyGoodsSales(String url) {
        mSearchTravelActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url, mSearchTravelActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mSearchTravelActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSearchTravelActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mSearchTravelActivityView.closeProgress();
                Gson gson = new Gson();
                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<TravelBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelBean>>() {
                    }.getType());
                    mSearchTravelActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mSearchTravelActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mSearchTravelActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mSearchTravelActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
