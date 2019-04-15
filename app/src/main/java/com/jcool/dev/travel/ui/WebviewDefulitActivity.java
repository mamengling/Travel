package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.ActivityAnim;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.log.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者： MLing
 * 邮箱：mamenglingkl1314@163.com
 * 创建时间 ：2017/7/7 14:39
 * $DESE$
 */
public class WebviewDefulitActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.web_view)
    WebView webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String title;
    private String loadUrl;


    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_webview;
    }

    @Override
    protected void getExtra() {
        title = getIntent().getStringExtra("title");
        loadUrl = getIntent().getStringExtra("loadUrl");
        LogUtil.i("loadUrl", loadUrl);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        icon_title.setText(title);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        icon_right.setOnClickListener(this);
        webView.setWebViewClient(new WebViewClient() {
            //覆写shouldOverrideUrlLoading实现内部显示网页
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO 自动生成的方法存根
                view.loadUrl(url);
                icon_title.setText(webView.getTitle());
                return true;
            }
        });
        WebSettings seting = webView.getSettings();
        seting.setJavaScriptEnabled(true);//设置webview支持javascript脚本
        seting.setUseWideViewPort(true);
        seting.setLoadWithOverviewMode(true);
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    icon_title.setText(webView.getTitle());
                    progressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    progressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    progressBar.setProgress(newProgress);//设置进度值
                }
            }
        });
    }

    @Override
    protected void initData() {
        webView.loadUrl(loadUrl);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    //设置返回键动作（防止按返回键直接退出程序)
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO 自动生成的方法存根
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                webView.goBack();
                return true;
            } else {
                finish();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_title_back:
                icon_title.setText(title);
                if (webView.canGoBack()) {//当webview不是处于第一页面时，返回上一个页面
                    webView.goBack();
                } else {
                    ActivityAnim.endActivity(WebviewDefulitActivity.this);
                }
                break;
            case R.id.icon_right:
                webView.loadUrl(loadUrl);
                break;
        }
    }
}
