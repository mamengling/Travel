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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
                    try {
                        JSONArray jsonArray = new JSONArray(mCallBackVo.getData().getWorkingData());
                        ArrayList<VisaInfoBean.VisaSpecBean> workList = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            VisaInfoBean.VisaSpecBean item = new VisaInfoBean.VisaSpecBean();
                            JSONObject obj = jsonArray.getJSONObject(i);
                            item.setName(obj.optString("name"));
                            item.setContent(obj.optString("content"));
                            item.setIndex(obj.optString("index"));
                            item.setInitRowIndex(obj.optString("initRowIndex"));
                            item.setType(obj.optInt("type"));
                            workList.add(item);
                        }
                        mCallBackVo.getData().setWorkingDataList(workList);
                        JSONArray jsonFree = new JSONArray(mCallBackVo.getData().getFreeData());
                        ArrayList<VisaInfoBean.VisaSpecBean> freeList = new ArrayList<>();
                        for (int i = 0; i < jsonFree.length(); i++) {
                            VisaInfoBean.VisaSpecBean item = new VisaInfoBean.VisaSpecBean();
                            JSONObject obj = jsonFree.getJSONObject(i);
                            item.setName(obj.optString("name"));
                            item.setContent(obj.optString("content"));
                            item.setIndex(obj.optString("index"));
                            item.setInitRowIndex(obj.optString("initRowIndex"));
                            item.setType(obj.optInt("type"));
                            freeList.add(item);
                        }
                        mCallBackVo.getData().setFreeDataList(freeList);
                        JSONArray visaSpec = new JSONArray(mCallBackVo.getData().getVisaSpec());
                        ArrayList<VisaInfoBean.VisaSpecBean> visaSpecList = new ArrayList<>();
                        for (int i = 0; i < visaSpec.length(); i++) {
                            VisaInfoBean.VisaSpecBean item = new VisaInfoBean.VisaSpecBean();
                            JSONObject obj = visaSpec.getJSONObject(i);
                            item.setName(obj.optString("name"));
                            item.setContent(obj.optString("content"));
                            item.setIndex(obj.optString("index"));
                            item.setInitRowIndex(obj.optString("initRowIndex"));
                            item.setPrice(obj.optString("price"));
                            visaSpecList.add(item);
                        }
                        mCallBackVo.getData().setVisaSpecList(visaSpecList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
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
