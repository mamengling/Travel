package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.UserInfoGetView;
import com.jcool.dev.travel.persenter.UserInfoGetPresenter;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoActivity extends BaseActivity implements View.OnClickListener, UserInfoGetView {
    private UserInfoGetPresenter mPresenter;
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

            }
        });
    }

    @Override
    protected void initData() {
        mPresenter.getUserInfo(getUserId());
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
                // TODO: 2019/4/3
                break;
            case R.id.tv_phone:
                // TODO: 2019/4/3
                break;
            case R.id.tv_sex:
                // TODO: 2019/4/3
                break;
            case R.id.tv_user_birthday:
                // TODO: 2019/4/3
                break;
            case R.id.tv_user_name:
                // TODO: 2019/4/3
                break;
            case R.id.image_head:
            case R.id.tv_head:
                // TODO: 2019/4/3
                break;
        }
    }

    @Override
    public void excuteSuccessUserCallBack(CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo) {
        if (mCallBackVo != null && mCallBackVo.getData() != null) {
            setUserInfo(mCallBackVo.getData());
            if (TextUtils.isEmpty(mCallBackVo.getData().getNickname())) {
                tv_user_name.setText(mCallBackVo.getData().getUsername());
            } else {
                tv_user_name.setText(mCallBackVo.getData().getNickname());
            }
            tv_phone.setText(mCallBackVo.getData().getPhone());
            ImageLoaderUtils.showImageViewToCircle(this, mCallBackVo.getData().getAvatar(), image_head, R.mipmap.icon_default_head);
        }
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
}
