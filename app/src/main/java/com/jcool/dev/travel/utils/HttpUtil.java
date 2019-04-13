package com.jcool.dev.travel.utils;


import android.content.Context;

import com.jcool.dev.travel.utils.log.LogUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.ParseException;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

/**
 * Created by MLing on 2016/7/20.
 */
public class HttpUtil {

    private static AsyncHttpClient httpclient = new AsyncHttpClient();

    static {
        httpclient.setTimeout(6000 * 10);
        httpclient.addHeader("Content-Type", "application/json");
    }

    public static void get(String urlString, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象

    {
        LogUtil.i(urlString);
        httpclient.get(urlString, res);
    }

    public static void get(String urlString,String token, AsyncHttpResponseHandler res) // 用一个完整url获取一个string对象

    {
        LogUtil.i(urlString);
        httpclient.addHeader(AppConfigStatic.USER_TOKEN_KEY, token);
        httpclient.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params,
                           AsyncHttpResponseHandler res) // url里面带参数
    {
        LogUtil.i(urlString + "?" + params.toString());
        httpclient.get(urlString, params, res);
    }


    public static void get(String urlString, JsonHttpResponseHandler res) // 不带参数，获取json对象或者数组
    {
        LogUtil.i(urlString);
        httpclient.get(urlString, res);
    }

    public static void get(String urlString, RequestParams params,
                           JsonHttpResponseHandler res) // 带参数，获取json对象或者数组
    {
        LogUtil.i(urlString + "?" + params.toString());
        httpclient.get(urlString, params, res);
    }

    public static void get(String uString, BinaryHttpResponseHandler bHandler) // 下载数据使用，会返回byte数据
    {
        LogUtil.i(uString);
        httpclient.get(uString, bHandler);
    }

    public static void post(String uString,
                            JsonHttpResponseHandler bHandler) // post数据使用，会返回json数据
    {
        LogUtil.i(uString);
        httpclient.post(uString, bHandler);
    }

    public static void post(String uString, RequestParams params,
                            AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        httpclient.post(uString, params, res);
    }

    /**
     * 请求体
     *
     * @param mContext
     * @param uString
     * @param params
     * @param res
     */
    public static void post(Context mContext, String uString,String token, JSONObject params,
                            AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i(uString + "?" + params.toString());
        //创建ByteArrayEntity对象
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(params.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpclient.addHeader(AppConfigStatic.USER_TOKEN_KEY, token);
        httpclient.post(mContext, uString, entity, "application/json", res);
    }

    /**
     * 请求体
     *
     * @param mContext
     * @param uString
     * @param params
     * @param res
     */
    public static void post(Context mContext, String uString,String token, JSONArray params,
                            AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i(uString + "?" + params.toString());
        //创建ByteArrayEntity对象
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(params.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpclient.addHeader(AppConfigStatic.USER_TOKEN_KEY, token);
        httpclient.post(mContext, uString, entity, "application/json", res);
    }


    /**
     * 请求体
     *
     * @param mContext
     * @param uString
     * @param params
     * @param res
     */
    public static void post(Context mContext, String uString, JSONObject params,
                            AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i(uString + "?" + params.toString());
        //创建ByteArrayEntity对象
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(params.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpclient.post(mContext, uString, entity, "application/json", res);
    }


    /**
     * 请求体
     *
     * @param mContext
     * @param uString
     * @param params
     * @param res
     */
    public static void get(Context mContext, String uString, JSONObject params,
                           AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i(uString + "?" + params.toString());
        //创建ByteArrayEntity对象
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(params.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpclient.addHeader("access-token", "d19575c9-0aad-4876-8549-b359e4f193d6");
        httpclient.get(mContext, uString, entity, "application/json", res);
    }

    /**
     * 请求体
     *
     * @param mContext
     * @param uString
     * @param params
     * @param res
     */
    public static void get(Context mContext, String uString, String token, JSONObject params,
                           AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i(uString + "?" + params.toString());
        //创建ByteArrayEntity对象
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(params.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpclient.addHeader(AppConfigStatic.USER_TOKEN_KEY, token);
        httpclient.get(mContext, uString, entity, "application/json", res);
    }


    public static void post(String uString, AsyncHttpResponseHandler res) // post数据使用，返回普通的手
    {
        LogUtil.i(uString);
        httpclient.post(uString, res);
    }

    public static AsyncHttpClient getClient() {
        return httpclient;
    }

}
