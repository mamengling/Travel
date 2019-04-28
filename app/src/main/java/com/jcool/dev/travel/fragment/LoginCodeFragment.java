package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.CodeBean;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.LoginFragmentView;
import com.jcool.dev.travel.persenter.LoginFragmentPresenter;
import com.jcool.dev.travel.ui.FindPswActivity;
import com.jcool.dev.travel.ui.RegisterActivity;
import com.jcool.dev.travel.utils.ToastUtils;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 验证码登录
 */
public class LoginCodeFragment extends BaseFragment implements View.OnClickListener, LoginFragmentView {
    private LoginFragmentPresenter mPresenter;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_code)
    EditText edt_code;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.find_psw)
    TextView find_psw;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @BindView(R.id.btn_code)
    Button btn_code;

    private String mPhone;
    private String mCode;
    private String mStrCode;

    private TimeCount time;

    public static LoginCodeFragment newInstance(String userName, String password, String userID) {

        Bundle args = new Bundle();
        args.getString("userName", userName);
        args.getString("password", password);
        args.getString("userID", userID);
        LoginCodeFragment fragment = new LoginCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login_code;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {
        ButterKnife.bind(this, view);
    }

    @Override
    protected void setListener() {
        btn_commit.setOnClickListener(this);
        btn_code.setOnClickListener(this);
        tv_register.setOnClickListener(this);
        find_psw.setOnClickListener(this);
    }

    @Override
    protected void initTools() {
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        mPresenter = new LoginFragmentPresenter(this, getContext());
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_register:
                Intent intentReg = new Intent(getContext(), RegisterActivity.class);
                startActivity(intentReg);
                break;
            case R.id.find_psw:
                Intent intentFind = new Intent(getContext(), FindPswActivity.class);
                startActivity(intentFind);
                break;
            case R.id.btn_code:
                inputSuccessCode();
                break;
            case R.id.btn_commit:
                inputSuccess();
                break;
        }
    }
    private void inputSuccessCode() {
        mPhone = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        mPresenter.loginGetCode(mPhone);
    }
    private void inputSuccess() {
        mPhone = edt_phone.getText().toString().trim();
        mCode = edt_code.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(mCode)) {
            ToastUtils.showShortToast("请输入验证码");
            return;
        }


        RequestParams params=new RequestParams();
        params.put("code",mCode);
        params.put("phone",mPhone);
        params.put("token",getKey());


        mPresenter.loginCode(params);
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
        ToastUtils.showShortToast(mCallBackVo.getMsg());
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<UserInfo> mCallBackVo) {
        ToastUtils.showShortToast("登录成功");
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            setUserInfo(mCallBackVo.getData().getUserInfo().getSysUser());
            setToken(mCallBackVo.getData().getAccesstoken());
        }
        if (isAdded()) {
            getActivity().finish();
        }
    }

    @Override
    public void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo) {
        setKey(mCallBackVo.getData().getToken());
        ToastUtils.showShortToast("验证码发送成功");
        time.start();
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
