package com.jcool.dev.travel.utils.log.klog;

import android.text.TextUtils;
import android.util.Log;

import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.utils.log.LogUtilHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;
//        Log.d(tag, "处理前： " + msg);
        try {
            if (!TextUtils.isEmpty(msg) && msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(LogUtil.JSON_INDENT);  //这一句代码好厉害，直接把json格式优化了：缩进，换行
            } else if (!TextUtils.isEmpty(msg) && msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(LogUtil.JSON_INDENT);//这一句代码好厉害，直接把json格式优化了
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }
        LogUtilHelper.printLine(tag, true);
        if (!TextUtils.isEmpty(message))
            Log.d(tag, message);
        if (!TextUtils.isEmpty(headString))
            Log.d(tag, headString);

        LogUtilHelper.printLine(tag, false);
    }
}
