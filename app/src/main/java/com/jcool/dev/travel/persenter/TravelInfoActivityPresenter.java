package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.TravelInfoActivityView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;

import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TravelInfoActivityPresenter {
    private TravelInfoActivityView mTravelInfoActivityView;
    private Context mContext;

    public TravelInfoActivityPresenter(TravelInfoActivityView mTravelInfoActivityView, Context mContext) {
        this.mTravelInfoActivityView = mTravelInfoActivityView;
        this.mContext = mContext;
    }

    public void journeyTravelInfo(String id) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_INFO + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<TravelInfoBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelInfoBean>>() {
                    }.getType());
                    mTravelInfoActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelLineInfo(String id) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_GOODSSLINE_INFO + id, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<TravelInfoBean.LinesBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<TravelInfoBean.LinesBean>>() {
                    }.getType());
                    mTravelInfoActivityView.excuteSuccessLineCallBack(mCallBackVo);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void getCollectStatus(String id, String token) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_GET_COLLECT_STATUS + id, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();



                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<String> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<String>>() {
                    }.getType());
                    mTravelInfoActivityView.excuteSuccessCollectCallBack(mCallBackVo);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelTimeList(String id) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_TRAVEL_TIME_LIST, mTravelInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    CallBackVo<List<TravelInfoBean.GoodsAndDateBean>> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<List<TravelInfoBean.GoodsAndDateBean>>>() {
                    }.getType());
                    mTravelInfoActivityView.excuteSuccessGoodsCallBack(mCallBackVo);
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelCollect(String token) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_COLLECT_TRAVEL, token, mTravelInfoActivityView.getParamenters(), new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    mTravelInfoActivityView.excuteSuccessAddCollectCallBack(AppUtils.getFailure(gson, result));
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

    public void journeyTravelCollectDelete(JSONArray array, String token) {
        mTravelInfoActivityView.showProgress();
        HttpUtil.post(mContext, Constants.BASE_URL + Constants.APP_HOME_API_JOURNEY_COLLECT_DELETE, token, array, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mTravelInfoActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mTravelInfoActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mTravelInfoActivityView.closeProgress();
                Gson gson = new Gson();

                if (AppUtils.getFailure(gson, result).isSuccess()) {
                    mTravelInfoActivityView.excuteSuccessDelCollectCallBack(AppUtils.getFailure(gson, result));
                } else {
                    mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure(gson, result));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mTravelInfoActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mTravelInfoActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
