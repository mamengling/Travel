package com.jcool.dev.travel.base;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.SharePreferenceUtil;
import com.jcool.dev.travel.view.CustomButtonDialog;

/**
 * BaseFragment
 *
 * @author W_X
 * @date 2018/6/21 0021 11:02
 */
public abstract class BaseFragment extends Fragment {


    public View rootView;
    public LayoutInflater inflater;

    protected abstract int getContentViewId();

    protected abstract void getExtra();

    /**
     * 只用来初始化View
     */
    protected abstract void initView(View view);

    protected abstract void setListener();

    /**
     * 这个用来初始化数据：如网络、读取本地
     */
    protected abstract void initTools();

    protected abstract void initData();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        this.inflater = inflater;
        if (rootView == null) {
            rootView = inflater.inflate(getContentViewId(), container, false);
        }
        getExtra();
        initView(rootView);
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        initTools();
        setListener();
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
        initData();
    }


    protected boolean isLogin() {
        return SharePreferenceUtil.getBoolean(getContext(), AppConfigStatic.IS_LOGIN, false);
    }

    /**
     * 保存用户信息
     *
     * @param info
     */
    protected void setUserInfo(UserInfo.UserInfoBean.SysUserBean info) {
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.IS_LOGIN, true);
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_ID, info.getUserId()+"");
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_NAME, info.getUsername());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.PASSWORD, info.getPassword());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.NICK_NAME, info.getNickname());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.PHONE, info.getPhone());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_TYPE, info.getUserType());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.WX_OPEN_ID, info.getWxOpenid());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.QQ_OPEN_ID, info.getQqOpenid());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_SEX, info.getSex());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_CITY, info.getCity());

    }
    protected void setToken(String token){
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_TOKEN, token);
    }
    protected String getUserId() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_ID, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_ID, "");
        } else {
            return null;
        }
    }

    protected String getUsername() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_NAME, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_NAME, "");
        } else {
            return null;
        }
    }

    protected String getPassword() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.PASSWORD, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.PASSWORD, "");
        } else {
            return null;
        }
    }

    protected String getNickname() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.NICK_NAME, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.NICK_NAME, "");
        } else {
            return null;
        }
    }

    protected String getUserPhone() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.PHONE, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.PHONE, "");
        } else {
            return null;
        }
    }

    protected int getUserType() {
        if (SharePreferenceUtil.getInt(getContext(), AppConfigStatic.USER_TYPE, 0) == 0) {
            return SharePreferenceUtil.getInt(getContext(), AppConfigStatic.USER_TYPE, 0);
        } else {
            return 0;
        }
    }

    protected String getSex() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_SEX, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_SEX, "");
        } else {
            return null;
        }
    }

    protected String getCity() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_CITY, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_CITY, "");
        } else {
            return null;
        }
    }

    protected String getQqOpenid() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.QQ_OPEN_ID, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.QQ_OPEN_ID, "");
        } else {
            return null;
        }
    }

    protected String getWxOpenid() {
        if (!TextUtils.isEmpty(SharePreferenceUtil.getString(getContext(), AppConfigStatic.WX_OPEN_ID, ""))) {
            return SharePreferenceUtil.getString(getContext(), AppConfigStatic.WX_OPEN_ID, "");
        } else {
            return null;
        }
    }


    /**
     * 获取token
     *
     * @return
     */
    protected String getToken() {
        return SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_TOKEN, "");
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

    protected void callPhone(final String phoneNumber) {
        final CustomButtonDialog dialog = new CustomButtonDialog(getContext());
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
