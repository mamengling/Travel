package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.VisaInfoBeanView;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：MLing
 * 邮箱：mlingvip@163.com
 * 创建时间：2019/4/8 21:20
 */

public class VisaInfoViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<VisaInfoBeanView> mList = new ArrayList<>();
    private Context mContext;
    private ConstmOnItemOnclickListener mOnItemClickListener;

    public VisaInfoViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<VisaInfoBeanView> list1) {
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
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_visa_info, viewGroup, false);
            return new ViewInfoHolder(view);
        } else if (viewType == 102) {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_title, viewGroup, false);
            return new ViewTitleHolder(view);
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_travel_line_image, viewGroup, false);
            return new ViewImageHolder(view);
        }

    }

    public List<VisaInfoBeanView> getList() {
        return mList;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (getItemViewType(i) == 101) {
            ImageLoaderUtils.showImageViewToRoundedCorners(mContext, mList.get(i).getmCallBackVo().getData().getVisaImage(), ((ViewInfoHolder) viewHolder).image_goods, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
            ((ViewInfoHolder) viewHolder).tv_goods_title.setText(mList.get(i).getmCallBackVo().getData().getVisaName());
            ((ViewInfoHolder) viewHolder).tv_money.setText("¥" + mList.get(i).getmCallBackVo().getData().getVisaPrice());
            ((ViewInfoHolder) viewHolder).tv_persone.setText("已办理" + mList.get(i).getmCallBackVo().getData().getSalesVolume() + "人");
            ((ViewInfoHolder) viewHolder).tv_time.setText(mList.get(i).getmCallBackVo().getData().getHandleTime());
            ((ViewInfoHolder) viewHolder).tv_place.setText(mList.get(i).getmCallBackVo().getData().getCommonPlace());
            ((ViewInfoHolder) viewHolder).tv_days.setText(mList.get(i).getmCallBackVo().getData().getValidityDate() + "天");
            ((ViewInfoHolder) viewHolder).tv_stay_days.setText(mList.get(i).getmCallBackVo().getData().getStayDays() + "天");
            ((ViewInfoHolder) viewHolder).tv_entry_count.setText(mList.get(i).getmCallBackVo().getData().getEntryCount() + "次");
            ((ViewInfoHolder) viewHolder).tv_renyuan_number.setText("共需" + mList.get(i).getmCallBackVo().getData().getWorkingDataList().size() + "项材料");
            ((ViewInfoHolder) viewHolder).tv_zhiye_number.setText("共需" + mList.get(i).getmCallBackVo().getData().getFreeDataList().size() + "项材料");
            ((ViewInfoHolder) viewHolder).tv_xuesheng_number.setText("共需" + 0 + "项材料");
            ((ViewInfoHolder) viewHolder).tv_tuixiu_number.setText("共需" + 0 + "项材料");
            ((ViewInfoHolder) viewHolder).tv_ertong_number.setText("共需" + 0 + "项材料");
            ((ViewInfoHolder) viewHolder).acceptRange.setText(mList.get(i).getmCallBackVo().getData().getAcceptRange());
            ((ViewInfoHolder) viewHolder).tv_faces.setText(TextUtils.equals("02", mList.get(i).getmCallBackVo().getData().getIsMeet()) ? "不需要面试" : "需要面试");
            ((ViewInfoHolder) viewHolder).radiogroup_full.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.radiobutton0:
                            mOnItemClickListener.clickItem(null, i, 0, 1, null);
                            break;
                        case R.id.radiobutton1:
                            mOnItemClickListener.clickItem(null, i, 1, 1, null);
                            break;
                        case R.id.radiobutton2:
                            mOnItemClickListener.clickItem(null, i, 2, 1, null);
                            break;
                        case R.id.radiobutton3:
                            mOnItemClickListener.clickItem(null, i, 3, 1, null);
                            break;
                    }
                }
            });
            ((ViewInfoHolder) viewHolder).tv_renyuan_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(null, i, 0, 2, null);
                }
            });
            ((ViewInfoHolder) viewHolder).tv_zhiye_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(null, i, 1, 2, null);
                }
            });
            ((ViewInfoHolder) viewHolder).tv_xuesheng_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(null, i, 2, 2, null);
                }
            });
            ((ViewInfoHolder) viewHolder).tv_tuixiu_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(null, i, 3, 2, null);
                }
            });
            ((ViewInfoHolder) viewHolder).tv_ertong_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.clickItem(null, i, 4, 2, null);
                }
            });
        } else if (getItemViewType(i) == 102) {
            ((ViewTitleHolder) viewHolder).tvTitle.setText(mList.get(i).getTitle());
        } else {
            Glide.with(mContext)
                    .load(mList.get(i).getImageUrl())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                            ((ViewImageHolder) viewHolder).image_city_head.setImageBitmap(resource);
                        }
                    });
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
        @BindView(R.id.tv_persone)
        TextView tv_persone;
        @BindView(R.id.tv_money)
        TextView tv_money;
        @BindView(R.id.tv_goods_title)
        TextView tv_goods_title;
        @BindView(R.id.tv_goods_day)
        TextView tv_goods_day;
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_place)
        TextView tv_place;
        @BindView(R.id.tv_days)
        TextView tv_days;
        @BindView(R.id.tv_stay_days)
        TextView tv_stay_days;
        @BindView(R.id.tv_faces)
        TextView tv_faces;
        @BindView(R.id.tv_entry_count)
        TextView tv_entry_count;
        @BindView(R.id.tv_ertong_number)
        TextView tv_ertong_number;
        @BindView(R.id.tv_tuixiu_number)
        TextView tv_tuixiu_number;
        @BindView(R.id.tv_xuesheng_number)
        TextView tv_xuesheng_number;
        @BindView(R.id.tv_zhiye_number)
        TextView tv_zhiye_number;
        @BindView(R.id.tv_renyuan_number)
        TextView tv_renyuan_number;
        @BindView(R.id.acceptRange)
        TextView acceptRange;
        @BindView(R.id.image_goods)
        ImageView image_goods;
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
