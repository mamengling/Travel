package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.iactivityview.UserInfoGetView;
import com.jcool.dev.travel.persenter.UserInfoGetPresenter;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;
import com.jcool.dev.travel.utils.log.LogUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class AccMangerActivity extends BaseActivity implements View.OnClickListener, PlatformActionListener, UserInfoGetView {
    private UserInfoGetPresenter mPresenter;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.tv_phone)
    TextView tv_phone;
    @BindView(R.id.tv_pas)
    TextView tv_pas;
    @BindView(R.id.tv_wx_set)
    TextView tv_wx_set;
    @BindView(R.id.tv_qq_set)
    TextView tv_qq_set;
    private int flag;
    private String wxOpenid;
    private String qqOpenid;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_acc_mannger;
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
        ActivityCollector.getInstance().flagActivity(this);
        if (isLogin() && getUserPhone() != null) {
            tv_phone.setText(AppUtils.getPhoneCode(getUserPhone()));
        }
        if (!TextUtils.isEmpty(getQqOpenid())) {
            tv_qq_set.setText("已绑定");
        }
        if (!TextUtils.isEmpty(getWxOpenid())) {
            tv_wx_set.setText("已绑定");
        }
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        tv_pas.setOnClickListener(this);
        tv_phone.setOnClickListener(this);
        tv_qq_set.setOnClickListener(this);
        tv_wx_set.setOnClickListener(this);
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
            case R.id.tv_phone:
                Intent intent = new Intent(this, SetPhoneActivity.class);
                intent.putExtra("phone", getUserPhone());
                startActivity(intent);
                break;
            case R.id.tv_pas:
                Intent intentPass = new Intent(this, RePasswordActivity.class);
                startActivity(intentPass);
                break;
            case R.id.tv_qq_set:
                if (!TextUtils.equals("已绑定", tv_qq_set.getText().toString())) {
                    Platform platQQ = ShareSDK.getPlatform(QQ.NAME);
                    ShareSDK.setActivity(this);//抖音登录适配安卓9.0
                    platQQ.showUser(null);
                    platQQ.SSOSetting(false);
                    platQQ.setPlatformActionListener(this);
                    flag = 0;
                }

                break;
            case R.id.tv_wx_set:
                if (!TextUtils.equals("已绑定", tv_wx_set.getText().toString())) {
                    Platform platWechat = ShareSDK.getPlatform(Wechat.NAME);
                    ShareSDK.setActivity(this);//抖音登录适配安卓9.0
                    platWechat.showUser(null);
                    //这里开启一下SSO，防止OneKeyShare分享时调用了oks.disableSSOWhenAuthorize();把SSO关闭了
                    platWechat.SSOSetting(false);
                    platWechat.setPlatformActionListener(this);
                    flag = 1;
                }
                break;
        }
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
        LogUtil.i("hashMap", hashMap.toString());
        if (flag == 0) {//qq
            qqOpenid = (String) hashMap.get("openid");
        } else {//wx
            wxOpenid = (String) hashMap.get("openid");
        }
        mPresenter.getUserUpdate(getToken());
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }

    @Override
    public void excuteSuccessUserCallBack(CallBackVo<UserInfo.UserInfoBean.SysUserBean> mCallBackVo) {

    }

    @Override
    public void excuteSuccessUpdateCallBack(CallBackVo mCallBackVo) {
        ToastUtils.showShortToast("绑定成功");
        if (flag == 0) {//qq
            tv_qq_set.setText("已绑定");
        } else {//wx
            tv_wx_set.setText("已绑定");
        }

    }

    @Override
    public JSONObject getParamenters() {
        JSONObject object = new JSONObject();
        try {
            object.put("wxOpenid", wxOpenid);
            object.put("qqOpenid", qqOpenid);
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
}
