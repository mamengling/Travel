package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.iactivityview.CollectionGoodsFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CollectionGoodsFragmentPresenter {
    private CollectionGoodsFragmentView mCollectionGoodsFragmentView;
    private Context mContext;

    public CollectionGoodsFragmentPresenter(CollectionGoodsFragmentView mCollectionGoodsFragmentView, Context mContext) {
        this.mCollectionGoodsFragmentView = mCollectionGoodsFragmentView;
        this.mContext = mContext;
    }



    public void getCollectionTravelList(String token) {
        mCollectionGoodsFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_COLLECTION, token, mCollectionGoodsFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCollectionGoodsFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCollectionGoodsFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCollectionGoodsFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<TravelBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mCollectionGoodsFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mCollectionGoodsFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCollectionGoodsFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCollectionGoodsFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
