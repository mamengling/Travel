package com.jcool.dev.travel.persenter;

import android.content.Context;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.DataImageInfo;
import com.jcool.dev.travel.bean.GroupBean;
import com.jcool.dev.travel.bean.VisaOrderInfo;
import com.jcool.dev.travel.iactivityview.VisaOrderDetailActivityView;
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

public class VisaOrderDetailActivityPresenter {
    private VisaOrderDetailActivityView mVisaOrderDetailActivityView;
    private Context mContext;

    public VisaOrderDetailActivityPresenter(VisaOrderDetailActivityView mVisaOrderDetailActivityView, Context mContext) {
        this.mVisaOrderDetailActivityView = mVisaOrderDetailActivityView;
        this.mContext = mContext;
    }


    public void getVisaOrderInfo(String token, String orderId) {
        mVisaOrderDetailActivityView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_GET_OTHER_ORDER_VISA_INFO + orderId, token, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mVisaOrderDetailActivityView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mVisaOrderDetailActivityView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mVisaOrderDetailActivityView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<VisaOrderInfo> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<VisaOrderInfo>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    if (mCallBackVo.getData() != null && mCallBackVo.getData().getCustomer() != null) {
                        for (int i = 0; i < mCallBackVo.getData().getCustomer().size(); i++) {
                            ArrayList<GroupBean> list = new ArrayList<>();
                            try {
                                JSONArray array = new JSONArray(mCallBackVo.getData().getCustomer().get(i).getData());
                                for (int j = 0; j < array.length(); j++) {
                                    JSONObject object = array.getJSONObject(j);
                                    GroupBean item = new GroupBean();
                                    item.setTitle(object.optString("name"));
                                    item.setContent(object.optString("content"));
                                    item.setIndex(object.optString("index"));
                                    item.setType(object.optString("type"));
                                    item.setInitRowIndex(object.optString("initRowIndex"));

                                    if (mCallBackVo.getData().getCustomer().get(i).getVisaOrderData() != null && mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().size() > 0) {
                                        List<String> itemList = new ArrayList<>();
                                        for (int k = 0; k < mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().size(); k++) {
                                            if (TextUtils.equals(mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().get(k).getDataName(), item.getTitle())) {
                                                String imageArr[] = null;
                                                if (!TextUtils.isEmpty(mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().get(k).getDataImage()) && mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().get(k).getDataImage().length() > 10) {
                                                    String image = mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().get(k).getDataImage().substring(0, (mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().get(k).getDataImage().length() - 1));
                                                    imageArr = image.split(",");
                                                    for (int l = 0; l < imageArr.length; l++) {
                                                        itemList.add(imageArr[l]);
                                                    }
                                                }
                                                item.setInfoList(itemList);
                                                item.setDataInfo(mCallBackVo.getData().getCustomer().get(i).getVisaOrderData().get(k));
                                            }
                                        }
                                        item.setInfoList(itemList);
                                    }
                                    list.add(item);
                                }
                                mCallBackVo.getData().getCustomer().get(i).setDataList(list);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }

                    mVisaOrderDetailActivityView.excuteSuccessCallBack(mCallBackVo);
                } else {
                    mVisaOrderDetailActivityView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mVisaOrderDetailActivityView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mVisaOrderDetailActivityView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }

}
