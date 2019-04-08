package com.jcool.dev.travel.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
}
