package com.jcool.dev.travel.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.jcool.dev.travel.R;
import com.jcool.dev.travel.bean.OrderVisaInfo;
import com.jcool.dev.travel.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

public class OrderVisaListAdapter extends BaseQuickAdapter<OrderVisaInfo.RecordsBean, BaseViewHolder> {
    private Context mContext;
    private List<OrderVisaInfo.RecordsBean> mList = new ArrayList<>();

    public OrderVisaListAdapter(Context mContext, int layoutResId, @Nullable List<OrderVisaInfo.RecordsBean> data) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderVisaInfo.RecordsBean item) {
        helper.setText(R.id.tv_day, AppUtils.getDate2String(item.getRefundTime(), "yyyy-MM-dd"));
        helper.setText(R.id.tv_country, item.getVisaName());
        helper.setText(R.id.tv_time, "出行日期：" + item.getTripTime());
        helper.setText(R.id.tv_commit_time, "截止材料提交日期：" + item.getDataCommitTime());
        helper.setText(R.id.tv_money, "¥" + item.getVisaTotalamt());
        helper.addOnClickListener(R.id.tv_btn_life);
        helper.addOnClickListener(R.id.tv_btn_right);
        helper.addOnClickListener(R.id.tv_btn_center);
        // 订单状态(01:待付款；02：待发货；03；待提交资料；04；待收货；05：待评价；06：已完成；07：退款中；08：已退款；09：已关闭;10:资料待审核；11：资料审核驳回)
        if (TextUtils.equals("13", item.getOrderStatus())) {
            helper.setText(R.id.tv_status, "已取消");

            helper.setVisible(R.id.tv_btn_life, false);
            helper.setVisible(R.id.tv_btn_center, false);

            helper.setText(R.id.tv_btn_right, "查看详情");
            helper.setTag(R.id.tv_btn_right, "查看详情");
        } else if (TextUtils.equals("07", item.getOrderStatus())) {
            helper.setText(R.id.tv_status, "退款中");

            helper.setVisible(R.id.tv_btn_life, false);
            helper.setVisible(R.id.tv_btn_center, false);

            helper.setText(R.id.tv_btn_right, "查看详情");
        } else if (TextUtils.equals("10", item.getOrderStatus())) {
            helper.setText(R.id.tv_status, "资料待审核");

            helper.setVisible(R.id.tv_btn_life, false);
            helper.setVisible(R.id.tv_btn_center, false);

            helper.setTag(R.id.tv_btn_right, "查看详情");
        } else if (TextUtils.equals("03", item.getOrderStatus())) {
            helper.setText(R.id.tv_status, "待提交资料");

            helper.setVisible(R.id.tv_btn_life, false);
            helper.setVisible(R.id.tv_btn_center, true);

            helper.setText(R.id.tv_btn_center, "退款");
            helper.setText(R.id.tv_btn_right, "查看详情");
            helper.setTag(R.id.tv_btn_center, "退款");
            helper.setTag(R.id.tv_btn_right, "查看详情");
        } else if (TextUtils.equals("01", item.getOrderStatus())) {
            helper.setText(R.id.tv_status, "待付款");

            helper.setVisible(R.id.tv_btn_life, false);
            helper.setVisible(R.id.tv_btn_center, true);

            helper.setText(R.id.tv_btn_center, "取消");
            helper.setText(R.id.tv_btn_right, "立即支付");

            helper.setTag(R.id.tv_btn_center, "取消");
            helper.setTag(R.id.tv_btn_right, "立即支付");
        } else if (TextUtils.equals("11", item.getOrderStatus())) {
            helper.setText(R.id.tv_status, "资料审核驳回");

            helper.setVisible(R.id.tv_btn_life, false);
            helper.setVisible(R.id.tv_btn_center, false);

            helper.setText(R.id.tv_btn_right, "查看详情");
            helper.setTag(R.id.tv_btn_right, "查看详情");
        }
    }
}
