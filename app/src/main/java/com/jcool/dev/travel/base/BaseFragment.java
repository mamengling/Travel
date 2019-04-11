package com.jcool.dev.travel.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcool.dev.travel.bean.UserInfo;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.jcool.dev.travel.utils.SharePreferenceUtil;

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
    protected void setUserInfo(UserInfo info) {
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.IS_LOGIN, true);
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_ID, info.getUserInfo().getSysUser().getUserId()+"");
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_NAME, info.getUserInfo().getSysUser().getUsername());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.PASSWORD, info.getUserInfo().getSysUser().getPassword());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.NICK_NAME, info.getUserInfo().getSysUser().getNickname());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.PHONE, info.getUserInfo().getSysUser().getPhone());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_TYPE, info.getUserInfo().getSysUser().getUserType());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.WX_OPEN_ID, info.getUserInfo().getSysUser().getWxOpenid());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.QQ_OPEN_ID, info.getUserInfo().getSysUser().getQqOpenid());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_SEX, info.getUserInfo().getSysUser().getSex());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_CITY, info.getUserInfo().getSysUser().getCity());
        SharePreferenceUtil.setValue(getContext(), AppConfigStatic.USER_TOKEN, info.getAccesstoken());
    }

    /**
     * 获取用户信息
     *
     * @return userinfo
     */
    protected UserInfo.UserInfoBean.SysUserBean getUserInfo() {
        UserInfo.UserInfoBean.SysUserBean userBean = new UserInfo.UserInfoBean().getSysUser();
        userBean.setUserId(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_ID, ""));
        userBean.setUsername(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_NAME, ""));
        userBean.setPassword(SharePreferenceUtil.getString(getContext(), AppConfigStatic.PASSWORD, ""));
        userBean.setNickname(SharePreferenceUtil.getString(getContext(), AppConfigStatic.NICK_NAME, ""));
        userBean.setPhone(SharePreferenceUtil.getString(getContext(), AppConfigStatic.PHONE, ""));
        userBean.setUserType(SharePreferenceUtil.getInt(getContext(), AppConfigStatic.PHONE, 0));
        userBean.setSex(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_SEX, ""));
        userBean.setCity(SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_CITY, ""));
        userBean.setQqOpenid(SharePreferenceUtil.getString(getContext(), AppConfigStatic.QQ_OPEN_ID, ""));
        userBean.setWxOpenid(SharePreferenceUtil.getString(getContext(), AppConfigStatic.WX_OPEN_ID, ""));
        return userBean;
    }

    /**
     * 获取token
     *
     * @return
     */
    protected String getToken() {
        return SharePreferenceUtil.getString(getContext(), AppConfigStatic.USER_TOKEN, "");
    }
}
