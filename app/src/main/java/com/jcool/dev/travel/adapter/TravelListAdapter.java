package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.utils.ImageLoaderUtils;

import java.util.List;

public class TravelListAdapter extends BaseQuickAdapter<TravelBean.RecordsBean, BaseViewHolder> {
    private Context mContext;

    public TravelListAdapter(Context mContext, int layoutResId, @Nullable List<TravelBean.RecordsBean> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, TravelBean.RecordsBean item) {
        helper.setText(R.id.tv_goods_title, item.getName());
        helper.setText(R.id.tv_money, "¥" + item.getMinPrice());
        ImageLoaderUtils.showImageViewToRoundedCorners(mContext, item.getHeadImg(), (ImageView) helper.getView(R.id.image_goods), R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
    }
}
