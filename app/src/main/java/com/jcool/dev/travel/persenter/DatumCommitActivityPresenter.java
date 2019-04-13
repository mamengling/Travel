package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.DatumCommitActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import cz.msebera.android.httpclient.Header;

public class DatumCommitActivityPresenter {
    private DatumCommitActivityView mDatumCommitActivityView;
    private Context mContext;

    public DatumCommitActivityPresenter(DatumCommitActivityView mDatumCommitActivityView, Context mContext) {
        this.mDatumCommitActivityView = mDatumCommitActivityView;
        this.mContext = mContext;
    }


    public void addVisaData(JSONArray array,String token) {
        mDatumCommitActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_VISA_OTHER_ORDER_COMMIT_DATA, token, array, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mDatumCommitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mDatumCommitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mDatumCommitActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mDatumCommitActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mDatumCommitActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mDatumCommitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mDatumCommitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
