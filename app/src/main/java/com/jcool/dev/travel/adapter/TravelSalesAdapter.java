package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.GoodsBean;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.utils.AppUtils;
import com.jcool.dev.travel.utils.ImageLoaderUtils;
import com.jcool.dev.travel.utils.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TravelSalesAdapter extends RecyclerView.Adapter<TravelSalesAdapter.ViewHolder> {
    private List<GoodsBean.RecordsBean> mList = new ArrayList<>();
    private Context mContext;

    public TravelSalesAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<GoodsBean.RecordsBean> list1) {
        if (list1 != null) {
            mList.clear();
            mList.addAll(list1);
            notifyDataSetChanged();
        } else {
            mList.clear();
            notifyDataSetChanged();
        }
    }

    public void addOnReference(List<GoodsBean.RecordsBean> list1) {
        if (list1 != null) {
            mList.addAll(list1);
            notifyDataSetChanged();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_sales_goods, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int i) {
        ImageLoaderUtils.showImageViewToRoundedCorners(mContext, mList.get(i).getHeadImg(), holder.image_goods, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
        holder.tv_goods_title.setText(mList.get(i).getGoodsName());
        holder.tv_cunt.setText(mList.get(i).getStockSize() + "");
        holder.tv_money.setText("¥" + mList.get(i).getSalesPrice());
        TimeUtil timeUtil = new TimeUtil();
        if (TextUtils.equals("ING", mList.get(i).getState())) {
            holder.tv_goods_time.setText("距离结束\t" + timeUtil.getTimeDifference(AppUtils.getTimeAtt("yyyy-MM-dd HH:mm:ss"), AppUtils.getDate2String(mList.get(i).getSalesEndTime(), "yyyy-MM-dd HH:mm:ss")));
        } else if (TextUtils.equals("FUTURE", mList.get(i).getState())) {
            holder.tv_goods_time.setText("即将开始");
        } else if (TextUtils.equals("ENDED", mList.get(i).getState())) {
            holder.tv_goods_time.setText("已售罄");
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TravelDefuiltActivity.class);
                intent.putExtra("travelId", mList.get(i).getGoodsId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_goods)
        ImageView image_goods;
        @BindView(R.id.tv_goods_title)
        TextView tv_goods_title;
        @BindView(R.id.tv_goods_time)
        TextView tv_goods_time;
        @BindView(R.id.tv_money)
        TextView tv_money;
        @BindView(R.id.tv_cunt)
        TextView tv_cunt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
