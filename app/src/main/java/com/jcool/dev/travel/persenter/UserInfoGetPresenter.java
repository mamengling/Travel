package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.UserInfoGetView;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

public class UserInfoGetPresenter {
    private UserInfoGetView mUserInfoGetView;
    private Context mContext;

    public UserInfoGetPresenter(UserInfoGetView mUserInfoGetView, Context mContext) {
        this.mUserInfoGetView = mUserInfoGetView;
        this.mContext = mContext;
    }

    public void getUserInfo(String userId) {
        mUserInfoGetView.showProgress();
        HttpUtil.get(Constants.BASE_URL + Constants.APP_HOME_API_USER_INFO + userId, new AsyncHttpResponseHandler() {
            @Override
            public void onStart() {
                super.onStart();
                mUserInfoGetView.showProgress();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mUserInfoGetView.closeProgress();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                LogUtil.i("Http", result);
                JsonLog.printJson("HttpJson", result, this.getRequestURI().toString());
                mUserInfoGetView.closeProgress();
                Gson gson = new Gson();
                CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo = gson.fromJson(result, new TypeToken<CallBackVo<UserInfo.UserInfoBean.SysUserBean>>() {
                }.getType());
                if (mCallBackVo.isSuccess()) {
                    mUserInfoGetView.excuteSuccessUserCallBack(mCallBackVo);
                } else {
                    mUserInfoGetView.excuteFailedCallBack(mCallBackVo);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                LogUtil.i("Http", "-----------------" + statusCode + "");
                LogUtil.i("Http", "-----------------" + error.getMessage() + "");
                mUserInfoGetView.closeProgress();
                JsonLog.printJson("TAG" + "[onError]", error.getMessage(), "");
                mUserInfoGetView.excuteFailedCallBack(AppUtils.getFailure());
            }
        });
    }
}
