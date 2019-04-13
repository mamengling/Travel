package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.OrderInfoOthBean;
import com.jcool.dev.travel.iactivityview.OtherTabFragmentView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class OtherTabFragmentPresenter {
    private OtherTabFragmentView mOtherTabFragmentView;
    private Context mContext;

    public OtherTabFragmentPresenter(OtherTabFragmentView mOtherTabFragmentView, Context mContext) {
        this.mOtherTabFragmentView = mOtherTabFragmentView;
        this.mContext = mContext;
    }




    public void getOrderList(String url,String token) {
        mOtherTabFragmentView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + url,token ,mOtherTabFragmentView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mOtherTabFragmentView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mOtherTabFragmentView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mOtherTabFragmentView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<OrderInfoOthBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<OrderInfoOthBean>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mOtherTabFragmentView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mOtherTabFragmentView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mOtherTabFragmentView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mOtherTabFragmentView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
