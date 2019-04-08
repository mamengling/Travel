package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.BannerBean;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.GoodsBean;
import com.jcool.dev.travel.iactivityview.HomeFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class HomeFragmentPresenter {
    private HomeFragmentView fragmentView;
    private Context mContext;

    public HomeFragmentPresenter(HomeFragmentView fragmentView, Context mContext) {
        this.fragmentView = fragmentView;
        this.mContext = mContext;
    }


    public void getBanner() {
        fragmentView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_BANNER, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                fragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                fragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                fragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<BannerBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<ArrayList<BannerBean>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    fragmentView.excuteSuccessCallBack(mCallBackVo, null);
                } else {
                    fragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                fragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                fragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyGoodsSales() {
        fragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_GOODS_SALES_QUERY, fragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                fragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                fragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                fragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<GoodsBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<GoodsBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    fragmentView.excuteSuccessCallBack(null, mCallBackVo);
                } else {
                    fragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                fragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                fragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
