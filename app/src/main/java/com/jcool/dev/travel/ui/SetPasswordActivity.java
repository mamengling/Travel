package com.jcool.dev.travel.ui;

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
import com.jcool.dev.travel.iactivityview.SetPasswordActivityView;
import com.jcool.dev.travel.persenter.SetPasswordActivityPresenter;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SetPasswordActivity extends BaseActivity implements View.OnClickListener, SetPasswordActivityView {
    private SetPasswordActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.edt_password)
    EditText edt_password;
    @BindView(R.id.edt_password_again)
    EditText edt_password_again;
    @BindView(R.id.btn_commit)
    Button btn_commit;

    private String password;
    private String passwordAgain;
    private String phone;
    private int getFlag;


    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_set_password;
    }

    @Override
    protected void getExtra() {
        phone = getIntent().getStringExtra("phone");
        getFlag = getIntent().getIntExtra("getFlag", 0);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        ActivityCollector.getInstance().flagActivity(this);
        mPresenter = new SetPasswordActivityPresenter(this, this);
        if (getFlag == 102) {
            icon_title.setText("忘记密码");
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
        password = edt_password.getText().toString().trim();
        passwordAgain = edt_password_again.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            ToastUtils.showShortToast("请输入密码");
            return;
        }
        if (TextUtils.isEmpty(passwordAgain)) {
            ToastUtils.showShortToast("请输入确认密码");
            return;
        }
        if (!TextUtils.equals(password, passwordAgain)) {
            ToastUtils.showShortToast("两次输入密码不一致");
            return;
        }

        RequestParams params = new RequestParams();
        params.put("password", password);
        if (getFlag == 101) {
            /**
             * 注册设置密码
             */
            mPresenter.register();
        } else if (getFlag == 102) {
            /**
             * 忘记密码
             */

        } else {
            /**
             * 修改密码
             */
            mPresenter.setPassword(params, getToken());
        }
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {
        ToastUtils.showShortToast("设置成功");
        ActivityCollector.getInstance().finishFlagActivities();
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("username", phone);
            object.put("phone", phone);
            object.put("password", password);
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
