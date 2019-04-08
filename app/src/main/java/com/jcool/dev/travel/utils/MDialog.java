package com.jcool.dev.travel.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.jcool.dev.travel.view.CustomProgressDialog;

/**
 * Author:zcmain on 2016/5/13 14:10
 * E-Mail:zcmain@163.com
 * 说明：
 */
public class MDialog {
    private volatile static MDialog mDialog = null;
    private static Context mContext;
    private CustomProgressDialog mProgressDialog;

    private MDialog() {

    }


    /**
     * @param context
     * @return
     */
//    public static MDialog getInstance(Context context) {
//        if (mDialog == null) {
//            synchronized (MDialog.class) {
//                if (mDialog == null) {
//                    mDialog = new MDialog();
//                }
//            }
//        }
//        mContext = context;
//        return mDialog;
//    }
    public static MDialog getInstance(Context context) {
        mContext = context;
        return SingletonHolder.sInstance;
    }

    private static class SingletonHolder {
        private static final MDialog sInstance = new MDialog();
    }

    /**
     * 打开弹窗
     * 作者：MLing
     * 创建时间 ：2017/6/9 16:56.
     */
    public void showProgressDialog() {
        closeProgressDialog();
        showProgressDialog(mContext, mProgressDialog);
    }

    /**
     * 关闭弹窗
     * 作者：MLing
     * 创建时间 ：2017/6/9 16:55.
     */
    public void closeProgressDialog() {
        if (mProgressDialog != null) {
            dismissProgressDialog(mProgressDialog);
            mProgressDialog = null;
        }
    }

    /**
     * @param msg
     */
    public void showToast(String msg) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @param msg
     */
    public void showToast(View view, String msg) {
        if (!TextUtils.isEmpty(msg)) {
//            Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
            Snackbar snackbar = Snackbar.make(view, msg, Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(Color.parseColor("#FEB500"));
            snackbar.show();
        }
    }



    //旋转进度条
    public static void showProgressDialog(Context context, final Dialog progressDialog) {
        progressDialog.setCancelable(false);
        progressDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (KeyEvent.KEYCODE_BACK == keyCode) {
                    progressDialog.dismiss();
                    return true;
                } else {
                    return false;
                }
            }
        });
        progressDialog.show();
    }

    public static void dismissProgressDialog(Dialog progressDialog) {
        if (null != progressDialog) {
            progressDialog.dismiss();
        }
    }
}
