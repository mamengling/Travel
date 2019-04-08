package com.jcool.dev.travel.fragment;

import android.os.Bundle;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseFragment;

/**
 * 签证
 */
public class VisaFragment extends BaseFragment {
    public static VisaFragment newInstance() {

        Bundle args = new Bundle();

        VisaFragment fragment = new VisaFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void getExtra() {

    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void initTools() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_visa;
    }
}
