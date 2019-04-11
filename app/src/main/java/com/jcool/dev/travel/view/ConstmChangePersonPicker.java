package com.jcool.dev.travel.view;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.PersonListAdapter;
import com.jcool.dev.travel.bean.PersonInfoBean;

import java.util.ArrayList;
import java.util.List;

public class ConstmChangePersonPicker implements View.OnClickListener {
    private Context mContext;
    private Dialog mPickerDialog;
    private Callback mCallback;
    private TextView tv_title;
    private TextView tv_change_person;
    private ListView recyclerView;
    private List<PersonInfoBean> mStrUnits = new ArrayList<>();
    private boolean mCanDialogShow;
    private String textChange;
    private int index = -1;
    private PersonListAdapter mAdapter;
    private PersonInfoBean item;

    /**
     * 时间选择结果回调接口
     */
    public interface Callback {
        void onSelected(List<PersonInfoBean> mStrUnits);

        void onAdd(String mStrUnits);
    }

    public ConstmChangePersonPicker(Context mContext, Callback callback, List<PersonInfoBean> mStrUnits) {
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
            case R.id.tv_change_person:
                mCallback.onAdd("");
                break;
            case R.id.tv_cancel:
                mCallback.onSelected(null);
                break;
            case R.id.tv_confirm:
                if (mCallback != null) {
                    if (mAdapter != null) {
                        List<PersonInfoBean> mStr = new ArrayList<>();
                        for (int i = 0; i < mAdapter.getList().size(); i++) {
                            if (mAdapter.getList().get(i).isCheck()) {
                                mStr.add(mAdapter.getList().get(i));
                            }
                        }
                        mCallback.onSelected(mStr);
                    } else {
                        mCallback.onSelected(null);
                    }
                }
                break;
        }

        if (mPickerDialog != null && mPickerDialog.isShowing()) {
            mPickerDialog.dismiss();
        }
    }


    private void initView() {
        mPickerDialog = new Dialog(mContext, R.style.date_picker_dialog);
        mPickerDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mPickerDialog.setContentView(R.layout.dialog_person_picker);
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
        mPickerDialog.findViewById(R.id.tv_change_person).setOnClickListener(this);
        tv_title = mPickerDialog.findViewById(R.id.tv_title);
        recyclerView = mPickerDialog.findViewById(R.id.recyclerView);
        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mAdapter != null) {
                    mAdapter.changeState(position);
                }
            }
        });
    }

    private void initData() {
        mAdapter = new PersonListAdapter(mContext, mStrUnits);
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
