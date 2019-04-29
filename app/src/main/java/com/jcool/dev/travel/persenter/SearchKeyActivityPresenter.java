package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CityBeanHot;
import com.jcool.dev.travel.iactivityview.SearchKeyActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class SearchKeyActivityPresenter {
    private SearchKeyActivityView mSearchKeyActivityView;
    private Context mContext;

    public SearchKeyActivityPresenter(SearchKeyActivityView mSearchKeyActivityView, Context mContext) {
        this.mSearchKeyActivityView = mSearchKeyActivityView;
        this.mContext = mContext;
    }

    public void getVisaCity(String id) {
        mSearchKeyActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_USER_GOODS_HOT_GOODSINFO_QUERY + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mSearchKeyActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSearchKeyActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mSearchKeyActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<List<CityBeanHot>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<CityBeanHot>>>() {
                    }.getType());
                    mSearchKeyActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mSearchKeyActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mSearchKeyActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mSearchKeyActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
