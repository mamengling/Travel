package com.jcool.dev.travel.persenter;

import android.content.Context;

import com.jcool.dev.travel.iactivityview.CollectionActivityView;

public class CollectionActivityPresenter {
    private CollectionActivityView mCollectionActivityView;
    private Context mContext;

    public CollectionActivityPresenter(CollectionActivityView mCollectionActivityView, Context mContext) {
        this.mCollectionActivityView = mCollectionActivityView;
        this.mContext = mContext;
    }



}
