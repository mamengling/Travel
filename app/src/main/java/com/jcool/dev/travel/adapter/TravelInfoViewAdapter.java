package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.TravelInfoBeanView;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;
import com.jcool.dev.travel.view.FixedGridView;
import com.jcool.dev.travel.view.rollviewpage.OnItemClickListener;
import com.jcool.dev.travel.view.rollviewpage.RollPagerView;
import com.jcool.dev.travel.view.rollviewpage.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/4/8 21:20
 */

public class TravelInfoViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<TravelInfoBeanView> mList = new ArrayList<>();
    private Context mContext;
    private ConstmOnItemOnclickListener mOnItemClickListener;

    public TravelInfoViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<TravelInfoBeanView> list1) {
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
    public int getItemViewType(int position) {
        return mList.get(position).getViewType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = null;
        if (viewType == 101) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_info, viewGroup, false);
            return new ViewInfoHolder(view);
        } else if (viewType == 102) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_title, viewGroup, false);
            return new ViewTitleHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_line_image, viewGroup, false);
            return new ViewImageHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (getItemViewType(i) == 101) {
            ((ViewInfoHolder) viewHolder).tv_info.setText(mList.get(i).getmCallBackVo().getData().getName());
            ((ViewInfoHolder) viewHolder).tv_money.setText("¥" + mList.get(i).getmCallBackVo().getData().getMinPrice());
            ((ViewInfoHolder) viewHolder).tv_number.setText(mList.get(i).getmCallBackVo().getData().getDefaultSellNumber() + "人一起出游");
            TravelInfoBannerAdapter mPagerAdapter = new TravelInfoBannerAdapter(mContext);
            ((ViewInfoHolder) viewHolder).rollPagerView.setPlayDelay(3000);
            ((ViewInfoHolder) viewHolder).rollPagerView.setAdapter(mPagerAdapter);
            ((ViewInfoHolder) viewHolder).rollPagerView.setHintView(new ColorPointHintView(mContext, Color.parseColor("#5ac5b3"), Color.WHITE));
            ((ViewInfoHolder) viewHolder).rollPagerView.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void onItemClick(int positionChild) {

                }
            });
            ((ViewInfoHolder) viewHolder).tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(null, i, 0, 2, null);
                }
            });
            if (mList.get(i).getmCallBackVo().getData().getBanner() != null) {
                mPagerAdapter.onReference(mList.get(i).getmCallBackVo().getData().getBanner());
            }
            TravelInfoLineAdapter mLineAdapter = new TravelInfoLineAdapter(mContext);
            ((ViewInfoHolder) viewHolder).fixedGridView.setAdapter(mLineAdapter);
            if (mList.get(i).getmCallBackVo().getData().getLines() != null) {
                mLineAdapter.onReference(mList.get(i).getmCallBackVo().getData().getLines());
            }
            ((ViewInfoHolder) viewHolder).radiogroup_full.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radiobutton0:
                            // 滚动到指定位置
//                        mRecyclerView.scrollToPosition(position);
                            // 平滑滚动到指定位置
                            mOnItemClickListener.clickItem(null, i, 0, 1, null);
                            break;
                        case R.id.radiobutton1:
                            mOnItemClickListener.clickItem(null, i, 1, 1, null);
                            break;
                        case R.id.radiobutton2:
                            mOnItemClickListener.clickItem(null, i, 2, 1, null);
                            break;
                    }
                }
            });
        } else if (getItemViewType(i) == 102) {
            ((ViewTitleHolder) viewHolder).tvTitle.setText(mList.get(i).getItemImage().getTitle());
        } else {
            ImageLoaderUtils.showImageViewToRoundedCorners(mContext, mList.get(i).getItemImage().getFileUrl(), ((ViewImageHolder) viewHolder).image_city_head, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewTitleHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView tvTitle;

        public ViewTitleHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewInfoHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.normal_view_pager)
        RollPagerView rollPagerView;
        @BindView(R.id.tv_info)
        TextView tv_info;
        @BindView(R.id.tv_money)
        TextView tv_money;
        @BindView(R.id.tv_number)
        TextView tv_number;
        @BindView(R.id.tv_more)
        TextView tv_more;
        @BindView(R.id.recyclerView)
        RecyclerView mRecyclerView;
        @BindView(R.id.fixedGridView)
        FixedGridView fixedGridView;
        @BindView(R.id.radiogroup_full)
        RadioGroup radiogroup_full;

        public ViewInfoHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class ViewImageHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView image_city_head;


        public ViewImageHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(ConstmOnItemOnclickListener listener) {
        this.mOnItemClickListener = listener;
    }
}
