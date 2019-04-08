package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GoodsBean;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.iactivityview.HomeTabFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class HomeTabFragmentPresenter {
    private HomeTabFragmentView mTabView;
    private Context mContext;

    public HomeTabFragmentPresenter(HomeTabFragmentView mTabView, Context mContext) {
        this.mTabView = mTabView;
        this.mContext = mContext;
    }


    public void journeyGoodsSales(String url) {
        mTabView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url, mTabView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTabView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTabView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTabView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<TravelBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mTabView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mTabView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTabView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTabView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
