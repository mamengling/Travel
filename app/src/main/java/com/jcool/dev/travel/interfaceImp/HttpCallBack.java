package com.jcool.dev.travel.interfaceImp;

import cz.msebera.android.httpclient.HttpException;

public interface HttpCallBack {
    void onStart();

    void onLoading(long total, long current, boolean isUploading);

    void onCancelled();

    void onFailure(HttpException arg0, String arg1);

    void onSuccess(String obj);
}
