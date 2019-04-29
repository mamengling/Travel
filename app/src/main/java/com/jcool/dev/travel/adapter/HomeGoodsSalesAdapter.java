package com.jcool.dev.travel.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jcool.dev.travel.R;
import com.jcool.dev.travel.base.BaseListAdapter;
import com.jcool.dev.travel.bean.GoodsBean;
import com.jcool.dev.travel.ui.TravelDefuiltActivity;
import com.jcool.dev.travel.ui.TravelSalesActivity;
import com.jcool.dev.travel.utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liufx on 16/4/20.
 */
public class HomeGoodsSalesAdapter extends BaseListAdapter<GoodsBean.RecordsBean> {
    private Activity activity;

    public HomeGoodsSalesAdapter(Activity context, List<GoodsBean.RecordsBean> list) {
        super(context, list);
        this.activity = context;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.xml_item_home_goods_sales, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GoodsBean.RecordsBean app = getItem(position);
        holder.tv_content.setText(app.getGoodsName());

        if (TextUtils.equals("", app.getIsOnline())) {

        }
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TravelSalesActivity.class);
                mContext.startActivity(intent);
            }
        });
        holder.goods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TravelDefuiltActivity.class);
                intent.putExtra("travelId",app.getGoodsId());
                mContext.startActivity(intent);
            }
        });

        holder.tv_money.setText("¥" + app.getSalesPrice());
        ImageLoaderUtils.showImageViewToRoundedCorners(mContext, app.getHeadImg(), holder.givImage, R.mipmap.icon_home_banner, R.mipmap.icon_home_banner);
        return convertView;
    }


    static class ViewHolder {
        @BindView(R.id.image_goods)
        ImageView givImage;
        @BindView(R.id.tv_goods_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tv_content;
        @BindView(R.id.tv_money)
        TextView tv_money;
        @BindView(R.id.title)
        RelativeLayout title;
        @BindView(R.id.goods)
        RelativeLayout goods;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
