package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.iactivityview.VisaFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class VisaFragmentPresenter {
    private VisaFragmentView mVisaFragmentView;
    private Context mContext;

    public VisaFragmentPresenter(VisaFragmentView mVisaFragmentView, Context mContext) {
        this.mVisaFragmentView = mVisaFragmentView;
        this.mContext = mContext;
    }


    public void journeyVisaInfo() {
        mVisaFragmentView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_VISA_VISAINFO_QUERY_SORT_REGION, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<VisaInfoDtoList>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<VisaInfoDtoList>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mVisaFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mVisaFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
