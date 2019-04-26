package com.jcool.dev.travel.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.iactivityview.CreateVisaOrderActivityView;
import com.jcool.dev.travel.persenter.CreateVisaOrderActivityPresenter;
import com.jcool.dev.travel.utils.AppTravelLocation;
import com.jcool.dev.travel.utils.BuyTimePicker;
import com.jcool.dev.travel.utils.DateFormatUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.ConstmChangePersonPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateVisaOrderActivity extends BaseActivity implements View.OnClickListener, CreateVisaOrderActivityView {
    private CreateVisaOrderActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.tv_date_time)
    TextView tv_date_time;
    @BindView(R.id.tv_change_person)
    TextView tv_change_person;
    @BindView(R.id.person_list)
    LinearLayout person_list;
    @BindView(R.id.line_address)
    LinearLayout line_address;
    @BindView(R.id.edt_user_name)
    EditText edt_user_name;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.radiobutton0)
    RadioButton radiobutton0;
    @BindView(R.id.radiobutton2)
    RadioButton radiobutton2;
    @BindView(R.id.ed_address)
    TextView ed_address;
    @BindView(R.id.tv_give_type)
    TextView tv_give_type;
    @BindView(R.id.tv_give_address)
    TextView tv_give_address;
    @BindView(R.id.tv_address)
    TextView tv_address;
    @BindView(R.id.check_box)
    CheckBox check_box;
    @BindView(R.id.btn_to_pay)
    Button btn_to_pay;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    private ConstmChangePersonPicker personPicker;
    private List<PersonInfoBean> mPersonUnits = new ArrayList<>();
    private String custIds;
    private int peopleCount;
    private View personViews;
    private BuyTimePicker buyTimePicker;
    private double visaTotalamt;
    private String visaId;
    private String visaPlace;
    private String visaTime;
    private String visaName;
    private String visaImage;
    private String visaPrice;
    private String giveType;
    private String detailAddress;
    private String cityAddress;
    private String tripTime;
    private String phoneNumber;
    private String linkName;
    private String emailNumber;
    private String specName;
    private String specIndex;
    private double specPrice;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_create_visa_order;
    }

    @Override
    protected void getExtra() {
        visaId = getIntent().getStringExtra("visaId");
        visaPlace = getIntent().getStringExtra("visaPlace");
        visaTime = getIntent().getStringExtra("visaTime");
        visaName = getIntent().getStringExtra("visaName");
        visaImage = getIntent().getStringExtra("visaImage");
        visaPrice = getIntent().getStringExtra("visaPrice");
        specName = getIntent().getStringExtra("specName");
        specIndex = getIntent().getStringExtra("specIndex");
        specPrice = getIntent().getDoubleExtra("specPrice", 0);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new CreateVisaOrderActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        ed_address.setText("取签地址：" + visaPlace);
        tv_date_time.setText(visaTime + "");
        icon_title_back.setOnClickListener(this);
        tv_day.setOnClickListener(this);
        tv_give_address.setOnClickListener(this);
        tv_change_person.setOnClickListener(this);
        btn_to_pay.setOnClickListener(this);
        radiobutton0.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//配送方式(01:配送；02：自取)
                    giveType = "02";
                    ed_address.setVisibility(View.VISIBLE);
                    line_address.setVisibility(View.GONE);
                }
            }
        });
        radiobutton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {//配送方式(01:配送；02：自取)
                    giveType = "01";
                    ed_address.setVisibility(View.GONE);
                    line_address.setVisibility(View.VISIBLE);
                }
            }
        });
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                }
            }
        });

    }

    @Override
    protected void initData() {
        mPresenter.journeyPersonList(getToken());
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
            case R.id.tv_change_person:
                initPersonPicker();
                break;
            case R.id.tv_day:
                initDatePicker();
                break;
            case R.id.btn_to_pay:
                isInputSuccess();
                break;
            case R.id.tv_give_address:
                //判断是否为android6.0系统版本，如果是，需要动态添加权限
                if (Build.VERSION.SDK_INT >= 23) {
                    showContacts();
                } else {
                    Intent intent = new Intent(this, SearchLocationActivity.class);
                    startActivityForResult(intent, 101);
                }
                break;
        }
    }

    public void showContacts() {
        Intent intent = new Intent(this, SearchLocationActivity.class);
        startActivityForResult(intent, 101);
    }

    private void isInputSuccess() {
        tripTime = tv_day.getText().toString();
        detailAddress = tv_address.getText().toString();
        phoneNumber = edt_phone.getText().toString().trim();
        linkName = edt_user_name.getText().toString().trim();
        emailNumber = edt_email.getText().toString().trim();

        if (TextUtils.isEmpty(tripTime)) {
            ToastUtils.showShortToast("请设置出行时间");
            return;
        }
        if (TextUtils.equals("02", giveType)) {
            if (TextUtils.isEmpty(cityAddress)) {
                ToastUtils.showShortToast("请选择配送地址");
                return;
            }
            if (TextUtils.isEmpty(detailAddress)) {
                ToastUtils.showShortToast("请输入详细地址");
                return;
            }
        }
        if (peopleCount == 0 || TextUtils.isEmpty(custIds)) {
            ToastUtils.showShortToast("请选择申请人");
            return;
        }
        if (TextUtils.isEmpty(linkName)) {
            ToastUtils.showShortToast("请输入联系人姓名");
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            ToastUtils.showShortToast("请输入手机号");
            return;
        }
        if (TextUtils.isEmpty(emailNumber)) {
            ToastUtils.showShortToast("请输入电子邮箱");
            return;
        }
        visaTotalamt = specPrice * peopleCount;

        mPresenter.addVisaOrder(getToken());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case 101:
                if (data != null) {
                    cityAddress = data.getStringExtra("locationAddress");
                    tv_give_address.setText(cityAddress);
                }
                break;
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("visaId", visaId);
            object.put("giveType", giveType);
            object.put("visaPlace", visaPlace);
            object.put("tripTime", tripTime);
            object.put("visaName", visaName);
            object.put("visaImage", visaImage);
            object.put("visaTotalamt", visaTotalamt);
            object.put("visaPrice", visaPrice);
            object.put("linkName", linkName);
            object.put("linkPhone", phoneNumber);
            object.put("linkEmail", emailNumber);
            object.put("custIds", custIds);
            object.put("specName", specName);
            object.put("specIndex", specIndex);
            object.put("specPrice", specPrice);
            object.put("detailAddress", cityAddress + detailAddress);
            object.put("peopleCount", peopleCount);
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

    @Override
    public void excuteSuccessPersonCallBack(CallBackVo<List<PersonInfoBean>> mCallBackVo) {
        if (mCallBackVo != null & mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mPersonUnits.clear();
            mPersonUnits.addAll(mCallBackVo.getData());
        }
    }

    @Override
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {
        Intent intent = new Intent(this, PayActivity.class);
        intent.putExtra("totalAmount", visaTotalamt + "");
        intent.putExtra("goodsName", visaName);
        intent.putExtra("outOrderNo", mCallBackVo.getData());
        intent.putExtra("productType", "02");
        startActivity(intent);
        finish();
    }


    private void initPersonPicker() {
        // 通过时间戳初始化日期，毫秒级别
        personPicker = new ConstmChangePersonPicker(this, new ConstmChangePersonPicker.Callback() {
            @Override
            public void onSelected(List<PersonInfoBean> tamp) {
                person_list.removeAllViews();
                custIds = "";
                peopleCount = 0;
                if (tamp != null && tamp.size() > 0) {
                    for (int i = 0; i < tamp.size(); i++) {
                        personViews = LayoutInflater.from(CreateVisaOrderActivity.this).inflate(R.layout.x_item_person, null);
                        TextView tv_name = personViews.findViewById(R.id.tv_name);
                        TextView tv_sex = personViews.findViewById(R.id.tv_sex);
                        TextView tv_person_type = personViews.findViewById(R.id.tv_person_type);
                        TextView tv_work_type = personViews.findViewById(R.id.tv_work_type);

                        custIds += tamp.get(i).getId() + ",";
                        peopleCount += 1;
                        tv_name.setText(tamp.get(i).getCustName());
                        if (TextUtils.equals("girl", tamp.get(i).getCustSex())) {//旅客性别(boy,girl)
                            tv_sex.setText("女");
                        } else {
                            tv_sex.setText("男");
                        }

                        //客户类型(01:在职；02：在校学生；03：退休；04：学龄儿童)
                        if (TextUtils.equals("01", tamp.get(i).getCertType())) {
                            tv_work_type.setText("在职");
                        } else if (TextUtils.equals("02", tamp.get(i).getCertType())) {
                            tv_work_type.setText("在校学生");
                        } else if (TextUtils.equals("03", tamp.get(i).getCertType())) {
                            tv_work_type.setText("退休");
                        } else {
                            tv_work_type.setText("学龄儿童");
                        }

                        //旅客年龄段(01:0-12周岁；02：儿童；03：成人)
                        if (TextUtils.equals("3", tamp.get(i).getCustAge())) {
                            tv_work_type.setText("在职");
                        } else {
                            tv_work_type.setText("儿童");
                        }
                        person_list.addView(personViews);
                    }
                }
            }

            @Override
            public void onAdd(String mStrUnits) {
                Intent intent = new Intent(CreateVisaOrderActivity.this, AddPersonActivity.class);
                startActivity(intent);
            }
        }, mPersonUnits);
        // 不允许点击屏幕或物理返回键关闭
        personPicker.setCancelable(true);
        // 不允许滚动动画
        personPicker.show();
    }

    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2000-01-01 00:00", true);
        long endTimestamp = DateFormatUtils.str2Long("2050-05-01 00:00", true);
        // 通过时间戳初始化日期，毫秒级别
        buyTimePicker = new BuyTimePicker(this, new BuyTimePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tv_day.setText(DateFormatUtils.long2Str(timestamp, false));
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
