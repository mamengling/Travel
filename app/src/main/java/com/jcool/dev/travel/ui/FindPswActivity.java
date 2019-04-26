package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.iactivityview.FindPswActivityView;
import com.jcool.dev.travel.persenter.FindPswActivityPresenter;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindPswActivity extends BaseActivity implements View.OnClickListener, FindPswActivityView {
    private FindPswActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private String mStrPhone;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_find_psw;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTools() {
        ActivityCollector.getInstance().flagActivity(this);
        mPresenter = new FindPswActivityPresenter(this, this);
        ButterKnife.bind(this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_title_back:
                finish();
                break;
            case R.id.btn_commit:
                inputSuccessPhone();
                break;
        }
    }

    private void inputSuccessPhone() {
        mStrPhone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(mStrPhone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (mStrPhone.length() != 11) {
            ToastUtils.showShortToast("请输入正确的手机号");
            return;
        }

        Intent intent = new Intent(this, FindPswCodeActivity.class);
        intent.putExtra("phone", mStrPhone);
        startActivity(intent);
    }

    @Override
    public JSONObject getParamenters() {
        return null;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo) {

    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {

    }
}
