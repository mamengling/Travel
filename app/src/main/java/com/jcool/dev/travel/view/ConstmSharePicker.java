package com.jcool.dev.travel.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.SpecListAdapter;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.VisaInfoBean;

import java.util.ArrayList;

public class ConstmSharePicker implements View.OnClickListener {
    private Context mContext;
    private Dialog mPickerDialog;
    private Callback mCallback;
    private boolean mCanDialogShow;
    private String textChange;
    private int index = -1;
    private TextView weiixin;
    private TextView pyq;
    private TextView qq;
    private TextView kongjian;
    private TextView cancle;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.weiixin:
                mCallback.onSelected(0);
                break;
            case R.id.pyq:
                mCallback.onSelected(1);
                break;
            case R.id.qq:
                mCallback.onSelected(2);
                break;
            case R.id.kongjian:
                mCallback.onSelected(3);
                break;
            case R.id.cancle:
                mPickerDialog.dismiss();
                break;
        }

        if (mPickerDialog != null && mPickerDialog.isShowing()) {
            mPickerDialog.dismiss();
        }
    }

    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onSelected(int type);
    }

    public ConstmSharePicker(Context mContext, Callback callback) {
        this.mContext = mContext;
        this.mCallback = callback;
        initView();
        initData();
        mCanDialogShow = true;
    }

    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.dialog_share_picker);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        weiixin = mPickerDialog.findViewById(R.id.weiixin);
        pyq = mPickerDialog.findViewById(R.id.pyq);
        qq = mPickerDialog.findViewById(R.id.qq);
        kongjian = mPickerDialog.findViewById(R.id.kongjian);
        cancle = mPickerDialog.findViewById(R.id.cancle);
    }

    private void initData() {
        weiixin.setOnClickListener(this);
        pyq.setOnClickListener(this);
        qq.setOnClickListener(this);
        kongjian.setOnClickListener(this);
        cancle.setOnClickListener(this);
    }

    /**
     * 设置是否允许点击屏幕或物理返回键关闭
     */
    public void setCancelable(boolean cancelable) {
        if (!canShow()) return;

        mPickerDialog.setCancelable(cancelable);
    }

    /**
     * 展示选择器
     */
    public void show() {
//        if (!canShow() || dateStr != null) return;
        mPickerDialog.show();
    }


    private boolean canShow() {
        return mCanDialogShow && mPickerDialog != null;
    }

}
