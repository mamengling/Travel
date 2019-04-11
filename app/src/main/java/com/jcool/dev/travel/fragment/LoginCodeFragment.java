package com.jcool.dev.travel.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseFragment;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.LoginFragmentView;
import com.jcool.dev.travel.persenter.LoginFragmentPresenter;
import com.jcool.dev.travel.utils.ToastUtils;

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

    private String mPhone;
    private String mCode;

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
            case R.id.btn_commit:
                inputSuccess();
                break;
        }
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
    public void excuteSuccessGoodsCallBack(CallBackVo<UserInfo> mCallBackVo) {

    }
}
