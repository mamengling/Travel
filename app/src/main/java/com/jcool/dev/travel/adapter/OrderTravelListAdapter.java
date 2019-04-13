package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.OrderTravelInfoBean;
import java.util.List;

public class OrderTravelListAdapter extends BaseQuickAdapter<OrderTravelInfoBean.RecordsBean, BaseViewHolder> {
    private Context mContext;
    private List<OrderTravelInfoBean.RecordsBean> mList;

    public OrderTravelListAdapter(Context mContext, int layoutResId, @Nullable List<OrderTravelInfoBean.RecordsBean> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }



    @Override
    protected void convert(BaseViewHolder holder, OrderTravelInfoBean.RecordsBean item) {
        holder.setText(R.id.tv_money, "¥" + item.getTotalMoney());
        holder.setText(R.id.tv_day, item.getFormDatetime() + "");
        holder.setText(R.id.tv_name_tavel,item.getGoodsName() + "");
        holder.setText(R.id.tv_time,item.getFormDatetime() + "至" + item.getGoodsDate());
        // （可多个，用逗号隔开）CREATE订单已经提交;PAY订单已经支付;REFUNDING退款中;REFUNDED已退款;USED订单已经出行;EVALUATE订单已经评价;CLOSE订单取消或是关闭
        if (TextUtils.equals("REFUNDING", item.getState())) {
            holder.setText(R.id.tv_status,"退款中");
        } else if (TextUtils.equals("EVALUATE", item.getState())) {
            holder.setText(R.id.tv_status,"已评价");
        } else if (TextUtils.equals("CLOSE", item.getState())) {
            holder.setText(R.id.tv_status,"已取消");
        } else if (TextUtils.equals("PAY", item.getState())) {
            holder.setText(R.id.tv_status,"待出行");
        } else if (TextUtils.equals("CREATE", item.getState())) {
            holder.setText(R.id.tv_status,"待支付");
        } else if (TextUtils.equals("USED", item.getState())) {
            holder.setText(R.id.tv_status,"待评价");
        }
    }
}
