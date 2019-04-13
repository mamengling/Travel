package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.OrderInfoOthBean;
import com.jcool.dev.travel.view.ConstmOnItemOnclickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderOtherAdapter extends RecyclerView.Adapter<OrderOtherAdapter.ViewHolder> {
    private List<OrderInfoOthBean> mList = new ArrayList<>();
    private Context mContext;
    private ConstmOnItemOnclickListener<OrderInfoOthBean> mOnItemClickListener;

    public OrderOtherAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void onReference(List<OrderInfoOthBean> list1) {
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.xml_item_order_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tv_name.setText(mList.get(position).getLinkName());
        holder.tv_time.setText(mList.get(position).getCreatedTime() + "");
        holder.tv_shop.setText(mList.get(position).getShopType());
        holder.tv_order_number.setText(mList.get(position).getOrderId());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.clickItem(holder.itemView, position, 0, 0, mList.get(position));
            }
        });
        //订单总状态（01：通过；02：驳回；03：待审核）

        if (TextUtils.equals("01", mList.get(position).getOrderStatus())) {
            holder.tv_order_status.setText("已审核");
        } else if (TextUtils.equals("02", mList.get(position).getOrderStatus())) {
            holder.tv_order_status.setText("已退审");
        } else {
            holder.tv_order_status.setText("待审核");
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_time)
        TextView tv_time;
        @BindView(R.id.tv_shop)
        TextView tv_shop;
        @BindView(R.id.tv_name)
        TextView tv_name;
        @BindView(R.id.tv_order_number)
        TextView tv_order_number;
        @BindView(R.id.tv_order_status)
        TextView tv_order_status;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public void setOnItemClickListener(ConstmOnItemOnclickListener<OrderInfoOthBean> listener) {
        this.mOnItemClickListener = listener;
    }
}
