package com.jcool.dev.travel.ui;

import android.content.Intent;
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
import com.jcool.dev.travel.bean.PersonInfoBean;
import com.jcool.dev.travel.bean.VisaInfoDtoList;
import com.jcool.dev.travel.bean.VisaTargetInfo;
import com.jcool.dev.travel.iactivityview.VisaCommitActivityView;
import com.jcool.dev.travel.persenter.VisaCommitActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
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

    @Override
    protected int getContentViewId() {
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
                    }
                } else {
                    ToastUtils.showShortToast("请选择目的地");
                }
                break;
            case R.id.tv_change_person:
                initPersonPicker();
                break;
        }
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
        if (mCallBackVoGoods != null && mCallBackVoGoods.getData() != null && mCallBackVoGoods.getData().size() > 0) {
            mGoodsUnits.clear();
            visaList.clear();
            visaList.addAll(mCallBackVoGoods.getData());
            for (int i = 0; i < mCallBackVoGoods.getData().size(); i++) {
                mGoodsUnits.add(mCallBackVoGoods.getData().get(i).getVisaName());
            }
        }
    }
}
