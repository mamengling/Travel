package com.jcool.dev.travel.fragment;

import android.os.Bundle;
import android.view.View;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseFragment;

public class OtherVisaTabFragment extends BaseFragment {

    private int number;
    private String url;
    private String orderStatus;

    public static OtherVisaTabFragment newInstance(String url, String orderStatus) {

        Bundle args = new Bundle();
        args.getString("url", url);
        args.getString("orderStatus", orderStatus);
        OtherVisaTabFragment fragment = new OtherVisaTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_visa_order;
    }

    @Override
    protected void getExtra() {
        number = getActivity().getIntent().getIntExtra("number", 0);
        url = getArguments().getString("url", "");
        orderStatus = getArguments().getString("orderStatus", "");
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void initTools() {

    }

    @Override
    protected void initData() {

    }
}
