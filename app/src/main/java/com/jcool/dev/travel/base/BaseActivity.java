package com.jcool.dev.travel.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;
import android.support.v4.graphics.ColorUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.jcool.dev.travel.utils.ActivityCollector;
import com.jcool.dev.travel.utils.StatusBarUtils;


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
        StatusBarUtils.setStatusTextColor(true,this);
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

}
