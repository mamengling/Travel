package com.jcool.dev.travel.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jcool.dev.travel.R;

public class CameraPhotoPicker implements View.OnClickListener {
    private Context mContext;
    private Dialog mPickerDialog;
    private Callback mCallback;
    private boolean mCanDialogShow;
    private String textChange;
    private TextView pic_photo;
    private TextView pic_tuku;
    private TextView pic_cancel;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pic_photo:
                mCallback.onSelected(101);
                break;
            case R.id.pic_tuku:
                mCallback.onSelected(102);
                break;
            case R.id.pic_cancel:
                mCallback.onSelected(103);
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
        void onSelected(int mStrUnits);
    }

    public CameraPhotoPicker(Context mContext, Callback callback) {
        this.mContext = mContext;
        this.mCallback = callback;
        initView();
        initData();
        mCanDialogShow = true;

    }

    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.register_pick_pic);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        pic_photo = mPickerDialog.findViewById(R.id.pic_photo);
        pic_tuku = mPickerDialog.findViewById(R.id.pic_tuku);
        pic_cancel = mPickerDialog.findViewById(R.id.pic_cancel);

    }

    private void initData() {
        pic_cancel.setOnClickListener(this);
        pic_tuku.setOnClickListener(this);
        pic_photo.setOnClickListener(this);
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
        mPickerDialog.show();
    }


    private boolean canShow() {
        return mCanDialogShow && mPickerDialog != null;
    }

}
