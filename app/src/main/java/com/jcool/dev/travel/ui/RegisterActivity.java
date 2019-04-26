package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.adapter.ViewPagerFragmentAdapter;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.bean.InfoColumn;
import com.jcool.dev.travel.fragment.LoginAccFragment;
import com.jcool.dev.travel.fragment.LoginCodeFragment;
import com.jcool.dev.travel.iactivityview.RegisterActivityView;
import com.jcool.dev.travel.persenter.RegisterActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterActivityView {
    private RegisterActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_code)
    EditText edt_code;
    @BindView(R.id.btn_code)
    Button btn_code;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private String mStrPhone;
    private String mStrCode;
    private TimeCount time;
    private String phone;
    private String username;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_register;
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
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象

        icon_title.setText("注册");
        mPresenter = new RegisterActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        btn_code.setOnClickListener(this);
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
            case R.id.btn_code:
                inputSuccessPhone();
                break;
            case R.id.btn_commit:
                inputSuccessCode();
                break;
        }
    }

    private void inputSuccessCode() {
        mStrPhone = edt_phone.getText().toString().trim();
        mStrCode = edt_code.getText().toString().trim();
        if (TextUtils.isEmpty(mStrPhone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (mStrPhone.length() != 11) {
            ToastUtils.showShortToast("请输入正确的手机号");
            return;
        }
        if (TextUtils.isEmpty(mStrCode)) {
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        RequestParams object = new RequestParams();
        object.put("phone", mStrPhone);
        object.put("token", getKey());
        object.put("code", mStrCode);
        mPresenter.registerP(object);
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
        mPresenter.registerGetCode(mStrPhone);
    }

    @Override
    public JSONObject getParamentersRegister() {
        JSONObject object = new JSONObject();
        try {
            object.put("phone", mStrPhone);
            object.put("token", getKey());
            object.put("code", mStrCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public RequestParams getParamentersPhone() {
        return null;
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {

        Intent intent = new Intent(this, SetPasswordActivity.class);
        intent.putExtra("phone", mStrPhone);
        intent.putExtra("getFlag", 101);
        startActivity(intent);
        finish();
    }

    @Override
    public void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo) {
        setKey(mCallBackVo.getData().getToken());
        ToastUtils.showShortToast("验证码发送成功");
        time.start();
    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {

    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("phone", mStrPhone);
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


    /**
     * 计时器
     */
    class TimeCount extends CountDownTimer {
        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);//参数依次为总时长,和计时的时间间隔
        }

        @Override
        public void onFinish() {//计时完毕时触发
            btn_code.setText("重新获取");
            btn_code.setClickable(true);
        }

        @Override
        public void onTick(long millisUntilFinished) {//计时过程显示
            btn_code.setClickable(false);
            btn_code.setText(millisUntilFinished / 1000 + "秒" + "后重发");
        }
    }
}
