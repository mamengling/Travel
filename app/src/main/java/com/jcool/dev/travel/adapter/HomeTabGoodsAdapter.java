package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.TravelBean;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTabGoodsAdapter extends RecyclerView.Adapter<HomeTabGoodsAdapter.ViewHolder> {
    private Context mContext;
    private List<TravelBean.RecordsBean> mList = new ArrayList<>();
    private ConstmOnItemOnclickListener onItemClickListener;

    public HomeTabGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<TravelBean.RecordsBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_home_tab_goods_sales, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        if (mList.size() == 0) {
            viewHolder.info.setVisibility(View.GONE);
            viewHolder.tv_more.setVisibility(View.VISIBLE);
            viewHolder.tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.clickItem(viewHolder.info, i, 0, 1, "");
                }
            });
        } else {
            if (i == mList.size() - 1) {
                viewHolder.tv_more.setVisibility(View.VISIBLE);
            } else {
                viewHolder.tv_more.setVisibility(View.GONE);
            }
            TravelBean.RecordsBean item = mList.get(i);
            viewHolder.tvTitle.setText(item.getName());
            viewHolder.tv_money.setText("¥" + item.getMinPrice() + "起");
            ImageLoaderUtils.showImageViewToRoundedCorners(mContext, item.getHeadImg(), viewHolder.givImage, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
            viewHolder.info.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.clickItem(viewHolder.info, i, 0, 0, mList.get(i).getId());
                }
            });

            viewHolder.tv_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null)
                        onItemClickListener.clickItem(viewHolder.info, i, 0, 1, "");
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        if (mList == null || mList.size() == 0) {
            return 1;
        } else {
            return mList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_goods)
        ImageView givImage;

        @BindView(R.id.tv_goods_title)
        TextView tvTitle;

        @BindView(R.id.tv_money)
        TextView tv_money;

        @BindView(R.id.tv_more)
        TextView tv_more;
        @BindView(R.id.info)
        RelativeLayout info;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setOnItemClickListener(ConstmOnItemOnclickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
