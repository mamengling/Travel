package com.jcool.dev.travel.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.jcool.dev.travel.bean.CallBackVo;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Common Utils
 * Created by blacktoast on 2018/2/23.
 */

public class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /*-------------------------------------resource---------------------------------------------*/

    /**
     * 获取配置的string
     * @param context   上下文
     * @param id        资源id
     */
    public static String getString(Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取配置的color
     * @param context   上下文
     * @param id        资源id
     */
    public static int getColor(Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }

    /**
     * 将资源图片转化为Bitmap
     * @param context   上下文
     * @param id        资源id
     */
    public static Bitmap getBitmap(Context context, @DrawableRes int id) {
        return ((BitmapDrawable) ContextCompat.getDrawable(context, id)).getBitmap();
    }

    /**
     * 获取app meta data
     * @param ctx   上下文
     * @param key   key
     * @return      meta data
     */
    public static String getAppMetaData(Context ctx, final String key) {
        try {
            ApplicationInfo info = ctx.getPackageManager().getApplicationInfo(ctx.getPackageName(),
                    PackageManager.GET_META_DATA);
            return info.metaData.getString(key);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /*--------------------------------------reflect---------------------------------------------*/

    /**
     * 获取反射字段
     * @param className 反射类
     * @param fieldName 字段名
     * @param obj       反射对象
     * @return          反射字段
     */
    public static Object getReflectField(String className, String fieldName, Object obj) {
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static CallBackVo getFailure() {
        CallBackVo mCallBackVo = new CallBackVo();
        mCallBackVo.setCode(404);
        mCallBackVo.setSuccess(true);
        mCallBackVo.setMsg("别着急哦~");
        mCallBackVo.setData(null);
        return mCallBackVo;
    }



    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);

        return str;
    }




    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }

    // 判断网络是否可用
    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivity = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivity != null) {
                NetworkInfo info = connectivity.getActiveNetworkInfo();
                if (info != null && info.isConnected()) {
                    if (info.getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    // WiFi是否连接
    public static boolean isWiFiAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isAvailable() && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return true;
        }
        return false;
    }

    // 获取当前应用的版本号
    public static String getVersionName(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            String version = packInfo.versionName;
            if (!TextUtils.isEmpty(version)) {
                return "V" + version;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "V1.0";
    }

    // 获取当前应用的版本号
    public static int getVersionCode(Context context) {
        try {
            PackageManager packageManager = context.getApplicationContext().getPackageManager();
            PackageInfo packInfo = packageManager.getPackageInfo(context.getApplicationContext().getPackageName(), 0);
            return packInfo.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }

}
