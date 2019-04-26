package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.TravelOrderInfoAdd;
import com.jcool.dev.travel.iactivityview.CreateTravelOrderActivityView;
import com.jcool.dev.travel.iactivityview.CreateVisaOrderActivityView;
import com.jcool.dev.travel.persenter.CreateTravelOrderActivityPresenter;
import com.jcool.dev.travel.persenter.CreateVisaOrderActivityPresenter;
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

public class CreateTravelOrderActivity extends BaseActivity implements View.OnClickListener, CreateTravelOrderActivityView {
    private CreateTravelOrderActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_change_person)
    TextView tv_change_person;
    @BindView(R.id.tv_buy)
    TextView tv_buy;
    @BindView(R.id.tv_money)
    TextView tv_money;
    @BindView(R.id.person_list)
    LinearLayout person_list;
    @BindView(R.id.edt_user_name)
    EditText edt_user_name;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.edt_other)
    EditText edt_other;
    private static final int BAIDU_READ_PHONE_STATE = 100;
    private ConstmChangePersonPicker personPicker;
    private List<PersonInfoBean> mPersonUnits = new ArrayList<>();
    private String custIds;
    private int peopleCount;
    private View personViews;
    private String travelId;
    private String phoneNumber;
    private String linkName;
    private String emailNumber;
    private String childrenNumber;
    private String adultNumber;
    private String goodsDate;
    private String remarks;
    private double visaTotalamt;
    private String travelName;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_create_travel_order;
    }

    @Override
    protected void getExtra() {
        travelId = getIntent().getStringExtra("travelId");
        travelName = getIntent().getStringExtra("travelName");//商品名称
        goodsDate = getIntent().getStringExtra("goodsDate");//排期id
        childrenNumber = getIntent().getStringExtra("childrenNumber");//儿童人数
        adultNumber = getIntent().getStringExtra("adultNumber");//成人人数
        visaTotalamt = getIntent().getDoubleExtra("money", 0);//钱数
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        mPresenter = new CreateTravelOrderActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        tv_money.setText("金额：" + "¥" + visaTotalamt);
        icon_title_back.setOnClickListener(this);
        tv_change_person.setOnClickListener(this);
        tv_buy.setOnClickListener(this);
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
            case R.id.tv_buy:
                isInputSuccess();
                break;
        }
    }

    private void isInputSuccess() {
        phoneNumber = edt_phone.getText().toString().trim();
        linkName = edt_user_name.getText().toString().trim();
        emailNumber = edt_email.getText().toString().trim();
        remarks = edt_other.getText().toString().trim();


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
        mPresenter.addTravelOrder(getToken());
    }


    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("goodsId", travelId);
            object.put("goodsDate", goodsDate);
            object.put("childrenNumber", childrenNumber);
            object.put("adultNumber", adultNumber);
            object.put("linkMan", linkName);
            object.put("linkPhone", phoneNumber);
            object.put("linkMail", emailNumber);
            object.put("remarks", remarks);
            object.put("people", custIds);
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
    public void excuteSuccessCallBack(CallBackVo<TravelOrderInfoAdd> mCallBackVo) {
        Intent intent = new Intent(this, PayActivity.class);
        intent.putExtra("totalAmount", visaTotalamt + "");
        intent.putExtra("goodsName", travelName);
        intent.putExtra("outOrderNo", mCallBackVo.getData().getId());
        intent.putExtra("productType", "01");
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
                        personViews = LayoutInflater.from(CreateTravelOrderActivity.this).inflate(R.layout.x_item_person, null);
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
                Intent intent = new Intent(CreateTravelOrderActivity.this, AddPersonActivity.class);
                startActivity(intent);
            }
        }, mPersonUnits);
        // 不允许点击屏幕或物理返回键关闭
        personPicker.setCancelable(true);
        // 不允许滚动动画
        personPicker.show();
    }
}
