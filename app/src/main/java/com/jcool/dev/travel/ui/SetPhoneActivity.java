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
import com.jcool.dev.travel.iactivityview.RegisterActivityView;
import com.jcool.dev.travel.persenter.RegisterActivityPresenter;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetPhoneActivity extends BaseActivity implements View.OnClickListener, RegisterActivityView {
    private RegisterActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private String mPhone;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_set_phone;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        ActivityCollector.getInstance().flagActivity(this);
        mPresenter = new RegisterActivityPresenter(this, this);
        if (isLogin() && getUserPhone() != null) {
            tv_phone.setText("当前绑定的手机号" + AppUtils.getPhoneCode(getUserPhone()));
        }
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
                inputSuccess();
                break;
        }
    }

    private void inputSuccess() {
        mPhone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (mPhone.length() != 11) {
            ToastUtils.showShortToast("请输入正确的手机号");
            return;
        }
        if (!TextUtils.equals(mPhone, getUserPhone())) {
            ToastUtils.showShortToast("请输入的手机号与当前绑定的手机号不一致");
            return;
        }
//        mPresenter.registerGetCode(mPhone);
        Intent intent = new Intent(this, InputCodeActivity.class);
        intent.putExtra("phone",getUserPhone());
        startActivity(intent);

    }

    @Override
    public JSONObject getParamentersRegister() {
        return null;
    }

    @Override
    public RequestParams getParamentersPhone() {
        return null;
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {

    }


    @Override
    public void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo) {
        setKey(mCallBackVo.getData().getToken());
        ToastUtils.showShortToast("验证码发送成功");
        Intent intent = new Intent(this, InputCodeActivity.class);
        intent.putExtra("phone",getUserPhone());
        startActivity(intent);
    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("phone", mPhone);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void closeProgress() {

    }

    @Override
    public void excuteFailedCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }
}
