package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaBean;
import com.jcool.dev.travel.iactivityview.CollectionVisaFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class CollectionVisaFragmentPresenter {
    private CollectionVisaFragmentView mCollectionVisaFragmentView;
    private Context mContext;

    public CollectionVisaFragmentPresenter(CollectionVisaFragmentView mCollectionVisaFragmentView, Context mContext) {
        this.mCollectionVisaFragmentView = mCollectionVisaFragmentView;
        this.mContext = mContext;
    }


    public void getCollectionVisaList(String token) {
        mCollectionVisaFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_VISA_COLLECTION, token, mCollectionVisaFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mCollectionVisaFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mCollectionVisaFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mCollectionVisaFragmentView.closeProgress();
                Gson gson = new Gson();


                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<VisaBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<VisaBean>>() {
                    }.getType());
                    mCollectionVisaFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mCollectionVisaFragmentView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mCollectionVisaFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mCollectionVisaFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
