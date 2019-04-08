package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.bean.VisaBean;
import com.jcool.dev.travel.ui.TravelViseActivity;
import com.jcool.dev.travel.utils.ImageLoaderUtils;

import java.util.List;

public class VisaListAdapter extends BaseQuickAdapter<VisaBean.RecordsBean, BaseViewHolder> {
    private Context mContext;

    public VisaListAdapter(Context mContext, int layoutResId, @Nullable List<VisaBean.RecordsBean> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, VisaBean.RecordsBean item) {
        helper.setText(R.id.tv_goods_title, item.getVisaName());
        helper.setText(R.id.tv_goods_day, item.getStayDays() + "天");
        helper.setText(R.id.tv_money, "¥" + item.getVisaPrice());
        helper.setText(R.id.tv_money, "¥" + item.getVisaPrice());
        ImageLoaderUtils.showImageViewToRoundedCorners(mContext, item.getVisaImage(), (ImageView) helper.getView(R.id.image_goods), R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
    }
}
