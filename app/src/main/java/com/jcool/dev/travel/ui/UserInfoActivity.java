package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.UploadImageFilesView;
import com.jcool.dev.travel.iactivityview.UserInfoGetView;
import com.jcool.dev.travel.persenter.UploadImageFilesPresenter;
import com.jcool.dev.travel.persenter.UserInfoGetPresenter;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.BuyTimePicker;
import com.jcool.dev.travel.utils.DateFormatUtils;
import com.jcool.dev.travel.utils.GlideLoader;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.view.CameraPhotoPicker;
import com.lcw.library.imagepicker.ImagePicker;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener, UserInfoGetView, UploadImageFilesView {
    private UserInfoGetPresenter mPresenter;
    private UploadImageFilesPresenter imagePresenter;
    private CityPickerView mCityPicker = new CityPickerView();
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_city)
    TextView tv_city;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_user_birthday)
    TextView tv_user_birthday;
    @BindView(R.id.tv_sex)
    TextView tv_sex;
    @BindView(R.id.tv_user_name)
    TextView tv_user_name;
    @BindView(R.id.tv_head)
    TextView tv_head;
    @BindView(R.id.image_head)
    ImageView image_head;
    private String filePath;
    private String avatar;
    private String nickname;
    private String birthday;
    private String sex;
    private String mCity;
    private int flag;
    private CameraPhotoPicker mPicker;
    private BuyTimePicker buyTimePicker;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_user_info;
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
        //预先加载仿iOS滚轮实现的全部数据
        mCityPicker.init(this);
        imagePresenter = new UploadImageFilesPresenter(this, this);
        mPresenter = new UserInfoGetPresenter(this, this);
        icon_right.setVisibility(View.GONE);
        icon_title.setText("个人信息");
        if (isLogin()) {
            tv_user_name.setText(TextUtils.isEmpty(getNickname()) ? getUsername() : getNickname());
            tv_phone.setText(getUserPhone());
            ImageLoaderUtils.showImageViewToCircle(this, getAvatar(), image_head, R.mipmap.icon_default_head);
        }
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_city.setOnClickListener(this);
        tv_sex.setOnClickListener(this);
        tv_user_name.setOnClickListener(this);
        tv_user_birthday.setOnClickListener(this);
        tv_head.setOnClickListener(this);
        image_head.setOnClickListener(this);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                mPresenter.getUserInfoToken(getToken());
            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getUserInfoToken(getToken());
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
            case R.id.tv_city:
                //添加默认的配置，不需要自己定义，当然也可以自定义相关熟悉，详细属性请看demo
                CityConfig cityConfig = new CityConfig.Builder()
                        .setCityWheelType(CityConfig.WheelType.PRO_CITY)
                        .setShowGAT(true)
                        .setCustomItemLayout(R.layout.x_item_city_picker)//自定义item的布局
                        .setCustomItemTextViewId(R.id.item_city_name_tv)
                        .showBackground(true)//是否显示半透明背景
                        .visibleItemsCount(4).build();
                cityConfig.setTitle("请选择城市");
                cityConfig.setTitleTextSize(14);
                cityConfig.setCancelText("取消");
                cityConfig.setCancelTextColorStr("#000000");
                cityConfig.setCancelTextSize(14);
                cityConfig.setCancelTextSize(14);
                cityConfig.setConfirmText("确定");//确认按钮文字
                cityConfig.setConfirmTextSize(14);//确认按钮文字大小
                cityConfig.setConfirmTextColorStr("#000000");
                cityConfig.setLineColor("#eeeeee");
                cityConfig.setDrawShadows(false);//滚轮不显示模糊效果
                mCityPicker.setConfig(cityConfig);
                //监听选择点击事件及返回结果
                mCityPicker.setOnCityItemClickListener(new OnCityItemClickListener() {
                    @Override
                    public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                        //省份province
                        //城市city
                        //地区district
                        flag = 3;
                        mCity = city.getName();
                        mPresenter.getUserUpdate(getToken());
                    }

                    @Override
                    public void onCancel() {
                        ToastUtils.showLongToast("已取消");
                    }
                });
                //显示
                mCityPicker.showCityPicker();
                break;
            case R.id.tv_phone:
                Intent intentPhone = new Intent(this, SetPhoneActivity.class);
                intentPhone.putExtra("phone", getUserPhone());
                startActivity(intentPhone);
                break;
            case R.id.tv_sex:
                initPicker();
                break;
            case R.id.tv_user_birthday:
                initDatePicker();
                break;
            case R.id.tv_user_name:
                Intent intent = new Intent(this, EdtUserInfoActivity.class);
                intent.putExtra("title", "修改昵称");
                intent.putExtra("hint", "请输入昵称");
                intent.putExtra("key", tv_user_name.getText().toString());
                startActivityForResult(intent, 101);
                break;
            case R.id.image_head:
            case R.id.tv_head:
                ImagePicker.getInstance()
                        .setTitle("上传照片或视频")//设置标题
                        .showCamera(true)//设置是否显示拍照按钮
                        .showImage(true)//设置是否展示图片
                        .showVideo(true)//设置是否展示视频
                        .setMaxCount(1)//设置最大选择图片数目(默认为1，单选)
                        .setImageLoader(new GlideLoader())//设置自定义图片加载器
                        .start(UserInfoActivity.this, AppConfigStatic.UPLOAD_IMAGE);//REQEST_SELECT_IMAGES_CODE为Intent调用的requestCode
                break;
        }
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
                    flag = 0;
                    nickname = data.getStringExtra("key");
                    mPresenter.getUserUpdate(getToken());
                }
                break;
            case 102:
                break;
            case 103:
                break;
            case AppConfigStatic.UPLOAD_IMAGE:
                ArrayList<String> mImage = data.getStringArrayListExtra(ImagePicker.EXTRA_SELECT_IMAGES);
                if (mImage.size() > 0) {
                    filePath = mImage.get(0);
                    imagePresenter.upLoadImage(filePath, this);
                }
                break;
        }
    }

    @Override
    public void excuteSuccessUserCallBack(CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo) {
        refreshLayout.finishRefresh();
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            setUserInfo(mCallBackVo.getData());
            if (TextUtils.isEmpty(mCallBackVo.getData().getNickname())) {
                tv_user_name.setText(mCallBackVo.getData().getUsername());
            } else {
                tv_user_name.setText(mCallBackVo.getData().getNickname());
            }
            tv_phone.setText(mCallBackVo.getData().getPhone());
            tv_user_birthday.setText(mCallBackVo.getData().getBirthday());
            tv_city.setText(mCallBackVo.getData().getCity());
            tv_sex.setText(mCallBackVo.getData().getSex());
            ImageLoaderUtils.showImageViewToCircle(this, mCallBackVo.getData().getAvatar(), image_head, R.mipmap.icon_default_head);
        }
    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast("修改成功");
        if (flag == 0) {
            tv_user_name.setText(nickname);
        } else if (flag == 1) {
            tv_sex.setText(sex);
        } else if (flag == 2) {
            tv_user_birthday.setText(birthday);
        } else if (flag == 3) {
            tv_city.setText(mCity);
        }
    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("nickname", nickname);
            object.put("avatar", avatar);
            object.put("birthday", birthday);
            object.put("sex", sex);
            object.put("city", mCity);
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
    public void excuteSuccessUploadCallBack(CallBackVo<String> mCallBackVo) {
        ImageLoaderUtils.showImageViewToCircle(this, mCallBackVo.getData(), image_head, R.mipmap.icon_default_head);
        avatar = mCallBackVo.getData();
        mPresenter.getUserUpdate(getToken());
    }


    private void initPicker() {
        // 通过时间戳初始化日期，毫秒级别
        mPicker = new CameraPhotoPicker(this, new CameraPhotoPicker.Callback() {
            @Override
            public void onSelected(int mStrUnits) {
                switch (mStrUnits) {
                    case 101:
                        flag = 1;
                        sex = "男";
                        mPresenter.getUserUpdate(getToken());
                        break;
                    case 102:
                        flag = 1;
                        sex = "女";
                        mPresenter.getUserUpdate(getToken());
                        break;
                }
            }
        });
        // 不允许点击屏幕或物理返回键关闭
        mPicker.setCancelable(true);
        // 不允许滚动动画
        mPicker.show();
    }


    private void initDatePicker() {
        long beginTimestamp = DateFormatUtils.str2Long("2000-01-01 00:00", true);
        long endTimestamp = DateFormatUtils.str2Long("2050-05-01 00:00", true);
        // 通过时间戳初始化日期，毫秒级别
        buyTimePicker = new BuyTimePicker(this, new BuyTimePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                flag = 2;
                birthday = DateFormatUtils.long2Str(timestamp, false);
                mPresenter.getUserUpdate(getToken());
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
