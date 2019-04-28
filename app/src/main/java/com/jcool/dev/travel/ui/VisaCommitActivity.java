package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.bean.VisaTargetInfo;
import com.jcool.dev.travel.iactivityview.VisaCommitActivityView;
import com.jcool.dev.travel.persenter.VisaCommitActivityPresenter;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.ConstmChangePersonPicker;
import com.jcool.dev.travel.view.VisaChangeInputPicker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VisaCommitActivity extends BaseActivity implements View.OnClickListener, VisaCommitActivityView {
    private VisaCommitActivityPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_shop_type)
    TextView tv_shop_type;
    @BindView(R.id.tv_tag_place)
    TextView tv_tag_place;
    @BindView(R.id.edt_goods_name)
    TextView edt_goods_name;
    @BindView(R.id.tv_change_person)
    TextView tv_change_person;
    @BindView(R.id.tv_order_number)
    EditText tv_order_number;
    @BindView(R.id.edt_user_name)
    EditText edt_user_name;
    @BindView(R.id.edt_phone)
    EditText edt_phone;
    @BindView(R.id.edt_email)
    EditText edt_email;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    @BindView(R.id.person_list)
    LinearLayout person_list;
    private VisaChangeInputPicker placePicker;
    private VisaChangeInputPicker goodsPicker;
    private VisaChangeInputPicker buyPicker;
    private ConstmChangePersonPicker personPicker;

    private List<VisaTargetInfo> placeList = new ArrayList<>();
    private List<VisaInfoDtoList.VisaInfoDtoListBean> visaList = new ArrayList<>();
    private List<String> mGoodsUnits = new ArrayList<>();
    private List<String> mStrUnits = new ArrayList<>();
    private List<String> mPlaceUnits = new ArrayList<>();
    private List<PersonInfoBean> mPersonUnits = new ArrayList<>();
    private String[] shopType = {"国旅-淘宝", "中青国际-淘宝", "国旅-京东", "中青国际-京东", "签证直客"};
    private String placeId = "";
    private String visaId = "";
    private String shopTypeName = "";
    private String orderNumber = "";
    private String placeName = "";
    private String visaName = "";
    private String custIds = "";
    private String linkName = "";
    private String phoneNumber = "";
    private String emailNumber = "";
    private int peopleCount = 0;
    private View personViews;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_commit_visa;
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
        icon_title.setText("资料提交");
        icon_right.setText("提交记录");
        mPresenter = new VisaCommitActivityPresenter(this, this);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_shop_type.setOnClickListener(this);
        tv_tag_place.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        edt_goods_name.setOnClickListener(this);
        tv_change_person.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mPresenter.journeyVisaInfo();
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
            case R.id.icon_right:
                Intent intentHisTory = new Intent(this, HisToryVisaActivity.class);
                startActivity(intentHisTory);
                break;
            case R.id.tv_shop_type:
                for (int i = 0; i < shopType.length; i++) {
                    mStrUnits.add(shopType[i]);
                }
                initDatePicker();
                break;
            case R.id.tv_tag_place:
                if (mPlaceUnits != null && mPlaceUnits.size() > 0) {
                    initPlacePicker();
                }
                break;
            case R.id.edt_goods_name:
                if (!TextUtils.isEmpty(tv_tag_place.getText())) {
                    if (mGoodsUnits != null && mGoodsUnits.size() > 0) {
                        initGoodsPicker();
                    } else {
                        ToastUtils.showShortToast("该目的地没有相关商品");
                    }
                } else {
                    ToastUtils.showShortToast("请选择目的地");
                }
                break;
            case R.id.tv_change_person:
                initPersonPicker();
                break;
            case R.id.btn_commit:
                inputSuccess();
                break;
        }
    }

    private void inputSuccess() {
        shopTypeName = tv_shop_type.getText().toString().trim();
        orderNumber = tv_order_number.getText().toString().trim();
        placeName = tv_tag_place.getText().toString().trim();
        phoneNumber = edt_phone.getText().toString().trim();
        linkName = edt_user_name.getText().toString().trim();
        emailNumber = edt_email.getText().toString().trim();
        if (TextUtils.isEmpty(shopTypeName)) {
            ToastUtils.showShortToast("请选择店铺类型");
            return;
        }
        if (TextUtils.isEmpty(orderNumber)) {
            ToastUtils.showShortToast("请输入订单号");
            return;
        }
        if (TextUtils.isEmpty(placeName) || TextUtils.isEmpty(placeId)) {
            ToastUtils.showShortToast("请选择目的地");
            return;
        }
        if (TextUtils.isEmpty(placeName) || TextUtils.isEmpty(placeId)) {
            ToastUtils.showShortToast("请选择目的地");
            return;
        }
        if (TextUtils.isEmpty(visaId) || TextUtils.isEmpty(visaName)) {
            ToastUtils.showShortToast("请选择商品名称");
            return;
        }
        if (TextUtils.isEmpty(custIds) || peopleCount == 0) {
            ToastUtils.showShortToast("请选择申请人");
            return;
        }
        if (TextUtils.isEmpty(custIds) || peopleCount == 0) {
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
        if (!AppUtils.isMobile(phoneNumber)) {
            ToastUtils.showShortToast("请输入正确手机号");
            return;
        }
        if (TextUtils.isEmpty(emailNumber)) {
            ToastUtils.showShortToast("请输入电子邮箱");
            return;
        }
        if (!AppUtils.isEmail(emailNumber)) {
            ToastUtils.showShortToast("请输入正确邮箱");
            return;
        }
        mPresenter.journeyVisaAdd(getToken());
    }

    private void initDatePicker() {
        // 通过时间戳初始化日期，毫秒级别
        buyPicker = new VisaChangeInputPicker(this, new VisaChangeInputPicker.Callback() {
            @Override
            public void onSelected(String tamp, int index) {
                tv_shop_type.setText(tamp);
            }
        }, mStrUnits);
        // 不允许点击屏幕或物理返回键关闭
        buyPicker.setCancelable(true);
        // 不允许循环滚动
        buyPicker.setScrollLoop(false);
        // 不允许滚动动画
        buyPicker.setCanShowAnim(false);
        buyPicker.setTitle("店铺类型");
        buyPicker.show(shopType[0]);
    }

    private void initPlacePicker() {
        // 通过时间戳初始化日期，毫秒级别
        placePicker = new VisaChangeInputPicker(this, new VisaChangeInputPicker.Callback() {
            @Override
            public void onSelected(String tamp, int index) {
                tv_tag_place.setText(tamp);
                edt_goods_name.setText("");
                visaId = "";
                visaName = "";
                for (int i = 0; i < placeList.size(); i++) {
                    if (TextUtils.equals(tamp, placeList.get(i).getPlaceName())) {
                        placeId = placeList.get(i).getId();
                    }
                }
                mPresenter.journeyVisaGoodsList();
            }
        }, mPlaceUnits);
        // 不允许点击屏幕或物理返回键关闭
        placePicker.setCancelable(true);
        // 不允许循环滚动
        placePicker.setScrollLoop(false);
        // 不允许滚动动画
        placePicker.setCanShowAnim(true);
        placePicker.setTitle("目的地");
        placePicker.show(mPlaceUnits.get(0));
    }

    private void initGoodsPicker() {
        // 通过时间戳初始化日期，毫秒级别
        goodsPicker = new VisaChangeInputPicker(this, new VisaChangeInputPicker.Callback() {
            @Override
            public void onSelected(String tamp, int index) {
                edt_goods_name.setText(tamp);
                for (int i = 0; i < visaList.size(); i++) {
                    if (TextUtils.equals(tamp, visaList.get(i).getVisaName())) {
                        visaId = visaList.get(i).getId();
                        visaName = visaList.get(i).getVisaName();
                    }
                }
            }
        }, mGoodsUnits);
        // 不允许点击屏幕或物理返回键关闭
        goodsPicker.setCancelable(true);
        // 不允许循环滚动
        goodsPicker.setScrollLoop(false);
        // 不允许滚动动画
        goodsPicker.setCanShowAnim(true);
        goodsPicker.setTitle("商品名称");
        goodsPicker.show(mGoodsUnits.get(0));
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
                        personViews = LayoutInflater.from(VisaCommitActivity.this).inflate(R.layout.x_item_person, null);
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
                            tv_person_type.setText("成人");
                        } else {
                            tv_person_type.setText("儿童");
                        }
                        person_list.addView(personViews);
                    }
                }
            }

            @Override
            public void onAdd(String mStrUnits) {
                Intent intent = new Intent(VisaCommitActivity.this, AddPersonActivity.class);
                startActivity(intent);
            }
        }, mPersonUnits);
        // 不允许点击屏幕或物理返回键关闭
        personPicker.setCancelable(true);
        // 不允许滚动动画
        personPicker.show();
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("placeId", placeId);
            object.put("shopType", shopTypeName);
            object.put("orderId", orderNumber);
            object.put("place", placeName);
            object.put("visaName", visaName);
            object.put("custIds", custIds);
            object.put("linkName", linkName);
            object.put("phone", phoneNumber);
            object.put("email", emailNumber);
            object.put("peopleCount", peopleCount);
            object.put("visaId", visaId);
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
    public void excuteSuccessCallBack(CallBackVo<List<VisaTargetInfo>> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mPlaceUnits.clear();
            placeList.clear();
            placeList.addAll(mCallBackVo.getData());
            for (int i = 0; i < mCallBackVo.getData().size(); i++) {
                mPlaceUnits.add(mCallBackVo.getData().get(i).getPlaceName());
            }
        }
    }

    @Override
    public void excuteSuccessPersonCallBack(CallBackVo<List<PersonInfoBean>> mCallBackVo) {
        if (mCallBackVo != null & mCallBackVo.getData() != null && mCallBackVo.getData().size() > 0) {
            mPersonUnits.clear();
            mPersonUnits.addAll(mCallBackVo.getData());
        }
    }

    @Override
    public void excuteSuccessGoodsCallBack(CallBackVo<List<VisaInfoDtoList.VisaInfoDtoListBean>> mCallBackVoGoods) {
        mGoodsUnits.clear();
        if (mCallBackVoGoods != null && mCallBackVoGoods.getData() != null && mCallBackVoGoods.getData().size() > 0) {
            mGoodsUnits.clear();
            visaList.clear();
            visaList.addAll(mCallBackVoGoods.getData());
            for (int i = 0; i < mCallBackVoGoods.getData().size(); i++) {
                mGoodsUnits.add(mCallBackVoGoods.getData().get(i).getVisaName());
            }
        }
    }

    @Override
    public void excuteSuccessAddCallBack(CallBackVo<String> mCallBackVo) {
        Intent intent = new Intent(this, OtherOrderDetailActivity.class);
        intent.putExtra("orderId", mCallBackVo.getData());
        startActivity(intent);
    }
}
