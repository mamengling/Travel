package com.jcool.dev.travel.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;
import com.jcool.dev.travel.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EdtUserInfoActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;
    @BindView(R.id.icon_title)
    TextView icon_title;
    @BindView(R.id.icon_right)
    TextView icon_right;
    @BindView(R.id.edt_key)
    EditText edt_key;
    @BindView(R.id.btn_commit)
    Button btn_commit;
    private String mStrKey;
    private String title;
    private String hint;
    private String key;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_edt_user_info;
    }

    @Override
    protected void getExtra() {
        title = getIntent().getStringExtra("title");
        hint = getIntent().getStringExtra("hint");
        key = getIntent().getStringExtra("key");
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initTools() {
        icon_title.setText(title);
        edt_key.setHint(hint);
        if (!TextUtils.isEmpty(key)){
            edt_key.setText(key);
        }
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        btn_commit.setOnClickListener(this);
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
            case R.id.btn_commit:
                inputSuccess();
                break;
        }
    }

    private void inputSuccess() {
        mStrKey = edt_key.getText().toString().trim();
        if (TextUtils.isEmpty(mStrKey)) {
            ToastUtils.showShortToast("请输入" + title);
        }
        Intent intent = new Intent();
        intent.putExtra("key", mStrKey);
        setResult(RESULT_OK, intent);
        finish();
    }
}
