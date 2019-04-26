package com.jcool.dev.travel.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseActivity;
import com.jcool.dev.travel.bean.CallBackVo;
import com.jcool.dev.travel.fragment.CollectionGoodsFragment;
import com.jcool.dev.travel.fragment.CollectionVisaFragment;
import com.jcool.dev.travel.fragment.OrderGoodsFragment;
import com.jcool.dev.travel.fragment.OrderVisaFragment;
import com.jcool.dev.travel.iactivityview.CollectionActivityView;
import com.jcool.dev.travel.persenter.CollectionActivityPresenter;
import com.jcool.dev.travel.utils.StatusBarUtil;
import com.jcool.dev.travel.utils.StatusBarUtils;

import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionActivity extends BaseActivity implements CollectionActivityView,View.OnClickListener {
    private CollectionActivityPresenter mPresenter;
    @BindView(R.id.radiogroup_full)
    RadioGroup radiogroup_full;
    @BindView(R.id.icon_title_back)
    TextView icon_title_back;

    @Override
    protected int getContentViewId() {
        StatusBarUtils.setStatusTextColor(true, this);
        StatusBarUtil.setColor(this, Color.parseColor("#ffffff"));
        return R.layout.activity_collection_list;
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
        mPresenter = new CollectionActivityPresenter(this, this);
        toogleFragment(CollectionGoodsFragment.class);
    }

    @Override
    protected void setListener() {
        icon_title_back.setOnClickListener(this);
        radiogroup_full.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radiobutton0:
                        toogleFragment(CollectionGoodsFragment.class);
                        break;

                    case R.id.radiobutton1:
                        toogleFragment(CollectionVisaFragment.class);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {

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
    public void toogleFragment(Class<? extends Fragment> c) {
        FragmentManager manager = getSupportFragmentManager();
        String tag = c.getName();
        FragmentTransaction transaction = manager.beginTransaction();
        Fragment fragment = manager.findFragmentByTag(tag);

        if (fragment == null) {
            try {
                fragment = c.newInstance();
                // 替换时保留Fragment,以便复用
                transaction.add(R.id.content_frame, fragment, tag);
            } catch (Exception e) {
                // ignore
            }
        } else {
            // nothing
        }
        // 遍历存在的Fragment,隐藏其他Fragment
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null)
            for (Fragment fm : fragments)
                if (!fm.equals(fragment))
                    transaction.hide(fm);

        transaction.show(fragment);
        transaction.commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_title_back:
                finish();
                break;
        }
    }
}
