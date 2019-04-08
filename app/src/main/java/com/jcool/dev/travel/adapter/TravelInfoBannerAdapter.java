package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.BannerBean;
import com.jcool.dev.travel.bean.TravelInfoBean;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.utils.log.LogUtil;
import com.jcool.dev.travel.view.rollviewpage.adapter.StaticPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：MLing on 2016/12/7 13:25
 * 邮箱：mamenglingkl1314@163.com
 */
public class TravelInfoBannerAdapter extends StaticPagerAdapter {
    private List<TravelInfoBean.BannerBean> mList = new ArrayList<>();
    private Context mContext;

    public TravelInfoBannerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<TravelInfoBean.BannerBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ImageLoaderUtils.loadImageViewLoding(mContext, mList.get(position).getFileUrl(), view, R.mipmap.icon_home_banne02, R.mipmap.icon_home_banne02);
        return view;
    }


    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }
}
