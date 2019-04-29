package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.iactivityview.AddPersonActivityView;
import com.jcool.dev.travel.persenter.AddPersonActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.MianChangeInputPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPersonActivity extends BaseActivity implements View.OnClickListener, AddPersonActivityView {
    private AddPersonActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_person_type)
    TextView tv_person_type;
    @BindView(R.id.tv_card_type)
    TextView tv_card_type;
    @BindView(R.id.edt_code_number)
    EditText edt_code_number;
    @BindView(R.id.edt_china_name)
    EditText edt_china_name;
    @BindView(R.id.radiogroup_full)
    RadioGroup radiogroup_full;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @BindView(R.id.radiobutton0)
    RadioButton radiobutton0;
    @BindView(R.id.radiobutton1)
    RadioButton radiobutton1;
    /**
     * 年龄
     */
    private MianChangeInputPicker agePicker;
    private List<String> mAgeUnits = new ArrayList<>();
    private String[] agrType = {"0-12周岁选择儿童", "成人", "儿童"};
    /**
     * 客户类型
     */
    private MianChangeInputPicker personPicker;
    private List<String> mPersonUnits = new ArrayList<>();
    private String[] personType = {"在职", "在校学生", "退休", "学龄儿童"};
    /**
     * 证件类型
     */
    private MianChangeInputPicker cardPicker;
    private List<String> mCardUnits = new ArrayList<>();
    private String[] cardType = {"身份证", "护照"};
    private PersonInfoBean bean;

    private String custName = "";
    private String custSex = "";
    private String custAge = "";
    private String custType = "";
    private String custCert = "";
    private String certType = "";

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_person_add;
    }

    @Override
    protected void getExtra() {
        bean = (PersonInfoBean) getIntent().getSerializableExtra("info");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        if (bean != null) {
            edt_china_name.setText(bean.getCustName());
            if (TextUtils.equals("boy", bean.getCustSex())) {
                radiobutton0.setChecked(true);
                radiobutton1.setChecked(false);
                custSex = "boy";
            } else {
                custSex = "girl";
                radiobutton0.setChecked(false);
                radiobutton1.setChecked(true);
            }

            if (TextUtils.equals("01", bean.getCustAge())) {
                tv_age.setText("0-12周岁儿童");
                custAge = "01";
            } else if (TextUtils.equals("02", bean.getCustAge())) {
                tv_age.setText("儿童");
                custAge = "02";
            } else {
                tv_age.setText("成人");
                custAge = "03";
            }

            if (TextUtils.equals("01", bean.getCustType())) {
                custType = "01";
                tv_person_type.setText("在职");
            } else if (TextUtils.equals("02", bean.getCustType())) {
                custType = "02";
                tv_person_type.setText("在校学生");
            } else if (TextUtils.equals("03", bean.getCustType())) {
                custType = "03";
                tv_person_type.setText("退休");
            } else if (TextUtils.equals("04", bean.getCustType())) {
                custType = "04";
                tv_person_type.setText("学龄儿童");
            }
            if (TextUtils.equals("PASSPORT", bean.getCertType())) {
                certType = "PASSPORT";
                tv_card_type.setText("护照");
            } else if (TextUtils.equals("ID", bean.getCertType())) {
                certType = "ID";
                tv_card_type.setText("身份证");
            }

            edt_code_number.setText(bean.getCustCert());
        }
        mPresenter = new AddPersonActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_card_type.setOnClickListener(this);
        tv_person_type.setOnClickListener(this);
        tv_age.setOnClickListener(this);
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
            case R.id.tv_age:
                mAgeUnits.clear();
                for (int i = 0; i < agrType.length; i++) {
                    mAgeUnits.add(agrType[i]);
                }
                initAgePicker();
                break;
            case R.id.tv_person_type:
                mPersonUnits.clear();
                for (int i = 0; i < personType.length; i++) {
                    mPersonUnits.add(personType[i]);
                }
                initPersonPicker();
                break;
            case R.id.tv_card_type:
                mCardUnits.clear();
                for (int i = 0; i < cardType.length; i++) {
                    mCardUnits.add(cardType[i]);
                }
                initCardPicker();
                break;
            case R.id.btn_commit:
                inputSuccess();
                break;
        }
    }

    private void inputSuccess() {
        custName = edt_china_name.getText().toString().trim();
        custCert = edt_code_number.getText().toString().trim();
        if (radiobutton0.isChecked()) {
            custSex = "boy";
        }
        if (radiobutton1.isChecked()) {
            custSex = "girl";
        }
        if (TextUtils.isEmpty(custName)) {
            ToastUtils.showShortToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(custSex)) {
            ToastUtils.showShortToast("请选择性别");
            return;
        }
        if (TextUtils.isEmpty(custAge)) {
            ToastUtils.showShortToast("请选择年龄段");
            return;
        }
        if (TextUtils.isEmpty(custType)) {
            ToastUtils.showShortToast("请选择客户类型");
            return;
        }
        if (TextUtils.isEmpty(certType)) {
            ToastUtils.showShortToast("请选择证件类型");
            return;
        }
        if (TextUtils.isEmpty(custCert)) {
            ToastUtils.showShortToast("请输入证件编号");
            return;
        }

        if (bean != null) {
            mPresenter.updatePerson(getToken());
        } else {
            mPresenter.addPerson(getToken());
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            if (bean != null) {
                object.put("id", bean.getId());//旅客性别(boy,girl)
            }
            object.put("custName", custName);//旅客性别(boy,girl)
            object.put("custSex", custSex);//旅客性别(boy,girl)
            object.put("custAge", custAge);//旅客年龄段(01:0-12周岁；02：儿童；03：成人)
            object.put("custType", custType);//客户类型(01:在职；02：在校学生；03：退休；04：学龄儿童)
            object.put("custCert", custCert);//证件编号
            object.put("certType", certType);//证件类型（护照：PASSPORT；身份证：ID）
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
    public void excuteSuccessCallBack(CallBackVo<String> mCallBackVo) {
        if (bean != null) {
            ToastUtils.showShortToast("修改成功");
        } else {
            ToastUtils.showShortToast("添加成功");
        }
        finish();
    }


    private void initAgePicker() {
        // 通过时间戳初始化日期，毫秒级别
        agePicker = new MianChangeInputPicker(this, new MianChangeInputPicker.Callback() {
            @Override
            public void onSelected(String tamp, int index) {
                if (TextUtils.equals("0-12周岁选择儿童", tamp)) {
                    tv_age.setText("0-12周岁儿童");
                    custAge = "01";
                } else if (TextUtils.equals("儿童", tamp)) {
                    tv_age.setText(tamp);
                    custAge = "02";
                } else if (TextUtils.equals("成人", tamp)) {
                    tv_age.setText(tamp);
                    custAge = "03";
                }
            }
        }, mAgeUnits);
        // 不允许点击屏幕或物理返回键关闭
        agePicker.setCancelable(true);
        // 不允许循环滚动
        agePicker.setScrollLoop(false);
        // 不允许滚动动画
        agePicker.setCanShowAnim(false);
        agePicker.setTitle("年龄段");
        agePicker.show(TextUtils.isEmpty(tv_age.getText().toString()) ?agrType[0] : tv_age.getText().toString());
    }

    private void initPersonPicker() {
        // 通过时间戳初始化日期，毫秒级别
        personPicker = new MianChangeInputPicker(this, new MianChangeInputPicker.Callback() {
            @Override
            public void onSelected(String tamp, int index) {

                if (TextUtils.equals("在职", tamp)) {
                    custType = "01";
                    tv_person_type.setText(tamp);
                } else if (TextUtils.equals("在校学生", tamp)) {
                    custType = "02";
                    tv_person_type.setText(tamp);
                } else if (TextUtils.equals("退休", tamp)) {
                    custType = "03";
                    tv_person_type.setText(tamp);
                } else if (TextUtils.equals("学龄儿童", tamp)) {
                    custType = "04";
                    tv_person_type.setText(tamp);
                }
            }
        }, mPersonUnits);
        // 不允许点击屏幕或物理返回键关闭
        personPicker.setCancelable(true);
        // 不允许循环滚动
        personPicker.setScrollLoop(false);
        // 不允许滚动动画
        personPicker.setCanShowAnim(false);
        personPicker.setTitle("客户类型");
        personPicker.show(TextUtils.isEmpty(tv_person_type.getText().toString()) ? personType[0] : tv_person_type.getText().toString());
    }

    private void initCardPicker() {
        // 通过时间戳初始化日期，毫秒级别
        cardPicker = new MianChangeInputPicker(this, new MianChangeInputPicker.Callback() {
            @Override
            public void onSelected(String tamp, int index) {

                if (TextUtils.equals("护照", tamp)) {
                    certType = "PASSPORT";
                    tv_card_type.setText(tamp);
                } else if (TextUtils.equals("身份证", tamp)) {
                    certType = "ID";
                    tv_card_type.setText(tamp);
                }
            }
        }, mCardUnits);
        // 不允许点击屏幕或物理返回键关闭
        cardPicker.setCancelable(true);
        // 不允许循环滚动
        cardPicker.setScrollLoop(false);
        // 不允许滚动动画
        cardPicker.setCanShowAnim(false);
        cardPicker.setTitle("证件类型");
        cardPicker.show(TextUtils.isEmpty(tv_card_type.getText().toString()) ?cardType[0] : tv_card_type.getText().toString());
    }

}
