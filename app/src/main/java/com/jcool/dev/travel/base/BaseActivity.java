package com.jcool.dev.travel.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.SharePreferenceUtil;
import com.jcool.dev.travel.view.CustomButtonDialog;


/**
 * BaseActivity
 *
 * @author W_X
 * @date 2018/5/9 0009 16:46
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract int getContentViewId();

    protected abstract void getExtra();

    protected abstract void initView();

    protected abstract void initTools();

    protected abstract void setListener();


    protected abstract void initData();


    private InputMethodManager manager = null;


    protected abstract void initView(Bundle savedInstanceState);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        StatusBarUtils.setStatusTextColor(true,this);
        setContentView(getContentViewId());
        ActivityCollector.getInstance().addActivity(this);
        getExtra();
        initView();
        initView(savedInstanceState);
        initTools();
        setListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.getInstance().removeActivity(this);
    }


    /**
     * 判断颜色是不是亮色
     *
     * @param color
     * @return
     * @from https://stackoverflow.com/questions/24260853/check-if-color-is-dark-or-light-in-android
     */
    private boolean isLightColor(@ColorInt int color) {
        return ColorUtils.calculateLuminance(color) >= 0.5;
    }

    /**
     * 获取StatusBar颜色，默认黑色
     *
     * @return
     */
    protected @ColorInt
    int getStatusBarColor() {
        return Color.parseColor("#FFE25A");
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // TODO Auto-generated method stub
        /**
         * 点击空白处隐藏软键盘
         */
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (getCurrentFocus() != null && getCurrentFocus().getWindowToken() != null) {
                if (manager == null) {
                    manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                }
                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
        return super.onTouchEvent(event);
    }

    protected boolean isLogin() {
        return SharePreferenceUtil.getBoolean(this, AppConfigStatic.IS_LOGIN, false);
    }

    protected void setUserInfo(UserInfo.UserInfoBean.SysUserBean info) {
        SharePreferenceUtil.setValue(this, AppConfigStatic.IS_LOGIN, true);
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_ID, info.getUserId() + "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_NAME, info.getUsername());
        SharePreferenceUtil.setValue(this, AppConfigStatic.PASSWORD, info.getPassword());
        SharePreferenceUtil.setValue(this, AppConfigStatic.NICK_NAME, info.getNickname());
        SharePreferenceUtil.setValue(this, AppConfigStatic.PHONE, info.getPhone());
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_TYPE, info.getUserType());
        SharePreferenceUtil.setValue(this, AppConfigStatic.WX_OPEN_ID, info.getWxOpenid());
        SharePreferenceUtil.setValue(this, AppConfigStatic.QQ_OPEN_ID, info.getQqOpenid());
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_SEX, info.getSex());
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_CITY, info.getCity());

    }

    protected void setToken(String token) {
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_TOKEN, token);
    }

    protected String getUserId() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.USER_ID, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.USER_ID, "");
        } else {
            return null;
        }
    }

    protected String getUsername() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.USER_NAME, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.USER_NAME, "");
        } else {
            return null;
        }
    }

    protected String getPassword() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.PASSWORD, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.PASSWORD, "");
        } else {
            return null;
        }
    }

    protected String getNickname() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.NICK_NAME, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.NICK_NAME, "");
        } else {
            return null;
        }
    }

    protected String getUserPhone() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.PHONE, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.PHONE, "");
        } else {
            return null;
        }
    }

    protected int getUserType() {
        if (SharePreferenceUtil.getInt(this, AppConfigStatic.USER_TYPE, 0) == 0) {
            return SharePreferenceUtil.getInt(this, AppConfigStatic.USER_TYPE, 0);
        } else {
            return 0;
        }
    }

    protected String getSex() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.USER_SEX, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.USER_SEX, "");
        } else {
            return null;
        }
    }

    protected String getCity() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.USER_CITY, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.USER_CITY, "");
        } else {
            return null;
        }
    }

    protected String getQqOpenid() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.QQ_OPEN_ID, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.QQ_OPEN_ID, "");
        } else {
            return null;
        }
    }

    protected String getWxOpenid() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.WX_OPEN_ID, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.WX_OPEN_ID, "");
        } else {
            return null;
        }
    }

    protected void loginOut() {
        SharePreferenceUtil.setValue(this, AppConfigStatic.IS_LOGIN, false);
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_ID, 0);
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_NAME, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.PASSWORD, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.NICK_NAME, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.PHONE, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_TYPE, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.WX_OPEN_ID, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.QQ_OPEN_ID, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_SEX, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_CITY, "");
        SharePreferenceUtil.setValue(this, AppConfigStatic.USER_TOKEN, "");
    }

    protected String getToken() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(this, AppConfigStatic.USER_TOKEN, ""))) {
            return SharePreferenceUtil.getString(this, AppConfigStatic.USER_TOKEN, "");
        } else {
            return null;
        }
    }


    protected void callPhone(final String phoneNumber) {
        final CustomButtonDialog dialog = new CustomButtonDialog(this);
        dialog.setText(phoneNumber);
        dialog.setLeftButtonText("取消");
        dialog.setLeftButtonTextColor(R.color.text_black);
        dialog.setRightButtonText("确定");
        dialog.setRightButtonTextColor(R.color.colorAccent);
        dialog.setButtonListener(new CustomButtonDialog.OnButtonListener() {
            @Override
            public void onLeftButtonClick(CustomButtonDialog var1) {
                dialog.cancel();
            }

            @Override
            public void onRightButtonClick(CustomButtonDialog var1) {
                dialog.cancel();
                toCallPhoneIn(phoneNumber);
            }
        });
    }


    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void toCallPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }


    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void toCallPhoneIn(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
