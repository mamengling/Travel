package com.jcool.dev.travel.view;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jcool.dev.travel.R;

import java.util.ArrayList;
import java.util.List;

public class MianChangeInputPicker implements View.OnClickListener, PickerView.OnSelectListener {
    private Context mContext;
    private Dialog mPickerDialog;
    private PickerView dpv_content;
    private TextView tv_title;
    private Callback mCallback;
    private List<String> mStrUnits = new ArrayList<String>();
    private boolean mCanDialogShow;
    private String textChange;
    private int index = -1;

    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onSelected(String tamp, int index);
    }

    public MianChangeInputPicker(Context mContext, Callback callback, List<String> mStrUnits) {
        this.mContext = mContext;
        this.mStrUnits = mStrUnits;
        this.mCallback = callback;
        initView();
        initData();
        mCanDialogShow = true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                mCallback.onSelected("", index);
                break;
            case R.id.tv_confirm:
                if (mCallback != null) {
                    mCallback.onSelected(textChange, index);
                }
                break;
        }

        if (mPickerDialog != null && mPickerDialog.isShowing()) {
            mPickerDialog.dismiss();
        }
    }

    @Override
    public void onSelect(View view, String selected) {
        if (view == null || TextUtils.isEmpty(selected)) return;
        textChange = selected;
//        mCallback.onSelected(textChange);
    }

    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.dialog_mian_picker);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        mPickerDialog.findViewById(R.id.tv_cancel).setOnClickListener(this);
        mPickerDialog.findViewById(R.id.tv_confirm).setOnClickListener(this);
        dpv_content = mPickerDialog.findViewById(R.id.dpv_content);
        tv_title = mPickerDialog.findViewById(R.id.tv_title);
        dpv_content.setOnSelectListener(this);
    }

    private void initData() {
        initDateUnits();
    }


    private void initDateUnits() {
        dpv_content.setDataList(mStrUnits);
        textChange = mStrUnits.get(0);
        dpv_content.setSelected(0);
        setCanScroll();
    }

    private void setCanScroll() {
        dpv_content.setCanScroll(mStrUnits.size() > 1);
    }

    /**
     * 设置日期控件是否可以循环滚动
     */
    public void setScrollLoop(boolean canLoop) {
        if (!canShow()) return;
        dpv_content.setCanScrollLoop(canLoop);
    }

    /**
     * 设置是否允许点击屏幕或物理返回键关闭
     */
    public void setCancelable(boolean cancelable) {
        if (!canShow()) return;

        mPickerDialog.setCancelable(cancelable);
    }

    /**
     * 设置标题
     */
    public void setTitle(String title) {
        if (!canShow() && tv_title != null) return;
        tv_title.setText(title);
    }

    /**
     * 设置日期控件是否展示滚动动画
     */
    public void setCanShowAnim(boolean canShowAnim) {
        if (!canShow()) return;
        dpv_content.setCanShowAnim(canShowAnim);
    }

    /**
     * 展示选择器
     *
     * @param dateStr 选中字符
     */
    public void show(String dateStr) {
        if (!canShow() || TextUtils.isEmpty(dateStr)) return;

        // 弹窗时，考虑用户体验，不展示滚动动画
        if (setSelected(dateStr, true)) {
            mPickerDialog.show();
        }
    }

    /**
     * 设置日期选择器的选中时间
     *
     * @param dateStr  日期字符串
     * @param showAnim 是否展示动画
     * @return 是否设置成功
     */
    public boolean setSelected(String dateStr, boolean showAnim) {
        return canShow() && !TextUtils.isEmpty(dateStr)
                && setSelecteds(dateStr, showAnim);
    }

    /**
     * 设置日期选择器的选中时间
     *
     * @param timestamp 毫秒级时间戳
     * @param showAnim  是否展示动画
     * @return 是否设置成功
     */
    public boolean setSelecteds(String timestamp, boolean showAnim) {
        if (!canShow()) return false;
        dpv_content.setDataList(mStrUnits);
        index = 0;
        for (int i = 0; i < mStrUnits.size(); i++) {
            if (TextUtils.equals(timestamp, mStrUnits.get(i))) {
                index = i;
            }
        }
        dpv_content.setSelected(index);
        return true;
    }


    private boolean canShow() {
        return mCanDialogShow && mPickerDialog != null;
    }

}
