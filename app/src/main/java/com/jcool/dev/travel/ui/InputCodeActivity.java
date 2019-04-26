package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
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

public class InputCodeActivity extends BaseActivity implements View.OnClickListener, RegisterActivityView {
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
    @BindView(R.id.btn_code)
    Button btn_code;
    private String mPhone;
    private String mStrCode;
    private TimeCount time;
    private int flag;
    private String phone;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_input_code;
    }

    @Override
    protected void getExtra() {
        flag = getIntent().getIntExtra("flag", 0);
        phone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        ActivityCollector.getInstance().flagActivity(this);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        mPresenter = new RegisterActivityPresenter(this, this);
        if (isLogin() && getUserPhone() != null) {
            tv_phone.setText("输入当前手机：" + phone + "收到的验证码");
            mPhone = phone;
        }
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        btn_code.setOnClickListener(this);
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
                mPresenter.registerGetCode(mPhone);
                break;
            case R.id.btn_commit:
                if (flag == 101) {
                    inputSuccess();
                } else {
                    inputSuccessCode();
                }
                break;
        }
    }

    private void inputSuccess() {
        mStrCode = edt_phone.getText().toString().trim();

        if (TextUtils.isEmpty(mStrCode)) {
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        RequestParams object = new RequestParams();
        object.put("phone", mPhone);
        object.put("token", getKey());
        object.put("code", mStrCode);
        mPresenter.registerP(object);
    }

    private void inputSuccessCode() {
        mStrCode = edt_phone.getText().toString().trim();
        if (TextUtils.isEmpty(mStrCode)) {
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        mPresenter.verifyPhone();
    }

    @Override
    public JSONObject getParamentersRegister() {
        return null;
    }

    @Override
    public RequestParams getParamentersPhone() {
        RequestParams object = new RequestParams();
        object.put("phone", mPhone);
        object.put("token", getKey());
        object.put("code", mStrCode);
        return object;
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        if (flag == 101) {
            mPresenter.updatePhone(getToken());
        } else {
            Intent intent = new Intent(this, InputPhoneActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void excuteSuccessCodeCallBack(CallBackVo<CodeBean> mCallBackVo) {
        setKey(mCallBackVo.getData().getToken());
        ToastUtils.showShortToast("验证码发送成功");
        time.start();
    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {
        setUserPhone(phone);
        ActivityCollector.getInstance().finishFlagActivities();
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
