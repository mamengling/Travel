package com.jcool.dev.travel.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.SharePreferenceUtil;
import com.jcool.dev.travel.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 账号密码登录
 */
public class LoginAccFragment extends BaseFragment implements View.OnClickListener, LoginFragmentView {
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.find_psw)
    TextView find_psw;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private LoginFragmentPresenter mPresenter;
    private String mPhone;
    private String mPassword;

    public static LoginAccFragment newInstance(String userName, String password, String userID) {
        Bundle args = new Bundle();
        args.getString("userName", userName);
        args.getString("password", password);
        args.getString("userID", userID);
        LoginAccFragment fragment = new LoginAccFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_login_acc;
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
        tv_register.setOnClickListener(this);
        find_psw.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    protected void initTools() {
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
            case R.id.btn_commit:
                inputSuccess();
                break;
        }
    }

    private void inputSuccess() {
        mPhone = edt_phone.getText().toString().trim();
        mPassword = edt_password.getText().toString().trim();
        if (TextUtils.isEmpty(mPhone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(mPassword)) {
            ToastUtils.showShortToast("请输入密码");
            return;
        }
        mPresenter.loginPhone();
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("username", mPhone);
            object.put("password", mPassword);
            object.put("userType", "");
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

    }
}
