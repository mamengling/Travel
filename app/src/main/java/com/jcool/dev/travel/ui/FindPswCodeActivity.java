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
import com.jcool.dev.travel.iactivityview.FindPswActivityView;
import com.jcool.dev.travel.persenter.FindPswActivityPresenter;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindPswCodeActivity extends BaseActivity implements View.OnClickListener, FindPswActivityView {
    private FindPswActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.edt_code)
    EditText edt_code;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @BindView(R.id.btn_code)
    Button btn_code;
    private String mStrCode;
    private String mPhone;
    private TimeCount time;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_find_psw_code;
    }

    @Override
    protected void getExtra() {
        mPhone = getIntent().getStringExtra("phone");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTools() {
        ActivityCollector.getInstance().flagActivity(this);
        time = new TimeCount(60000, 1000);//构造CountDownTimer对象
        mPresenter = new FindPswActivityPresenter(this, this);
        ButterKnife.bind(this);
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
            case R.id.btn_commit:
                inputSuccess();
                break;
            case R.id.btn_code:
                mPresenter.registerGetCode(mPhone);
                break;
        }
    }

    private void inputSuccess() {
        mStrCode = edt_code.getText().toString().trim();

        if (TextUtils.isEmpty(mStrCode)) {
            ToastUtils.showShortToast("请输入验证码");
            return;
        }
        RequestParams object = new RequestParams();
        object.put("phone", mPhone);
        object.put("token", getKey());
        object.put("code", mStrCode);

        mPresenter.verifyCode(object);
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
        setKey(mCallBackVo.getData().getToken());
        ToastUtils.showShortToast("验证码发送成功");
        time.start();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {
        Intent intent = new Intent(this, SetPasswordActivity.class);
        intent.putExtra("phone", getUserPhone());
        intent.putExtra("getFlag", 102);
        startActivity(intent);
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
