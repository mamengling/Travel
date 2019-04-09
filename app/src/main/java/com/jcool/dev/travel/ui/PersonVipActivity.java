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
import com.jcool.dev.travel.iactivityview.CompanyVipActivityView;
import com.jcool.dev.travel.persenter.CompanyVipActivityPresenter;
import com.jcool.dev.travel.utils.BuyTimePicker;
import com.jcool.dev.travel.utils.DateFormatUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonVipActivity extends BaseActivity implements View.OnClickListener, CompanyVipActivityView {
    private CompanyVipActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.edt_place_in)
    EditText edt_place_in;
    @BindView(R.id.edt_place_out)
    EditText edt_place_out;
    @BindView(R.id.tv_out_day)
    TextView tv_out_day;
    @BindView(R.id.edt_children_person)
    EditText edt_children_person;
    @BindView(R.id.tv_person_number)
    EditText tv_person_number;
    @BindView(R.id.tv_money)
    EditText tv_money;
    @BindView(R.id.edt_other)
    EditText edt_other;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_name_user)
    EditText edt_name_user;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private BuyTimePicker buyTimePicker;
    private String goPlace;
    private String targetPlace;
    private String count;
    private String goTime;
    private String money;
    private String other;
    private String phone;
    private String name;
    private String email;

    @Override
    protected int getContentViewId() {
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_vip_person;
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
        icon_title.setText("个人定制");
        mPresenter = new CompanyVipActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
        tv_out_day.setOnClickListener(this);
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
            case R.id.tv_out_day:
                initDatePicker();
                break;
        }
    }

    private void inputSuccess() {
        goPlace = edt_place_in.getText().toString().trim();
        targetPlace = edt_place_out.getText().toString().trim();
        goTime = tv_out_day.getText().toString().trim();
        count = tv_person_number.getText().toString().trim();
        money = tv_money.getText().toString().trim();
        other = edt_other.getText().toString().trim();
        name = edt_name_user.getText().toString().trim();
        phone = edt_phone.getText().toString().trim();
        email = edt_email.getText().toString().trim();
        if (TextUtils.isEmpty(goPlace)) {
            ToastUtils.showShortToast("请输入出发地");
            return;
        }
        if (TextUtils.isEmpty(targetPlace)) {
            ToastUtils.showShortToast("请输入目的地");
            return;
        }
        if (TextUtils.isEmpty(goTime)) {
            ToastUtils.showShortToast("请输入出行日期");
            return;
        }
        if (TextUtils.isEmpty(count)) {
            ToastUtils.showShortToast("请输入出行人数");
            return;
        }
        if (TextUtils.isEmpty(money)) {
            ToastUtils.showShortToast("请输入预算金额");
            return;
        }
        if (TextUtils.isEmpty(other)) {
            ToastUtils.showShortToast("请输入其他需求");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(name)) {
            ToastUtils.showShortToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            ToastUtils.showShortToast("请输入邮箱");
            return;
        }
        mPresenter.commitVip();
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast(mCallBackVo.getMsg());
        finish();
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("company", "");//公司名
            object.put("count", count);//人数
            object.put("demandType", "01");//需求类型（01：个人；02：公司）
            object.put("email", email);//邮箱
            object.put("goPlace", goPlace);//出发地
            object.put("goTime", goTime);//出发日期
            object.put("money", money);//金额
            object.put("name", name);//名字
            object.put("other", other);//其它需求
            object.put("phone", phone);//手机
            object.put("targetPlace", targetPlace);//目的地
            object.put("status", "");//状态（01：未处理；02：已处理）
            object.put("userId", "");//
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

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2000-01-01 00:00", true);
        long endTimestamp = DateFormatUtils.str2Long("2050-05-01 00:00", true);
        // 通过时间戳初始化日期，毫秒级别
        buyTimePicker = new BuyTimePicker(this, new BuyTimePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tv_out_day.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTimestamp, endTimestamp);
        // 不允许点击屏幕或物理返回键关闭
        buyTimePicker.setCancelable(true);
        // 显示时和分
        buyTimePicker.setCanShowPreciseTime(false);
        // 不允许循环滚动
        buyTimePicker.setScrollLoop(false);
        // 不允许滚动动画
        buyTimePicker.setCanShowAnim(false);
        buyTimePicker.show(DateFormatUtils.long2Str(System.currentTimeMillis(), true));
    }

}
