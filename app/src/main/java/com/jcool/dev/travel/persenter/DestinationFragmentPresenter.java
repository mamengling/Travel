package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.DestinationBean;
import com.jcool.dev.travel.iactivityview.DestinationFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DestinationFragmentPresenter {
    private DestinationFragmentView mDestinationFragmentView;
    private Context mContext;

    public DestinationFragmentPresenter(DestinationFragmentView mDestinationFragmentView, Context mContext) {
        this.mDestinationFragmentView = mDestinationFragmentView;
        this.mContext = mContext;
    }

    public void querySecond() {
        mDestinationFragmentView.showProgress();
        HttpUtil.post(Constants.BASE_URL + Constants.APP_HOME_API_TARGETPLACE_QUERY_SECOND, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mDestinationFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mDestinationFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mDestinationFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo callBackVo = gson.fromJson(result, CallBackVo.class);
                if (callBackVo.isSuccess()) {
                    List<DestinationBean> list = new ArrayList<>();
                    DestinationBean itemFirst = new DestinationBean();
                    itemFirst.setPlaceName("境内");
                    list.add(itemFirst);
                    CallBackVo<List<DestinationBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<DestinationBean>>>() {
                    }.getType());
                    list.addAll(mCallBackVo.getData());
                    mDestinationFragmentView.excuteSuccessCallBack(list);
                } else {
                    mDestinationFragmentView.excuteFailedCallBack(callBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mDestinationFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mDestinationFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
