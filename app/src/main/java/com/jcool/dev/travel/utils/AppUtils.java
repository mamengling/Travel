package com.jcool.dev.travel.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * Common Utils
 * Created by blacktoast on 2018/2/23.
 */

public class AppUtils {
    private AppUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 正则表达式：验证用户名
     */
    public static final String REGEX_USERNAME = "^[a-zA-Z]\\w{5,20}$";

    /**
     * 正则表达式：验证密码
     */
    public static final String REGEX_PASSWORD = "^[a-zA-Z0-9]{6,20}$";

    /**
     * 正则表达式：验证手机号
     */
    public static final String REGEX_MOBILE = "^((16[0-9])|(17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    /**
     * 正则表达式：验证汉字
     */
    public static final String REGEX_CHINESE = "^[\u4e00-\u9fa5],{0,}$";

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^\\d{18}$)|(^\\d{15}$)";

    /**
     * 正则表达式：验证URL
     */
    public static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";

    /**
     * 正则表达式：验证IP地址
     */
    public static final String REGEX_IP_ADDR = "(25[0-5]|2[0-4]\\d|[0-1]\\d{2}|[1-9]?\\d)";

    /*-------------------------------------resource---------------------------------------------*/

    /**
     * 获取配置的string
     *
     * @param context 上下文
     * @param id      资源id
     */
    public static String getString(Context context, @StringRes int id) {
        return context.getResources().getString(id);
    }

    /**
     * 获取配置的color
     *
     * @param context 上下文
     * @param id      资源id
     */
    public static int getColor(Context context, @ColorRes int id) {
        return ContextCompat.getColor(context, id);
    }

    /**
     * 将资源图片转化为Bitmap
     *
     * @param context 上下文
     * @param id      资源id
     */
    public static Bitmap getBitmap(Context context, @DrawableRes int id) {
        return ((BitmapDrawable) ContextCompat.getDrawable(context, id)).getBitmap();
    }

    /**
     * 获取app meta data
     *
     * @param ctx 上下文
     * @param key key
     * @return meta data
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
     *
     * @param className 反射类
     * @param fieldName 字段名
     * @param obj       反射对象
     * @return 反射字段
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
        mCallBackVo.setMsg("网络请求异常");
        mCallBackVo.setData(null);
        return mCallBackVo;
    }


    public static CallBackVo getFailure(Gson gson, String result) {
        CallBackVo mCallBackVo = gson.fromJson(result, CallBackVo.class);
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

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getTimeAtt(String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        String str = formatter.format(curDate);

        return str;
    }

    //天数计算
    public static String dayAdd(int days) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date dd = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dd);
        calendar.add(Calendar.DATE, days);
        String T1 = format.format(calendar.getTime());
        return T1;
    }

    /**
     * 时间戳转换成字符串
     *
     * @param time    1541569323155
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return 2018-11-07 13:42:03
     */
    public static String getDate2String(long time, String pattern) {
        Date date = new Date(time);
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.getDefault());
        /**
         * Locale.getDefault() 该方法获取本地国家代码 不需要在设置时区 如特殊设置 可放开 下面代码
         */
//        TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");//获取中国的时区
//        format.setTimeZone(timeZoneChina);//设置系统时区
        return format.format(date);
    }

    /**
     * 获取精确到秒的时间戳
     *
     * @return
     */
    public static String getSecondTimestampTwo() {
        Date date = new Date();
        if (null == date) {
            return "";
        }
        String timestamp = String.valueOf(date.getTime() / 1000);
        return timestamp;
    }

    /**
     * 字符串转换成时间戳
     *
     * @param dateString 2018-11-07 13:42:03,
     * @param pattern    yyyy-MM-dd HH:mm:ss
     * @return 1541569323000
     */
    public static long getString2Date(String dateString, String pattern) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }

    public static String getWeek(long time) {

        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(time));

        int year = cd.get(Calendar.YEAR); //获取年份
        int month = cd.get(Calendar.MONTH); //获取月份
        int day = cd.get(Calendar.DAY_OF_MONTH); //获取日期
        int week = cd.get(Calendar.DAY_OF_WEEK); //获取星期

        String weekString;
        switch (week) {
            case Calendar.SUNDAY:
                weekString = "周日";
                break;
            case Calendar.MONDAY:
                weekString = "周一";
                break;
            case Calendar.TUESDAY:
                weekString = "周二";
                break;
            case Calendar.WEDNESDAY:
                weekString = "周三";
                break;
            case Calendar.THURSDAY:
                weekString = "周四";
                break;
            case Calendar.FRIDAY:
                weekString = "周五";
                break;
            default:
                weekString = "周六";
                break;

        }

        return weekString;
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

    // 获取当前应用的版本号
    public static String getPhoneCode(String phone) {
        String phoneNumber = phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        System.out.println("正则phone中4*：" + phoneNumber);
        return phoneNumber;
    }

    /**
     * 从文件中读取地址数据
     */
    public static String initJsonData(Context mContext, String fileName) {
        String addressAllString = "";
        AssetManager assetManager = mContext.getAssets();
        try {
            InputStream is = assetManager.open(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer stringBuffer = new StringBuffer();
            String str = null;
            while ((str = br.readLine()) != null) {
                stringBuffer.append(str);
            }
            addressAllString = stringBuffer.toString();
            LogUtil.d("tag", "loadingLocalJson:字符串 " + addressAllString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addressAllString;
    }


    /**
     * 校验用户名
     *
     * @param username
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUsername(String username) {
        return Pattern.matches(REGEX_USERNAME, username);
    }

    /**
     * 校验密码
     *
     * @param password
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * 校验手机号
     *
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验邮箱
     *
     * @param email
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEmail(String email) {
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 校验汉字
     *
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isChinese(String chinese) {
        return Pattern.matches(REGEX_CHINESE, chinese);
    }

    /**
     * 校验身份证
     *
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }

    /**
     * 校验URL
     *
     * @param url
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isUrl(String url) {
        return Pattern.matches(REGEX_URL, url);
    }

    /**
     * 校验IP地址
     *
     * @param ipAddr
     * @return
     */
    public static boolean isIPAddr(String ipAddr) {
        return Pattern.matches(REGEX_IP_ADDR, ipAddr);
    }

    /**
     * 获取缓存大小
     *
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    /**
     * 清除缓存
     *
     * @param context
     */
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    // 获取文件大小
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "K";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "M";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

}
