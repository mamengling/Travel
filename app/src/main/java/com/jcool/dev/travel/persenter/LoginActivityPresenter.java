package com.jcool.dev.travel.persenter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.iactivityview.LoginActivityView;
import com.jcool.dev.travel.utils.Constants;
import com.jcool.dev.travel.utils.HttpUtil;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.klog.JsonLog;
import com.loopj.android.http.AsyncHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by MLing on 2018/5/10 0010.
 */

public class LoginActivityPresenter {
    private LoginActivityView mLoginActivityView;

    public LoginActivityPresenter(LoginActivityView mLoginActivityView) {
        this.mLoginActivityView = mLoginActivityView;
    }
}
