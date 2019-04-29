package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.YijianDefulitActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class YijianDefulitActivityPresenter {
    private YijianDefulitActivityView mYijianDefulitActivityView;
    private Context mContext;

    public YijianDefulitActivityPresenter(YijianDefulitActivityView mYijianDefulitActivityView, Context mContext) {
        this.mYijianDefulitActivityView = mYijianDefulitActivityView;
        this.mContext = mContext;
    }


    public void addFaceBack(String token) {
        mYijianDefulitActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_FACE_BACK, token, mYijianDefulitActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mYijianDefulitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mYijianDefulitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mYijianDefulitActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mYijianDefulitActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mYijianDefulitActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mYijianDefulitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mYijianDefulitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
