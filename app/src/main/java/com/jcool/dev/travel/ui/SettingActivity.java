package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.btn_login_out)
    Button btn_login_out;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.tv_acc)
    TextView tv_acc;
    @BindView(R.id.tv_clear)
    TextView tv_clear;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_setting;
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
        ActivityCollector.getInstance().flagActivity(this);
    }

    @Override
    protected void setListener() {
        btn_login_out.setOnClickListener(this);
        icon_title_back.setOnClickListener(this);
        tv_acc.setOnClickListener(this);
        tv_clear.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        try {
            tv_clear.setText(AppUtils.getTotalCacheSize(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
            case R.id.btn_login_out:
                loginOut();
                ToastUtils.showShortToast("已退出");
                Platform platWechat = ShareSDK.getPlatform(Wechat.NAME);
                platWechat.removeAccount(true);
                Platform platQQ = ShareSDK.getPlatform(QQ.NAME);
                platQQ.removeAccount(true);
                finish();
                break;
            case R.id.tv_acc:
                if (isLogin()) {
                    Intent intent = new Intent(this, AccMangerActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.tv_clear:
                AppUtils.clearAllCache(this);
                ToastUtils.showShortToast("操作成功");
                tv_clear.setText("0M");
                break;
        }
    }
}
