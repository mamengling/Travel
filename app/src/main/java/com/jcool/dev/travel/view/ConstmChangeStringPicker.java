package com.jcool.dev.travel.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.PersonListAdapter;
import com.jcool.dev.travel.adapter.StringListAdapter;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.StringBean;
import com.jcool.dev.travel.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class ConstmChangeStringPicker {
    private Context mContext;
    private Dialog mPickerDialog;
    private Callback mCallback;
    private ListView recyclerView;
    private List<StringBean> mStrUnits = new ArrayList<>();
    private boolean mCanDialogShow;
    private String textChange;
    private int index = -1;
    private StringListAdapter mAdapter;
    private StringBean item;

    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onSelected(StringBean mStrUnits);

        void onAdd(String mStrUnits);
    }

    public ConstmChangeStringPicker(Context mContext, Callback callback, List<StringBean> mStrUnits) {
        this.mContext = mContext;
        this.mStrUnits = mStrUnits;
        this.mCallback = callback;
        initView();
        initData();
        mCanDialogShow = true;

    }

    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.dialog_str_picker);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        recyclerView = mPickerDialog.findViewById(R.id.recyclerView);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAdapter != null) {
                    if (mCallback != null) {
                        mCallback.onSelected(mStrUnits.get(position));
                    }
                }
                if (mPickerDialog != null && mPickerDialog.isShowing()) {
                    mPickerDialog.dismiss();
                }
            }
        });
    }

    private void initData() {
        mAdapter = new StringListAdapter(mContext, mStrUnits);
        recyclerView.setAdapter(mAdapter);
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
