package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaBean;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.iactivityview.VisaInfoActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class VisaInfoActivityPresenter {
    private Context mContext;
    private VisaInfoActivityView mVisaInfoActivityView;

    public VisaInfoActivityPresenter(Context mContext, VisaInfoActivityView mVisaInfoActivityView) {
        this.mContext = mContext;
        this.mVisaInfoActivityView = mVisaInfoActivityView;
    }

    public void journeyVisaInfo(String id) {
        mVisaInfoActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_VISA_INFO + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaInfoActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<VisaInfoBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<VisaInfoBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mVisaInfoActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mVisaInfoActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
