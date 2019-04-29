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
import com.jcool.dev.travel.bean.GoodsHistoryBean;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.ui.VisaDefuiltActivity;
import com.jcool.dev.travel.utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryGoodsAdapter extends RecyclerView.Adapter<HistoryGoodsAdapter.ViewHolder> {
    private List<GoodsHistoryBean> mList = new ArrayList<>();
    private Context mContext;

    public HistoryGoodsAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<GoodsHistoryBean> list1) {
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
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_history_goods, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int i) {
        ImageLoaderUtils.showImageViewToRoundedCorners(mContext, mList.get(i).getImage(), holder.image_goods, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
        holder.tv_goods_title.setText(mList.get(i).getName());
        holder.tv_money.setText("¥" + mList.get(i).getMoney());
        if (TextUtils.equals("102", mList.get(i).getType())) {
            holder.tv_goods_day.setText(mList.get(i).getDays() + "天");
            holder.tv_goods_day.setVisibility(View.VISIBLE);
        } else {
            holder.tv_goods_day.setVisibility(View.GONE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.equals("102", mList.get(i).getType())) {
                    Intent intent = new Intent(mContext, VisaDefuiltActivity.class);
                    intent.putExtra("visaId", mList.get(i).getId());
                    mContext.startActivity(intent);

                } else {
                    Intent intent = new Intent(mContext, TravelDefuiltActivity.class);
                    intent.putExtra("travelId", mList.get(i).getId());
                    mContext.startActivity(intent);
                }
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
        @BindView(R.id.tv_goods_day)
        TextView tv_goods_day;
        @BindView(R.id.tv_money)
        TextView tv_money;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
