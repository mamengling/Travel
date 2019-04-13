package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.bean.VisaTargetInfo;
import com.jcool.dev.travel.iactivityview.VisaCommitActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class VisaCommitActivityPresenter {
    private Context mContext;
    private VisaCommitActivityView mVisaCommitActivityView;

    public VisaCommitActivityPresenter(Context mContext, VisaCommitActivityView mVisaCommitActivityView) {
        this.mContext = mContext;
        this.mVisaCommitActivityView = mVisaCommitActivityView;
    }


    public void journeyVisaInfo() {
        mVisaCommitActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_VISA_TARGET_PLAACE_QUERY_SECOND_SORT, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaCommitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaCommitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaCommitActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<VisaTargetInfo>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<VisaTargetInfo>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mVisaCommitActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mVisaCommitActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaCommitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaCommitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyVisaAdd(String token) {
        mVisaCommitActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_VISA_OTHER_ORDER_DATA_ADD,token, mVisaCommitActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaCommitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaCommitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaCommitActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mVisaCommitActivityView.excuteSuccessAddCallBack(mCallBackVo);
                } else {
                    mVisaCommitActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaCommitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaCommitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyVisaGoodsList() {
        mVisaCommitActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_VISA_INFO_BY_PLACE, mVisaCommitActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaCommitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaCommitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaCommitActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<VisaInfoDtoList.VisaInfoDtoListBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<VisaInfoDtoList.VisaInfoDtoListBean>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mVisaCommitActivityView.excuteSuccessGoodsCallBack(mCallBackVo);
                } else {
                    mVisaCommitActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaCommitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaCommitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyPersonList(String token) {
        mVisaCommitActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_COSTOMERINFO_GETCUSTOMERBY, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaCommitActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaCommitActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaCommitActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<List<PersonInfoBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<PersonInfoBean>>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mVisaCommitActivityView.excuteSuccessPersonCallBack(mCallBackVo);
                } else {
                    mVisaCommitActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaCommitActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaCommitActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
