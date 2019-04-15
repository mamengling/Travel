package com.jcool.dev.travel.wxapi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.ui.OrderTabActivity;
import com.jcool.dev.travel.utils.AppConfigStatic;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private static final String TAG = "WXPayEntryActivity";
    private IWXAPI api;
    private WebView wxPayEntrywebView;
    private ImageButton wxPayEntryIbBack;
    private String url;

    @Override
    protected int getContentViewId() {
        return R.layout.pay_result;
    }

    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initTools() {
        api = WXAPIFactory.createWXAPI(this, AppConfigStatic.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(final BaseResp resp) {
        Log.d(TAG, "onPayFinish, errCode = " + resp.errCode);

        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {
                Intent intent = new Intent(WXPayEntryActivity.this, OrderTabActivity.class);
                startActivity(intent);
                finish();
            } else if (resp.errCode == -1) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WXPayEntryActivity.this);
                builder.setTitle("提示");
                builder.setMessage("微信支付失败");

                builder.setNegativeButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(WXPayEntryActivity.this, String.valueOf(resp.errStr + resp.errCode), Toast.LENGTH_LONG).show();
                        WXPayEntryActivity.this.finish();
                    }
                });
                builder.show();
            } else if (resp.errCode == -2) {
                AlertDialog.Builder builder = new AlertDialog.Builder(WXPayEntryActivity.this);
                builder.setTitle("提示");
                builder.setMessage("微信支付已取消");

                builder.setNegativeButton("确定", new OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        WXPayEntryActivity.this.finish();
                    }
                });
                builder.show();
            }

        }
    }

}