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
import com.jcool.dev.travel.adapter.SpecListAdapter;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.VisaInfoBean;
import com.jcool.dev.travel.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class ConstmChangeSpecPicker {
    private Context mContext;
    private Dialog mPickerDialog;
    private Callback mCallback;
    private ListView recyclerView;
    private ArrayList<VisaInfoBean.VisaSpecBean> mStrUnits = new ArrayList<>();
    private boolean mCanDialogShow;
    private String textChange;
    private int index = -1;
    private SpecListAdapter mAdapter;
    private PersonInfoBean item;

    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onSelected(VisaInfoBean.VisaSpecBean mStrUnits);
    }

    public ConstmChangeSpecPicker(Context mContext, Callback callback, ArrayList<VisaInfoBean.VisaSpecBean> mStrUnits) {
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
        mPickerDialog.setContentView(R.layout.dialog_spec_picker);
        Window window = mPickerDialog.getWindow();
        if (window != null) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.gravity = Gravity.BOTTOM;
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
        recyclerView = mPickerDialog.findViewById(R.id.recyclerView);
    }

    private void initData() {
        mAdapter = new SpecListAdapter(mContext, mStrUnits, new SpecListAdapter.Callback() {
            @Override
            public void setClick(View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_show:
                        mAdapter.changeState(position);
                        break;
                    case R.id.btn_to_pay_money:
                        mCallback.onSelected(mStrUnits.get(position));
                        break;
                }

                if (mPickerDialog != null && mPickerDialog.isShowing()) {
                    mPickerDialog.dismiss();
                }
            }
        });
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
